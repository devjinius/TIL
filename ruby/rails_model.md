# Model

## 관계설정

1:1, 1:N등의 관계를 설정하고 싶다. 예를들어 1 : N 관계로 포스트 : 댓글의 테이블이 필요하다면 /app/models에 들어가 post.rb 와 comment.rb로 들어가자.

굉장히 직관적이다.

```ruby
class Post < ApplicationRecord
    # 포스트는 많은 코멘트들을 가지고 있다.
    has_many :comments
end
```

```ruby
class Comment < ApplicationRecord
    #코멘트들은 하나의 포스트에 속해있다.
    belongs_to :post
end
```

이렇게하면 관계 설정은 끝났으나 실제로 외래키를 만들어주어야 한다. 

``` ruby
class CreateComments < ActiveRecord::Migration[5.1]
  def change
    create_table :comments do |t|
      t.string :content
      t.belongs_to :post      # 이부분이 외래키
      t.timestamps
    end
  end
end
```

이렇게 적어주면 된다. 역시 굉장히 직관적이다. 물론 그냥 `t.integer : post_id`로 적어도 무관하다.

나머지 일련의 작업들은 거의 비슷하다. 대신 기존의 java와 좀 다른 작업은 부모의 id를 컨트롤러로 보낼때 보통 input - hidden을 이용해서 넘겼었다. 다만 여기서는 그냥 routes에 넘기는 주소값에 담아 보내면 params에 추가된다.

### 댓글보여주기

댓글을 보여주려면 `SELECT * FROM comment WHERE post_id='부모의 id'`인 친구를 찾아야한다. 기존에는 그냥 `@post.find(id)`이렇게 했는데 이건 그냥 id고 외래키로 조회할때는 `.where()` 메서드를 사용한다.

```ruby
# 댓글 select * form comment where post_id = '@post_id'
@comment = Comment.where(post_id: @post_id)
```

이렇게 말이다.

`@comment = Comment.where("post_id = #{@post_id}")`정확히는 이건데 둘은 완전히 동일하다.

### dependent

부모 레코드가 지워지면 자손것을 어떻게 할것이냐에 대한 문제입니다. 공식 가이드를 보면

>4.3.2.5 `:dependent`
>
>Controls what happens to the associated objects when their owner is destroyed:
>
>- `:destroy` causes all the associated objects to also be destroyed
>- `:delete_all` causes all the associated objects to be deleted directly from the database (so callbacks will not execute)
>- `:nullify` causes the foreign keys to be set to `NULL`. Callbacks are not executed.
>- `:restrict_with_exception` causes an exception to be raised if there are any associated records
>- `:restrict_with_error` causes an error to be added to the owner if there are any associated objects

이렇게 기술되어있습니다. 그래서

```ruby
class Post < ApplicationRecord
    # 포스트는 많은 코멘트들을 가지고 있다.
    has_many :comments, dependent: :destroy
end
```

이렇게 수정하면 됩니다. 여기서 눈여겨볼점은 `dependent: :destroy`입니다. 원래는 `:dependent => :destroy`입니다. 해쉬입니다. 그치만 역시 쉽게 표현이 가능한데 중요한 점은 콜론사이에 한번 띄어쓰기가 되어야 한다는 점입니다.	