# URL 생략

우리가 주소창에 문자열을 적고 엔터를 누르면 request가 보내지고, response를 받는다. 그 전체의 단계 중 가장 첫 단추가 바로 브라우저의 URL 해독이다. 브라우전느 URL을 해독하여  request를 보내게 된다.

`http://www.lms.kyonggi.ac.kr/dir/file.html`의 경우  http는 프로토콜, 그 뒤는 서버명, /dir/file.html은 디렉토리/파일명으로 해석하게 된다. 여기서 우리는 /dir/file.html의 생략에 주목해보고자 한다. 

이것은 경로명이라고한다. 이름은 크게 중요하지 않고, 만일 파일명을 생략한다고 해보자.

`http://www.lms.kyonggi.ac.kr/dir/`

이 경우 /로 종료되었다. 이러면 index.html이나 default.htm 으로 접근하게 된다. 개발을 하며 당연하다고 생각 된 것이 바로 이 부분이다. 서버 설정으로 제어할 수 있다.

보통의 경우 `http://www.lms.kyonggi.ac.kr/`이 경우가 더 많았다. 이 역시 같은 방식으로 index.html을 찾아 가게 된다. 그러면 여기서 사용된 / 얘는 뭐하는 앨까?

바로 루트디렉토리다. 루트디렉토리 이하의 index.html을 찾게 된다.

그렇다면 `http://www.lms.kyonggi.ac.kr` 이렇게 쓰면 어떻게 될까? 이런짓을 해보질 않았는데 이는 인정되는 방법이다. 이 경우에도 역시 /index.html을 찾아가게 된다.



마지막으로 `http://www.lms.kyonggi.ac.kr/whatisthis` 이럴 경우 어떻게 될까? 확장자가 없어 파일도 아니고, /도 없어서 디렉토리도 아니다.  그러면 브라우저는 이를 어떻게 해석할까?

대다수의 브라우저는 이를 if else로 처리한다. 웹서버에 whatisthis라는 파일이 있다면 그 파일을 찾고, 아니면 이를 디렉토리로 취급하여 /whatisthis/index.html을 찾게 된다.

그 이유는 같은 이름을 가진 파일과 디렉토리 두 가지를 모두 만들 수 없기 때문이다.