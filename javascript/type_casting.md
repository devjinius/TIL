# 강제변환

자바스크립트에서 type conversion or type coercion이라고하는 타입변환을 설명해보자.

* [ Casting과 Coercion](#casting-And-Coercion)
* [추상연산](#추상연산)
  * [ToString](#toString)
  * [JSON.stringify](#json.stringify)
  * [ToNumber](#toNumber)
  * [ToBoolean](#toBoolean)
* [명시적 강제변환](#명시적-강제변환)

## Casting And Coercion

흔히 우리가 알고 있는 type casting은 타입을 명시적으로 바꿀때를 이야기한다. coercion은 암시적으로 규칙에 의해 변경될 때를 이야기 하고 이를 강제변환이라고 한다.

두 용어 중 전자는 정적 타입 언어에서 컴파일 시점에 발생하고, 후자는 동적 타입 언어에서 런타임 시점에 발생한다. 자바스크립트는 명백한 타입언어다. 다만 동적타입언어다. 동적 타입언어의 가장 큰 단점은 암시적인 강제변환이 일어난다는 것이며 이것으로 인해 side effect가 발생할 수 있다. 따라서 타입에 대해 잘 공부해야 한다.

```javascript
var a = 42; 	 // number
var b = a + "";  // string (암시적 변환)
var c = String(a); // string (명시적 변환)
```

`a + ""`라는 코드를 의도하여 문자열 변환을 했다고 명시적이라 반문할 수 있다. 그러나 타입 변환에서 가장 중요한 것은 대부분의 개발자들이 나의 코드를 봤을 때 모두가 나의 코드를 명시적으로 받아들인다면 이는 명시적, 암시적이라면 암시적이다. 개발은 나 혼자 하지 않는다. 팀 단위 업무에서 모두가 내가 짠 코드를 봤을 때 명시적이어야 한다.

## 추상연산

### toString

이 연산은 매우 간단하다. 문자열이 아닌 값을 문자열로 바꿔준다. ex) `42 -> "42", null -> "null"` 이런식으로 말이다. 이 친구는 따로 지정하지 않으면 `Object.prototype.toString()`메서드를 실행한다.자세히 말하자면 toPrimitive가 먼저 실행된다고 한다. 그런데 그 객체가 toString()이라는 이름을 가진 메서드를 가졌다면 그 메서드가 실행된다. 

배열의 경우 

```javascript
var a = [1,2,3];
a.toString(); // "1,2,3"
```

으로 바뀌게 된다. 이는 명시적 방법이다.

### json.stringify

이거 자바 프로젝트때 굉장히 많이 썼었다. Ajax로 값을 넘길 때 많이 사용했는데 드디어 공부하게 되었다. 어떤 값을 문자열로 직렬화 하게 된다. 

```javascript
JSON.stringify({
  a : "hi",
  b : 123,
  c : null
}) // "{"a":"hi","b":123,"c":null}" 
```

우선 이런식이다. json을 string화 시킨다.

JSON-Safe Value는 모두 JSON.stringify로 문자열화가 가능하다

```javascript
JSON.stringify(42); // "42"
JSON.stringify("42"); // ""42""
JSON.stringify(null); // "null"
JSON.stringify(true);  // "true"
```

JSON-Safe Value는 반대를 기억하는게 더 쉽다. undefined, 함수, 심벌, 환형참조객체 가 JSON으로 표현 불가능하다.인자에 이런 값이 포함되면 null로 바꿔버리고, 객체 프로퍼티에 있다면 지워버린다.

```javascript
JSON.stringify(undefined); // undefined
JSON.stringify(function(){}); // undefined

JSON.stringify(
  [1, function(){}, 4]
); // "[1, null, null, 4]"

JSON.stringify({
  a:2, b:function(){}
});  // "{"a": 2}"
```

이렇게 된다. 혹시 객체에 toJSON()메서드가 있다면 메서드를 자동으로 실행하게 된다.

#### toJSON의 의미

toJSON메서드를 객체에 넣으면 문자열로 바뀌지는 않는다. 정확히는 문자열화 하기 좋은 JSON-Safe Value로만 변환해서 반환하고, 그 뒤에 JSON.stringify()가 실제 문자열로 바꾸게 된다. 

```javascript
var a = {
  val : [1,2,3],
  
  toJSON: function() {
    return this.val.slice(1);
  }
}

var b = {
  val : [1,2,3],
  
  toJSON: function() {
    return "[" + this.val.slice(1); + "]";
  }
}
```

a는 맞고 b는 틀렸다. 

### toNumber

숫자가 아닌 값을 숫자로 변환한다. `"42" -> 42`로 바꾸는거는 쉽게 생각할 수 있다만 `undefined -> NaN` 과 같은 것은 쉽게 생각 못할 수도 있다.  저번에도 얘기했듯, [NaN](./type.md#NaN)은 숫자다.

- true = 1
- false = 0
- undefined = NaN
- null = 0
- 객체의 경우 원시값으로 변환 후 결과값을 ToNumber()를 실행하여 변환한다
  - 이 때 valueOf메서드가 구현이 되어있다면 이 것을 사용한다.

### toBoolean

흔히 1은 true, 0은 false라고 여긴다. 하지만 엄연히 다르다. 숫자는 숫자고 불리언은 불리언이다.

#### Falsy값

이 책에서는 Falsy라는 친구를 사용한다. 이는 불리언으로 강제 변환 시 false가 되는 값이다. 

* undefined
* null
* false
* +0, -0, NaN
* ""

는 모두 Falsy다. 나머지는 모두 true를 반환한다. 이 값만 외우면 된다. 

#### Falsy객체

객체는 모두 Truthy하다. 그런데 falsy object는 무엇일까?

```javascript
var a = new Boolean(false);
var b = new Number(0);
var c = new String("");
```

세 값은 모두 true다. 그 이유는 객체로 감쌌기 때문이다. `typeof(a)`는 object다. 위에 나온 5가지 경우를 제외하고는 모두 참이다. 그렇다면 falsy 객체는 무엇인가?

겉보기엔 평범한 객체지만 불리언으로 강제 변환하면 false가 되는 애들이다. 모든 객체는 강제 변환 시 true지만 내부적으로 false로 만든 애들이 있다. 바로 document.all이다.  이 특별한 친구는 비표준이고, 문법에 맞지않는 예외라고 보면 된다.

생겨난 배경은 IE기반의 브라우저에서 많이 사용했던 비표준 코딩인데 후에 레거시로 남게되었고 이를 돌아가게 하려고 true로 두었다. `if(document.all){}` 이런식으로 사용하는 낡은 코드들이 잘 돌아가게 하기 위해 남겨진 버그다.

이것을 제외하고 모두 true를 반환한다.

```javascript
var a = "false";
var b = "0";
var c = [];
var d = {};
var e = function() {};
```

모두 true를 반환하게 된다.

## 명시적 강제변환

명시적 강제변환은 개발자들이 흔히 사용하는 변환이다. 타입 변환 코드의 의미가 명확하고 추론할 필요없이 단번에 이해 가능한 코드다. 

### 문자열과 숫자

String() 과 Number()를 이용한다. new라는 키워드가 붙지 않으므로 객체 래퍼를 생성하는 것은 아니다. 자바나 C에서 `(int)x`라던가 `int(x)`와 같은 것이 명시적 변환이다. 자바스크립트에서는 `String(x)`와 같이 사용한다. 이 외에도 또 있다

```javascript
var a = 42;
var b = a.toString();

var c = "3.14";
var d = +c;

b; // 42
d; // 3.14
```

`a.toString()`은 앞에서 배웠듯 박싱과정을 거친다. 따라서 명시적인 타입변환이지만 객체로의 변환 과정이 암시적이다. 명시적으로 암시적인 작동이다. 그렇지만 보기에 명시적이기에 명시적 변환이라고 한다.

+는 단항연산자다. 우리가 흔히 알고있는 +는 a와 b를 더하는 binary operator인데, 여기서 사용된 것은 unary다. 따라서 피 연산자가 하나다. 이는 숫자로 명시적 변환을 하는 연산자다. 오픈소스 자바스크립트 커뮤니티에서 +를 명시적 변환으로 대부분 인정하기에 명시적으로 하자. 하지만 +는 헷갈리는 경우가 발생할 수 있다.

```javascript
var c = "3.14";
var d = 5+ +c;
d; // 8.14
```

-의 경우도 변환이 가능한데 - -로 공백을 두어 변환해야 원래 값이 나온다. -- 붙이게 되면 증감연산자가 된다.  비록 명시적 변환이지만 사용을 자제하자 너무 헷갈린다. +=과도 비슷하기 때문이다.

### 날짜와 숫자

날짜 값을 타임스탬프로 (밀리초단위의 number) 변환할 때 필요하다. 여기는 간단하게 권장되는 사용법만 적고 넘어가자

현재 시간은 Date.now()로, 특정 시간은 new Date.getTime()으로 사용한다.

### 문자열 파싱과 숫자

아래와 같은 경우가 파싱으로 변환하는 경우다.

```javascript
var a = "42";
var b = "42px";

Number(a);    // 42
parseInt(a);  // 42

Number(b);    // NaN
parseInt(b);  // 42
```

파싱의 경우 비 숫자형 문자도 변환이 가능하다. 좌 -> 우 방향으로 읽어가며 숫자가 아닌 문자를 만나면 멈춘다. 메커니즘은 간단하다만 주의할 사항이 있다.

parseInt()는 파라미터로 문자열을 받는다. 숫자를 넣을 생각조차 하지말자... 숫자 뿐만아니라 불리언이나 함수, 배열 등도 마찬가지다. string을 바꿔주는 친구다.

혹시라도 넣는 경우 어떻게 작동하는지 보자.

1. 인자가 비문자열일 경우 먼저 문자열로 강제변환한다. 
2. 이 경우 암시적 강제변환이 발생한다.
3. 그리고 나서 parseInt()가 작동한다.

예를들어 `parseInt(1/0)`의 경우 비문자열 인자를 받았기 때문에 parseInt(String(1/0))가 된다. 따라서 Infinity를 반환하게 된다.

### 불리언 변환

불리언 변환의 명시적인 방법은 Boolean()이다. 그런데 이건 잘 사용하지 않는 편이다.

! 부정단항 연산자는 자주 사용한다. 다만 true와 false가 뒤바뀌어 나오기때문에 !!를 사용한다.

```javascript
var a = "0";
var b = "";
var c = {};
var d = null;

!!a; // true
!!b; // false
!!c; // true
!!d; // false

Boolean(a); // true
Boolean(b); // false
Boolean(c); // true
Boolean(d); // false
```

if문 안에 두가지 경우를 사용하지 않으면 암시적 변환이 일어난다.

```javascript
var a = 42;
var b = a ? true : false;
```

이런 삼항 연산자를 자주 사용하는데 이 경우 전체적으로 보면 명시적이다. a에 따라 b가 결정되는 전체 과정은 명시적이나 결국 a가 boolean으로 변환되기 때문에 명시적으로 암시적인 코드다. 