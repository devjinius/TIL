# Devise

로그인과 관련된 gem이다.

`gem 'devise'`를 추가한 뒤 `rails g devise:install`을 해주자. 그런 뒤 모델 대신 devise를 만든다. `rails g devise User`라고 입력하자.

그런 뒤에 scaffold를 이용해서 Post를 만들어주자. ` rails g scaffold Post title:string content:text` 혹시 틀렸다면 `rails d scaffold Post`로 지우면 된다.

## 헬퍼

기본적으로 devise를 이용하면 헬퍼가 만들어집니다. 하나씩 배워봅시다.

`<% if user_signed_in	? %>`이라고하면 세션에 로그인 상태인지를 확인합니다.

### 언어

기본 세팅은 i18n을 이용합니다. 이는 internationalization의 줄임말로 devise-i18n을 gem으로 설치한 뒤 /config/application.rb에서 default_locale을 :ko로 바꾸면 한국어 세팅이 됩니다.

그런 뒤 추가적으로 커스터마이징을 한다면 `rails generate devise:views`이렇게 view파일을 직접 생성해서 바꿀 수 있습니다.

## Strong Parameters

form_for를 사용하게되면 `params[:post][:title]`과 같이 사용해야했습니다. 그 이유는 안에 hash를 보게되면 그렇게 생겨먹었기 때문이죠.

그런데 잘 생각해보면 이는 매우 편리합니다. `Post.create('title': '타이틀임', 'content': '가즈아!')`라고 하려면 그냥 `Post.create(params[:post])`만 하면 되기 때문입니다.

그런데 update의 경우는 다 바꿀 필요가 없이 일부만 바꾸고 싶을 수 있습니다. 또한 create의 경우에도 params에 id와 같은 값이 같이 넘어온다면 create시 오류가 발생할 가능성이 있습니다.  그래서 strong parameters를 이용하여 필터링을 거치게 됩니다. `params.require(:post).permit(:title, :content)`라고 사용하면 title과 content만 잘 받아오게 됩니다.

```ruby
# params = {"post" : {"title": "타이틀임", "content": "가즈아", "id": 5}}

params.require(:post) # {"title": "타이틀임", "content", "가즈아", "id": 5}
params.require(:post).permit(:title, :content) # {"title": "타이틀임", "content", "가즈아"}
```

이렇게 됩니다. require는 키를 버리고 반환하게되며, permit은 키를 포함하여 반환합니다

## cancancan & rolify

각각 권한부여와 등급부여입니다.