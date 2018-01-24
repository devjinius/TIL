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