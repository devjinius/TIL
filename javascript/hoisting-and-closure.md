# 호스팅과 클로저

두 개념이 연관이 있어 따로 묶은 것은 아니며 모두 scope의 하위 제목들이다.  다만 [scope](./scope.md)의 내용이 길어 분리해서 작성한다.

## 호이스팅

선언문이 스코프의 어디에 있는지에 따라 변수가 추가되는 과정에 차이가 있다.

```javascript
a = 2;
var a;
console.log(a);
```

정상적인 논리적 사고에 의하면 console.log()의 출력값이 `undefined`가 옳다. 그러나 자바스크립트에서의 출력 결과는 2이다.

```javascript
console.log(a);
var a = 2;
```

이 경우는 그럼 2일까? 아니면 ReferenceError가 나올까? 정답은 undefined이다.

### 기본 개념

[scope](./scope.md)의 컴파일러 부분을 보게되면 알겠지만, 엔진이 코드를 인터프리팅하기 전에 컴파일을 한다고 했었다. 컴파일레이션 단계중에 모든 선언문을 찾아 스코프에 연결해주는 과정이 있었다. 

다시보자면 `var a = 2;`라는 구문은 `var a;`와  `a = 2;`의 두 구문으로 보게 된다.

첫째 구문은 선언문으로 컴파일레이션 단계에서 처리된다. 둘째 구문은 대입문으로 실행 단계까지 내버려둔다.

```javascript
console.log(a);
var a = 2;


var a;
console.log(a);
a = 2;
```

따라서 위의 코드는 아래의 코드와 같게 된다. 이 과정에서 변수 선언만 맨 위로 끌어올려졌다. 결론부터 얘기하자면 변수와 함수의 선언문은 코드의 꼭대기로 끌어올려지고, 이를 호이스팅이라고 한다. 

호이스팅은 스코프별로 작동한다. 이 점도 중요하다. 

```javascript
function foo() {
  var a;
  console.log(a);
}
foo();
-----
foo(); // TypeError

var foo = function bar() {
}
```

위의 코드는 잘 실행되지만 아래 코드는 실행되지 않는다.  그 이유는 변수 foo는 글로벌 스코프에 붙어 선언문이 실행되고 foo호출은 가능하여 ReferenceError가 발생하지 않는다. 다만 foo()를 호출하니 TypeError가 발생한다. 함수로서 선언된 것이 아니기 때문이다.

또 기억할 것은 함수 표현식이 이름을 가진다고 해도 그 이름은 해당 스코프에서 찾을 수 없다는 점이다. bar를 잘 보자

```javascript
foo();  // TypeError
bar();  // ReferenceError

var foo = function bar() {
  
}
```

bar는 선언되지 않는다.

### 함수가 먼저다

그렇다면 함수가 먼절까 변수가 먼절까? 제목을 보면 알겠지만 함수가 먼저다.

```javascript
foo();   // 1
var foo;

function foo() {
  console.log(1);
}

foo = function() {
  console.log(2);
}
```

이 경우 1이 출력되고, 만약 마지막줄에 foo()를 다시 호출한다면 2가 출력된다. 

```javascript
function foo() {
  console.log(1);
}

foo();   // 1

foo = function() {
  console.log(2);
}
```

이런식으로 진행이 된다. 따라서 `var foo;`구문은 무시되어 버린다. `var foo;`가 함수 선언문보다 위지만 함수가 그걸 덮어버린다.

이게 중요한 것인가 싶지만 이런 경우가 발생할 수 있다.

```javascript
foo(); // b

var a = true;
if(a){
  function foo() {console.log(a)};
} else {
  function foo() {console.log(b)};
}
```

블록 스코프가 적용되지 않기 때문에 생각대로 작동하지 않을 수 있다.