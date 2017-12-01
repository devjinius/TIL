# 타입

자바스크립트는 타입 언어다. 누군가는 자바스크립트는 타입언어가 아니라고 하지만 엄연히 타입언어다. 다만 동적 타입개념이다!

* [7가지 내장 타입](#7가지-내장-타입)
  * [null](#null)
  * [undefined](#undefined)
  * [undefined와 void](#undefined와-void-연산자)
* [변수와 값의 타입](#변수와-값의-타입)
* [특수숫자](#특수-숫자)
  * [NaN](#nan)
  * [무한대](#무한)

## 7가지 내장 타입

자바스크립트는 타입 언어라고 했다. 7가지의 내장 타입은 다음과 같다

* null
* undefined
* boolean
* number
* string
* object
* symbol

여기에서 object를 제외하고는 primitves 들이다.

### null

null이란 친구는 조금 특별하다.

```javascript
typeof null; // "object"
typeof null === "object"; // true
```

`typeof null`의 결과값은 "object"다. null이 아니다. 왜 그러냐 하면 버그기 때문이다!!!

그렇다면 typeof로 타입이 null인 것을 검사하려면 어떻게 해야 할까?

```javascript
var a = null;
!a // true;

(!a && typeof a === "object");  // true
```

null은 falsy하기 때문에 `!a`가 true가 된다. 동시에 타입은 object기 때문에 이를 모두 검사해주어야 한다.

-----

### undefined

null과 조금은 다르게 undefined라는 친구 역시 특별하다.

#### null과 undefined

기본적으로 null과 undefined는 값이 없다라는 뜻이다. 이 '값이 없음'에서 null은 공(빈)값을 명시적으로 넣는다는 의미고 undefined는 말 그대로 값 자체가 없는거다.

헷갈리니 예제를 보자

```javascript
var a;
var b = null;

a; // undefined
b; // null

typeof a; // undefined
typeof b; // "object" (위에서 소개한 버그)
```

데이터베이스를 생각해보면 조금 더 명확하다. 테이블 안에 값이 null인 경우를 생각해보자. 원래 이 테이블안에 값이 있다는 것은 알지만 값이 현재 비었을 때 null을 사용한다.

undefined는 정의되지 않은 값이다. 즉 값을 초기화하지 않아 존재하지도 않는 값을 의미한다.

-----

#### undefined의 두가지 의미

undefined는 두가지 의미가 있다. '값이 없는것'과 '선언되지 않은 것'이다.

정확히는 no exist, is not defined(undeclared)라고 할 수 있겠다.  하지만 자바스크립트에서는 이 두 경우를 애매하게 표현한다. 따라서 매우 헷갈린다!

```javascript
var a;

a; // undefined
b; // ReferenceError: b is not defined
```

undefined와 is not defined는 결국 같은 뜻이 아닌가?? 이렇게 코딩하면 b의 경우 에러가 발생해 시스템에 문제가 생길 깃이다.

```javascript
var a;

typeof a; // undefined
typeof b; // undefined
```

아.... 정말 싫다.  typeof 연산결과의 경우 두 경우는 동일하게 처리된다. a와 b는 분명히 다른 존재지만 같은 결과를 반환한다. 

때문에 헷갈릴 수 있지만 이것 때문에 typeof는 safety guard라는 특징을 가진다.

스크립트의 경우 여러 파일들이 존재하고 같은 전역 네임스페이스를 공유하게 된다. 이 때 같은 결과를 반환하는 typeof가 유용해진다.

'디버그 모드'를 나타내는 전역변수 DEBUG가 있다고 하자.  debug.js 파일을 따로 빼고 전역스코프에 `var DEBUG = true;`라고 코딩했다. 이제 다른 파일에서 디버그 모드가 필요하다면 DEBUG가 true인지 확인하고 디버그 프로그램을 작동시킬 것이다. 이 때 매우 유용하다.

```javascript
// 이렇게하면 ReferenceError가 발생할 것이다.
if (DEBUG){
  console.log("디버깅을 시작합니다.");
}

// 이렇게 하면 선언되지 않은 변수를 안전하게 검사할 수 있다.
if (typeof DEBUG !== "undefined"){
  console.log("디버깅을 시작합니다.");
}
```

파일을 애플리케이션 단위로 나누어 코딩하는 자바스크립트의 특성상 이는 매우 유용한 에러방지 코드일 것이다.

typeof를 사용하지 않고 체크하는 방법이 또 한가지 있다. 바로 전역 객체를 이용하는 것이다. 브라우저의 경우 window객체가 될 수 있다. 모든 변수는 전역객체의 프로퍼티라는 점을 이용하는 것이다.

```javascript
if(window.DEBUG){
 console.log("디버깅을 시작합니다.") ;
}
```

하지만 이런 방식은 node.js와 같이 전역객체가 없는 환경이라면 불가능 하다. 

#### undefined만 할 수 있는 특별한 짓

전역 스코프에서 undefined라는 예약어(변수)에 값을 할당할 수 있다.

```java
undefined = 2;
```

이렇기 때문에 undefined란 이름의 지역변수를 만들 수 있는데 제발 하지 말자

#### undefined와 void 연산자

void는 자바에서 흔히 보던 문법이다. 자바스크립트에서 void는 무효화라는 뜻이다. 값이 무효화되면 undefined로 간다. 기존의 값은 건드리지 않으며 연산 후 값은 복구할 수 없다.

```javascript
var a = 42;
console.log(void a, a) // undefined 42
```

따라서 undefined라는 값을 얻고 싶다면 `void 0`처럼 쓰면 된다. 그럼 그냥 undefined로 쓰면 되지 않을까? 왜 굳이 이런걸 쓰나 싶은 생각이 든다. 그 이유는 어떤 표현식의 연산 결괏값이 없다(undefined)라는 것을 명확히 하는데 쓰인다.

## 변수와 값의 타입

동적 언어인 자바스크립트는 타입이 있어서 타입 언어다. 하지만 정적 언어와는 다른점이 있다. 값에는 타입이 있지만 변수에는 타입이 없다.

`42`라는 숫자의 타입은 number다. `var a = 42`의 경우 a는 타입이 없고 42는 number타입이다. 

```javascript
var a = 42;
typeof a; //number
```

변수는 타입이 없다고 했다. 그렇기 때문에  위의 경우처럼 a의 타입을 묻는다면  'a라는 변수에 들어있는 값의 타입이 뭐니?' 라고 묻는 것이다

*그렇다면 typeof 연산 결과의 타입은 무엇일까?*

`typeof typeof 42; // "string"` 어느정도 예상했지만 역시 String이다.

## 특수 숫자

### NaN

Not a Number다. 그렇지만 Not a Number는 명확한 표현이 아니며 Invalid Number가 더 정확한 표현이다. 생기는 경우는 다음과 같다.

```javascript
var a = 2 / "foo"; // NaN

// 그런데 말이다.
typeof a === "number"; // true
```

a는 숫자가 아니다. 그런데 숫자랑 타입을 비교하니 참이다!

```javascript
a == NaN; // false
a === NaN; // false
```

? 이게뭘까? NaN 을 비교하면 false다. `NaN!=NaN`이다. 즉 자신과도 같지 않은 이상한 값이다. 

그래서 NaN임을 확인할 때는 `isNaN(a) // true`의 방식을 사용한다. 근데 이것도 올바른 방법은 아니다. isNaN은 이거 숫자가 아니야?라고 묻는거기 때문에 `isNaN("hi") // true`다. 문자열을 넣어도 true다.

결국 해결사로 ES6부터 *`Number.isNaN()`*라는 친구가 나왔다!!! **여기서 얻어가는 제일 중요한 사실은 isNaN()이라는 코드를 사용하면 시한폭탄을 설치한 셈이된다** 

**Number.isNaN()**과 새로나온 Object.is(a, NaN)을 사용하자

### 무한

별로 쓸일은 없지만 간단하게만 정리해보자

```javascript
var a = 1/0;   // Infinity
var b = -1/0   // -Infinity
// 다른 언어에서는 Divided by Zero에러가 날 것!
```

음의 무한대와 양의 무한대가 따로 있다. 그럼 무한을 무한으로 나누면 어떻게 될까? 결과는 NaN이다.

*참고문헌*

* 카일 심슨,  YOU DON`T KNOW JS - 타입과 문법 스코프와 클로저, 한빛미디어, 2017