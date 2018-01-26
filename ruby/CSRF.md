# CSRF

Cross Site Request Forgery입니다. 한글로는 사이트간 요청위조인데요.

예를들어 action의 링크를 이용해서 다른사이트에서 CRUD기능을 실행한다거나 하는 등의 방식입니다. 이를 applicationController에서 exception을 이용해서 막게됩니다.

```ruby
class ApplicationController < ActionController::Base
  protect_from_forgery with: :exception
end
```

이 코드를 넣게되면 문제가 생깁니다. 정상적인 POST 접근도 막게 되기 때문입니다. 에러를 보게되면 `ActionController::InvalidAuthenticityToken`라고 나옵니다. 인증토큰이 유효하지 않은것입니다. 이제 인증토큰을 사용해서 접근해야 합니다.

구글에 rails form helper라고 검색하면 관련 문서가 나옵니다. 

input type hidden을 이용해서 인코딩 + 인증토큰을 알아서 처리해주게 됩니다.

```erb
<form action="/posts/create" method="post">
  <span class="input-group-text">제목</span>
  <input type="text" class="form-control col-sm-3" name="input_title" placeholder="title">
  <span class="input-group-text">내용</span>
  <input type="text" class="form-control col-sm-3" name="input_content" placeholder="content">
  <input type="submit">
</form>

<%= form_tag("/posts/create", method: "post") do %>
  <span class="input-group-text">제목</span>
  <%= text_field_tag 'input_title', nil,  placeholder: 'title', class: 'form-control col-sm-3'%>
  <span class="input-group-text">내용</span>
  <%= text_field_tag 'input_content', nil,  placeholder: 'content', class: 'form-control col-sm-3'%>
  <%= submit_tag "제출" %>
<% end %>
```

위와 아래는 모두 같은 코드입니다. 자동으로 폼으로 바꾸어주기 때문입니다. 다만 위코드는 실행이 안되고 아래코드는 실행이됩니다.

간단하게 설명을하자면 form태그는 경로명, 나머지속성입니다. text_field_tag는 name, value, 나머지 속성입니다. 모두 이런식이니 필요할때마다 꺼내서 사용하도록 합시다.