# 강제변환

자바스크립트에서 type conversion or type coercion이라고하는 타입변환을 설명해보자. 

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

### ToString

이 연산은 매우 간단하다. 문자열이 아닌 값을 문자열로 바꿔준다. ex) `42 -> "42", null -> "null"` 이런식으로 말이다. 이 친구는 따로 지정하지 않으면 `Object.prototype.toString()`메서드를 실행한다.자세히 말하자면 toPrimitive가 먼저 실행된다고 한다. 그런데 그 객체가 toString()이라는 이름을 가진 메서드를 가졌다면 그 메서드가 실행된다. 

배열의 경우 

```javascript
var a = [1,2,3];
a.toString(); // "1,2,3"
```

으로 바뀌게 된다. 이는 명시적 방법이다.

### JSON.stringify

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