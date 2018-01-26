# CRUD

루비는 기본적으로 sqlite3가 내장되어있으며 설정에서 바꿀 수 있습니다. 기본적으로 프레임워크가 가벼운게 특징인 레일즈다보니 굳이 오라클과 같은 큰 데이터베이스를 연동해서 사용할 필요는 없어보입니다. 

쉽게 db를 사용할 수 있게 라이브러리를 불러오면 좋습니다. gemfile에 가서 `gem 'rails_db'`

## DB

장고와 비슷하게 url을 통해서 관리 툴에 접근할 수 있습니다. url뒤에 `rails/db/`라고 입력하면 화면으로 넘어갑니다.

### DB생성

`rails g model post` 라고 터미널에 입력하면

```
invoke  active_record
create  db/migrate/20180124145534_create_posts.rb
create  app/models/post.rb
invoke  test_unit
create  test/models/post_test.rb
create  test/fixtures/posts.yml
```

이런 프로세스가 생깁니다. 테스트 파일 제외하면 2개의 파일이 생성됩니다.

#### db/migrate/20180124145534_create_posts.rb

숫자는 그냥 시간이고 뒤에 posts를 create하겠다라는겁니다. 파일로 들어가봅시다.

```ruby
class CreatePosts < ActiveRecord::Migration[5.1]
  def change
    create_table :posts do |t|
      # t.string :title
      # t.text :content
      t.timestamps
    end
  end
end
```

이건 단순히 만들겠다라는겁니다. 그냥 만들때 이런 코드대로 만들어라는 거고 만드는 명령어는 따로 있습니다.

`rake db:migrate`입니다. 이걸하면 이제 migrate되어 실제 테이블이 생성됩니다.

이제 간단한 게시판을 만들어보겠습니다. 

## 생성

기본적으로 컨트롤러를 만들고 뷰, routes 설정을합니다.

DB에 데이터가 없으니까 생성부터 해봅시다.

```ruby
def create
  @post = Post.new
  @post.title = params[:input_title]
  @post.content = params[:input_content]
  @post.save

  #방금 만든 row의 id를 사용하려면 save후에 id를 가져와서 사용하면 됩니다.
  redirect_to "/posts/show/#{@post.id}"
end
```

컨트롤러 메서드(액션)인데 이런식으로 합니다. 위에서 `rails g model post`로 테이블을 생성했습니다. 그 Post가 클래스로 `app/models/post.rb`여기 생성이 됩니다.  이 친구를 사용하면 connect할 수 있습니다.  `@post = Post.new`로 할 수 있습니다.

`@post.save`를 사용하면 rails_db가 변환하여 `INSERT INTO "posts" ("title", "content", "created_at", "updated_at") VALUES (?, ?, ?, ?)`이런식으로 알아서 만들어줍니다. 

`redirect_to`를 사용하면 리다이렉트 할 수 있고, 바로 방금 만든 row의 아이디를 사용하려면 `@post.id`를 사용합니다.

## 조회

```ruby
def index
  @posts = Post.all
end

#   ---------------- view ------------
<% @posts.each do |post| %>
  <h3>
  	<a href="/posts/show/<%= post.id %>"><%= post.title %></a>
  </h3>
<% end %>
```

전체 조회는 정말 쉽습니다. Post.all을 이용하면 전제 row를 가져옵니다.

하나의 결과만 보여주기위해서는 find를 사용합니다.

```ruby
def show
  @post_id = params[:post_id]
  @post = Post.find(@post_id)
end
```

## 삭제

```ruby
def destroy
  @post = Post.find(params[:post_id])
  @post.destroy

  redirect_to "/posts/index"
end
```

제일 중요한 점은`@post.destroy`입니다.

`data-confirm="진짜로 수정하시겠습니까?"` html에 이런 기능이있다. window.confirm과 같으니 애용하도록 하자.

## 수정

update는 사실 다를게 없습니다. UPDATE문을 따로 쓰지 않아도 이미 만들어진 객체를 save하면 알아서 업데이트가 진행됩니다.

## POST

application_controller라는 파일에서 `  protect_from_forgery with: :exception` 라는 부분이 에러를 내는 부분입니다. post로 접근했을 때 CSRF를 막기위해 존재합니다.

a 태그를 통해서 넘어갈때 포스트를 사용하는경우(삭제 버튼을 눌러야 하는경우) 태그안에 method-data="post"라고하면 잘 넘어갑니다.