# Native

* [Native란](#native란)
* [내부 클래스](#내부-클래스)
* [Boxing](#boxing)
* [Unboxing](#unboxing)
* [배열](#배열)
* [기타](#기타-네이티브)
* [Native Prototype](#네이티브-프로토타입)

### Native란

네이티브의 기본 정의는 특정환경(클라이언트의 프로그램 ex)browser)에 종속되지 않은 ECMA script의 내장객체를 말한다. 예를들어 Object, Math, Function, Array, Window, Button이 있다면 이 중 Window와 Button은 네이티브가 아니다.

네이티브는 사실 내장함수로 String(), Number(), Array() 이런걸 말한다.

new String()과 같은 자바의 생성자가 스크립트의 네이티브와 모양이 비슷하다.  자바의 Wrapper를 생각하면 된다

```javascript
var a = new String("abc");

typeof a;  // "Object"  String이 아니다!!

a instanceof String; // true
```

자바의 wrapper와 마찬가지로 String()으로 만들어낸 객체는 String의 원시값(스칼라)는 아니며 객체 래퍼다. Object의 하위 타입에 가깝다.

###내부 클래스

[[Class]] 다.  이건 Object타입이면 내부 프로퍼티다. 직접 접근은 불가능하고 호출하여 값을 확인할 수는 있다.

```javascript
Object.prototype.toString.call(new String("abc"));  // "[object String]"
```

이 내부 클래스는 Array, RegExp, null, undefined 등등도 있다. 그렇다면 원시값은 어떨까?

```javascript
Object.prototype.toString.call("abc");  // "[object String]"
Object.prototype.toString.call(42);  // "[object Number]"
Object.prototype.toString.call(true);  // "[object Boolean]"
```

네이티브로 Object를 만들지는 않았지만 내부 클래스에 접근이 가능하다. 이것은 왜 그런걸까?? 

그 이유는 Boxing때문이다.

###Boxing

박싱은 래핑과 같은의미로 사용된다. 원시값을 객체 래퍼로 감싸는(래핑) 것을 박싱이라고 하는데 자바스크립트가 알아서 자동 박싱한다. 그래서 아래와 같은 경우가 자동으로 사용 가능하다.

```java
var a = "abc";

a.length; // 3
a.toUpperCase(); // "ABC"
```

원시값 "abc"는 프로퍼티나 메서드가 없다. 그렇지만 위 예시와 같이 사용했는데 그 이유가 바로 자동 박싱이다.

이렇게 자바스크립트는 auto-boxing을 하게 되는데 그렇다면 개발자가 코딩을 할 때 이를 신경쓰고 `new Number(123);`과 같은 작업을 해야하나?

답은 아니다. 이렇게 하는 것을 Pre-Optimizie라고 한다. 코더가 최적화되지 않은 방향으로 하게 될 수 있고, 이미 엔진이 알아서 박싱을 잘 해주기 때문에 놔두는것이 더 효율적이다.

```javascript
var a = new Boolean(fasle);

if(!a){
  console.log("hi");  // 실행안됌
}
```

그 이유는 감싼 객체가 true기 때문이다. 이런 오류를 범할 수 있으므로 사용하지 않는 것이 좋으며 정 하고 싶다면 `Object(true)`의 형식이 더 좋다.

### Unboxing

박싱된 객체 래퍼의 원시값을 얻을 때 사용한다. valueOf()를 이용한다. `a.valueOf()`처럼 사용한다. 이는 강제변환에서 더욱 깊게 얘기 할 것이니 넘어가자

### 배열

기본적으로 리터럴 형태로 배열, 객체, 함수등을 생성한다. 어떻게 생성하던지 사실 모든 값은 래핑되게 된다.(자동 박싱, 자동 언박싱)

그런데 배열의 경우 조금 독특하다. 왜냐하면 `var a = new Array(1,2,3)`으로 그냥 만들어도 되지만 `var a = new Array(2)`가 되면 문제가 생기기 때문이다. presize기능인데 저 a는 [,]다. 빈 slot이 두개있는 배열이라는 의미다. 크롬의 경우 [empty* 2]다. 브라우저별로 다르니 저렇게 쓰지말자 그냥.  이 경우에 문제가 되는게 join과 같은 경우 그냥 빈 값이라 상관이 없는데 map의 경우 값이 없으면 가져오지 않기 때문에 버그가 발생한다 **쓰지말자**

### 기타 네이티브

기타 취급해서 미안하지만 RegExp와 Date, Error네이티브다

#### RegExp

정규표현식의 경우 리터럴형식이 좋다. 구문도 좋고 성능상의 이점도 있다. 엔진이 실행 전에 미리 컴파일 한 후 캐시하기 때문! 

```javascript
var h = new RegExp("^a*b+", "g");   // 패턴, 플래그형식
```

#### Date

얘는 리터럴 형식이 없으므로 유용하다. `Date.now()`를 자주 사용한다.

#### Error

`throw new Error("값이 없어요!")` 이렇게 자주 사용한다. 이것도 리터럴이 없으므로 유용하다.

### 네이티브 프로토타입

내장 네이티브 생성자는 prototype 객체를 가진다. 이 객체에는 해당 객체의 하위 타입별로 고유한 로직이 있다.  예를들어 String#indexOf()나 String#charAt()과 같은거다. (*#은 String.prototype.indexOf()를 줄여서 사용하는 것*) 참고로 그 객체를 직접 수정할 수는 없고 새 객체를 생성한다.

네이티브 프로토타입 자체를 변경할 수도 있는데 이는 좋은 방법은 아니다. `Array.prototype.push(1,2,3);` 이런식으로 말이다.