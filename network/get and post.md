# Get과 Post

## 공통점

get 방식과 post방식은 HTTP 프로토콜을 이용하여 웹에서 정보를 요청하는 방식의 차이다. 

HTML문서에서 FORM 태그의 method 속성의 속성값으로 get이나 post를 적는다. 그러면 각각의 방식으로 request가 보내진다.



## 차이점

### GET

get방식은 보내고자하는 form 태그의, 하위 input태그의 값(value)을 `HTTP Request Message`의 Header 부분의 url에 담겨서 전송된다.  설명이 굉장히 길지만 쉽게 정의하자면 **요청된 데이터를 URL에 담아 보냄** 이다.

```jsp
<html>
  <head>
    <title>test페이지입니다.</title>
  </head>
  <body>
    <form method="get">
      검색할 이름을 입력해주세요.<input name="username"/>
      검색할 이메일을 입력해주세요.<input name="email"/>
      <button>
      제출
       </button>
    </form>
  </body>
</html>
```

이 경우 `http://localhost/test.html?username=홍길동&email=hitest@hi.com` 의 형식으로 요청을 보내게 된다. 이 URL은 브라우저 주소창에 보여지게 된다.

POST

post방식은 보내고자 하는 form 태그의, 하위 input태그의 값(value)을 `HTTP Request Message`의 Body 부분의 서버버퍼를 이용하여 데이터가 전달된다. **요청된 데이터를 임시 저장공간에 담아 보냄** 이 키워드다.

따라서 주소창에 보이지 않는다. visible과 invisibile이 가장 큰 차이점이다

## 특징 분석과 장단점

### GET

단어를 잘 살펴보자. get이다. 무엇을 가져오는 것이다. 즉 조회(select)의 기능의 성향을 가지고 있다. 따라서 서버에게 'A와 관련된 정보를 얻고 싶어요' 하고 요청할때는 서버에게 'A'를 전달해야하고, 이 때 get방식이 적합하다.

get 방식은 브라우저에서 caching 한다. 따라서 로컬에 caching 되어있던 정보를 가져오게 된다. 속도는 좀 더 빠르지만 inconsistency 문제가 발생할 가능성이 있다.

URL에 담기기 때문에 길이에 제한이있고, 주소창에서 볼 수 있기 때문에 보안적 결함이 있다.(물론 암호화가 되지 않은 통신은 두가지 모두 취약하다)

### POST

이 역시 단어를 살펴보면 post다. get과는 다르게 서버에 데이터를 넘겨 상태나 값을 변경(insert, update)하는 성향을 가지고 있다. 따라서 서버에게 '내 상태를 A로 바꿔주세요' 하고 요청할 때는 서버에게 'A'를 post방식으로 전달하는 것이 적합하다. 

post는 캐시를 사용하지 않기때문에 요청할 때마다 서버를 접속하게 된다. 따라서 실시간 정보를 표시하는데 적합하다.

post는 보이지 않는다. 그래서 get보다는 보안적 측면이 좀 더 우수하다. 예시로 비밀번호의 경우 post를 사용한다.

url을 사용하지 않기 때문에 길이와 크기에 제한이 없다. 그렇기에 파일 업로드와 같은 경우 post를 사용한다.



