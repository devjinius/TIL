# REST

REST는 자원을 정의하고 주소를 지정하는 방법들의 모음입니다.

우리는 REST에 맞게 잘 디자인된 것을 보고 RESTful하다고 합니다.

Rails역시 RESTful한 framework입니다. 우선 REST에 대해서 좀 더 알아봅시다.

## REST 구성요소

REST에는 세가지가 존재합니다.

1. Resource
2. Method
3. Message

우선 2번 Method부터 보면 Http Method에서 보는 그 메서드입니다. CRUD기능은 각각 Post / Get / Put, Patch / Delete에 대응됩니다. 

1번 리소스는 URL 과 관련있습니다.  URL은 Uniform Resource Locator의 약자인데 여기 Resource가 있습니다. 네 같습니다. 즉 우리가 이용하는 자원입니다. 물리적인 파일의 위치나 Database의 메모리(모델)이 될 수 있습니다.

마지막 3번 Message는 What에 해당됩니다. 쉽게 parameters입니다. 루비에서 해쉬로 넘기는 데이터에 해당합니다.

## RESTful하게 routes 설정하기

이전에 이용했던 CRUD프로젝트의 경로들입니다. 이걸 RESTful하게 변경해봅시다.

``` ruby
Rails.application.routes.draw do

  get '/posts/new' => 'posts#new'
  get '/posts/index' => 'posts#index'
  get '/posts/show/:post_id' => 'posts#show'
  get '/posts/edit/:post_id' => 'posts#edit'
  post '/posts/create' => 'posts#create'
  post '/posts/destroy/:post_id' => 'posts#destroy'
  post '/posts/update/:post_id' => 'posts#update'

  root 'posts#index'
end
```

```ruby
Rails.application.routes.draw do
  
  get '/posts/new' => 'posts#new'
  get '/posts/index' => 'posts#index'
  
  # /posts의/ 아이디가 어떤것을/ 보여줘! 라는것을 의미하게끔 순서를 변경합니다.
  get '/posts/:post_id/show' => 'posts#show'
  # edit도 마찬가지입니다.
  get '/posts/:post_id/edit' => 'posts#edit'
  
  # 기본적으로 Post는 Create의 의미를 담고있습니다. 따라서 자원에 create를 쓰지 않아도 됩니다.
  post '/posts' => 'posts#create'
  # 이와 마찬가지로 delete역시 따로 설정하지 않아도 됩니다.
  delete '/posts/:post_id' => 'posts#destroy'  
  # put과 patch도 마찬가지입니다.
  put '/posts/:post_id' => 'posts#update'
  # patch '/posts/:post_id' => 'posts#update'
  
  root 'posts#index'
end
```

## 업그레이드

alias를 주어 업그레이드 할 수 있습니다.

```ruby
Rails.application.routes.draw do
  
  get '/posts/new' => 'posts#new'
  get '/posts/index' => 'posts#index'
  get '/posts/:post_id/show' => 'posts#show', as: "show"
  get '/posts/:post_id/edit' => 'posts#edit', as: "edit_post"
  post '/posts' => 'posts#create'
  delete '/posts/:post_id' => 'posts#destroy'  
  put '/posts/:post_id/' => 'posts#update'

  root 'posts#index'
end
```

이렇게 as로 별칭을 주면 `rake routes` 를 터미널에 쳤을 때 helper에 등록되게 됩니다. helper에는 edit_post_path라고 등록됩니다. 그럼 form helper에서 매우 편하게 사용가능합니다. Resource를 적는 부분에 그냥 `edit_post_path(넘겨줄 값)`이라고 적으면 되고, View helper 부분도 link_to의 resource에도 마찬가지입니다.

## resources

모든걸 합할 수 있습니다. 

```ruby
resources :posts
```

이 한줄이면 총 7가지가 생성됩니다.

| HTTP 메서드  | 경로               | 컨트롤러#액션        | 목적                         |
| --------- | ---------------- | -------------- | -------------------------- |
| GET       | /photos          | photos#index   | 모든 사진 목록을 표시               |
| GET       | /photos/new      | photos#new     | 사진을 1개 생성하기 위한 HTML 양식을 반환 |
| POST      | /photos          | photos#create  | 사진을 1개 생성                  |
| GET       | /photos/:id      | photos#show    | 특정 사진을 보여줌                 |
| GET       | /photos/:id/edit | photos#edit    | 사진 편집용의 HTML 양식을 반환        |
| PATCH/PUT | /photos/:id      | photos#update  | 특정 사진을 갱신                  |
| DELETE    | /photos/:id      | photos#destroy | 특정 사진을 삭제                  |

저 한줄이 이렇게나 많이 만들 수 있습니다. [관련내용](http://guides.rorlab.org/routing.html#restful%ED%95%9C-%EC%95%A1%EC%85%98%EC%9D%84-%EB%8D%94-%EC%B6%94%EA%B0%80%ED%95%98%EA%B8%B0)

가장 핵심은 이 친구들은 모두 posts_path, post_path, edit_post_path와 같이 helper를 가진다는 점입니다. 이 점이 매우 강력합니다. 왜냐면 erb에서 사용하기 때문입니다.

## form helper

```erb
<%= form_for @post do |f| %>
  <%= f.text_field :title, placeholder: '제목' %> <br>
  <%= f.text_field :content, placeholder: '내용'  %> <br>
  <%= f.submit "생성" %>
<% end %>
```

```erb
<%= link_to '수정하기', showUpdate_post_path("#{@post.id}") %> <br>
<%= link_to '삭제하기', post_path("#{@post.id}"),  method: 'delete', data: {confirm: "진짜 지우시겠습니까?"} %> <br>
<%= link_to '뒤로가기', :back %> <br>
```

와 같이 사용하면 됩니다. 매우 강력하게 사용할 수 있습니다.