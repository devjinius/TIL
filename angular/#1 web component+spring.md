## 웹 컴포넌트와 스프링

### 웹 컴포넌트 표준

* custom elements
* template
* html import 
* shadow dom

이렇게 네가지로 구성된다. 다음에 더 깊숙하게 공부하겠지만 우선 전체적인 흐름을 익히기 위해서 웹 컴포넌트에 대하여 공부했다.

```typescript
//index.html
<html>
  <head>
  </head>
  <body>
  	<app-root></app-root>
  </body>
</html>


//component.ts
import { Component } from '@angular/core';

@Component({
	selector: 'app-root',           // custom tag
	templateUrl: './app.component.html',           // template
	styleUrls: ['./app.component.css'] 
})
export class AppComponent {
	title = 'app123';                                      // data binding
}

// app.components.html
<div style="text-align:center">
  <h1>
    Welcome to {{title}}!
  </h1>
</div>
```

이게 기본적인 앵귤러의 페이지다. 앵귤러는 싱글 페이지 기반이고, 컴포넌트들을 묶어서 페이지를 만들게 된다. index.html의 경우 app-root라는 **custom-tag**에 의해 하위 돔이 숨겨지게 된다. 이를 **shadow dom**이라고 한다. 이 커스텀 태그는 컴포넌트의 selector이며, 그 컴포넌트에 가면 **template**을 정할 수 있다. 이 템플릿을 현재 **html import**를 이용하여 지정해줬다. 하지만 직접 태그를 사용해도 무관하다. css 역시 컴포넌트 단위로 나뉜다. 또한 {{title}}이 컴포넌트의 title과 **data-binding**되어 화면에서는 title이 아닌 app123이 나오게 된다.

어떻게 보면 쉽고 어떻게 보면 간단하지만 최대한 쉬운 예제로 간단하게 설명해서 그렇다. 쉬운 내용은 아니다.

### Angular + Spring

우리 스터디는 앵귤러와 스프링을 같이 사용하기로 했다. 이 말은 서버사이드는 기존 코딩하던 방식인 자바를 이용하여 개발하고, 클라이언트 사이드는 앵귤러를 사용하기로 한 것. 

기존의 고전적인 스프링 방식의 프로젝트의 경우 Jstl를 이용하여 값을 뿌려주고 Jquery를 이용하여 돔을 직접 조작하여 개발하였다. 하지만 이번 프로젝트의 목표는 기존에 사용하여 익숙하던 자바를 이용하되, 클라이언트 사이드는 새로운 방식인 앵귤러를 이용하여 컴포넌트 기반의 개발을 도전했다.

이에 도입한 개념이 두개의 서버다. 단순 처리인 web server의 경우 node서버를 이용하고, db처리를 해야하는 was server의 경우 노드단에서 포팅하여 apache 서버를 이용해 처리하기로 하였다. sql 쿼리 처리의 퍼포먼스가 중요한 것을 생각하면 서버가 두개인 것의 퍼포먼스는 큰 차이가 없을 것으로 생각한다.

이 경우 예상되는 제한사항을 알아보았다.

* Model Map 사용불가.
  * 기본적으로 model and view 방식인 spring인데 이를 전혀 사용하지 못한다.
  * 따라서 return으로 node서버에게 VO를 보내고, VO를 받아와 노드에서 view처리를 할 생각이다.
* Spring Form 태그 사용불가.
  * Spring에서 제공하는 다양한 라이브러리들을 사용하지 못한다.
  * 따라서 템플릿 유효성 검사를 생각해봐야 한다.
* Spring Message 태그 사용 불가
  * 이 역시 위와 같은 제한사항이다.
  * 관리의 측면에서 메세지태그는 굉장히 파워풀한데 이를 사용하지 못한다.
  * 앵귤러에서는 xml기반의 .xlf파일 라이브러리를 지원하는데 이는 다양한 버전을 xml파일로 나누고 서버를 올릴 때 모두 컴파일 시킨다는 점이 단점이다. 

