# lambda

함수형 언어의 기능을 갖추게 하는 기능이다.

람다식이란 함수를 간략하면서도 명확한 식으로 표현할 수 있게 해주는 식! 이다.

메서드의 이름과 반환값을 없애 익명함수를 만드는 것이다.

```java
int[] arr = new int[5];
Arrays.setAll(arr, () -> (int)(Math.random*5)+1);
// 이렇게 () -> 생긴 친구를 람다식이라 한다.
int method() {
  return (int)(Math.random()*5) + 1;
}
// 하는일은 이 메서드와 같다.
```

람다의 특징으로는 메서드의 매개변수로 전달되어지는 것이 가능하다. 또 메서드가 반환 될 수도 있다. 즉 메서드를 변수처럼 다루는 것인데, 그 이유는 람다가 객체기 때문이다. 람다식으로 표현하면 메서드도 객체가 된다. 객체기 때문에 파라미터, 반환값으로도 가능한 것이다.

### 람다식으로 변경과 생략

```java
int max(int a, int b){
  return a > b ? a : b;
}//이러한 메서드를

(a, b) -> a > b ? a : b // 이렇게 변경하면 된다.
```

반환값이 있는 메서드의 경우 식으로 return을 대신한다.  return'문'이 아니기 때문에 세미콜론은 붙이지 않는다.

*함수형 프로그래밍의 궁극?점은 반환값이 없는 메서드를 최소화 하는 것이다.*

매개변수의 경우도 추론이 가능한 경우 생략하고 반환타입도 마찬가지로 생략할 수 있다.

매개변수가 하나면 괄호도 생략할 수 있다. 단 타입을 적으면 괄호가 필요하다.

{}안의 문장이 하나면 생략 가능하다.

### 함수형 인터페이스

람다는 메서드 같지만 사실은 함수형 인터페이스의 객체다. 이 인터페이스에는 하나의 추상메서드만 존재할 수 있고 그게바로 람다식이다. 우리가 사용할 때 혹은 사용하기 전에 그 메서드를 () -> 이러한 표현으로 구현해주는 것이다. 

인터페이스의 객체니까 참조변수에 저장할 수 도 있을 것이다. 그렇다면 이 참조변수의 타입은 어떻게 될까? 

당연히 람다식과 동등한 메서드가 정의된 클래스나 인터페이스가 되어야 한다.

```java
@FunctionalInterface //함수형 인터페이스
interface MyFunction{
  public abstract int max(int a, int b);
}

psvm{
  MyFunction f = new MyFunction(){
    public int max(int a, int b){
      return a > b ? a : b;
    }
  }
}

// 이렇게 객체를 생성하여 참조변수에 저장하는 것이다. 이를 간단하게 표현하면 아래와 같다.

psvm{
  My function f = (a, b) -> a > b ? a : b
  int big = f.big(5, 3);
}
```

##### 매개변수와 반환타입

만일 함수의 매개변수가 함수형 인터페이스라면 메서드를 호출할 때 람다식을 참조하는 참조변수로 지정해주어야 하거나 람다식을 매개변수로 넘겨야 한다.

`void method(Myfunction f){}` 일경우 `method(Myfunction myf)`이거나 `method( () -> {} )` 이렇게 말이다. 사실 당연한 거다.

또 반환타입이 함수형 인터페이스라면 람다식을 참조하는 참조변수거나 람다식이 직접 반환된다.

다시 말하지만 당연한거다. 메서드를 매개변수로 주고 반환 받는 것 처럼 보이지만 람다는 객체다. 그러기에 자연스러워야 한다.

### 타입과 형변환

람다는 익명 객체다. 따라서 타입이 없다. 정확히는 타입은 있는데 컴파일러가 임의로 타입 이름을 정하기 때문에 알 수 없어서 그냥 없다고 보자.

정리하자면 람다는 객체이지만 타입이 없는 객체인 것이다. 따라서 Object타입으로 형변환 할 수 없다. 모든 타입은 Object 아래에 있는데 람다는 타입이 없기에 Object로 안된다. 람다는 오직 함수형 인터페이스로만 형변환이 가능하다. `MyFunction f = (MyFuction) () -> {}` 이렇게 말이다. 물론 생략 가능하다.

굳이 Object로 바꾸고 싶다면 먼저 함수형 인터페이스로 변환하고 난 뒤에 가능하다. 

`String str = ((Object)(MyFunction)(() -> {})).toString;` 이렇게 말이다.

### 람다의 지역변수

**참고로 람다식 내에서 람다가 참조하는 지역변수들은 final이 자동으로 붙게 된다.**

이는 불변(immortal)의 개념때문이다. 위에서 반환값이 없는 메소드를 최소화 한다고 했다. 그 이유가 불변과 관련되어 있다.

반환값이 없는 메서드는 다른 메서드와 충돌 가능성이 있다. 값이 변하는 변수도 마찬가지다. 애초에 반환하는 메서드만 만들고 상수만 이용한다면 사이드 이펙트의 가능성이 비교할 수 없을 정도로 낮아진다. 이와 관련해서는 *나는프로그래머다* 1권을 참고하자. 임백준 저자님께서 잘 설명 해주셨다. 

함수형 프로그래밍의 핵심은 사이드 이펙트를 줄일 수 있단점이라고 생각한다. 따라서 불변과 참조투명성을 보장하는 코딩이 수반되어야 진정한 함수형 프로그래밍이라고 생각한다. 객체지향 프로그래밍 언어인 자바로는 한계가 있다고 생각한다. 나중에 함수형 프로그래밍 언어를 공부하며 다시 정리해 보자. 

SCALA!!!!!!!

### java.util.function 패키지

자주 쓰이는 함수형 인터페이스를 미리 정의해 놓은 것이다. 매번 새롭게 정의하지 말고 이걸 가져다가 쓰는것이 좋다. 그러면 메서드 이름도 통일되고 재사용성, 유지보수 측면에서 좋다.

| interface | method            | 설명           |
| --------- | ----------------- | ------------ |
| Runnable  | void run()        | 파라미터 x 반환값 x |
| Supplier  | T get()           | 반환값만 있다.     |
| Consumer  | void accept(T t)  | 매개변수만 있다.    |
| Function  | R apply(T t)      | 매개변수1, 반환값 ㅇ |
| Predicate | boolean test(T t) | 반환값이 boolean |

매개변수가 두개인 경우 Bi를 붙여주면 된다. 참고로 Supplier는 반환값만 있기에 Bi 인터페이스는 없다.

3개 이상일 경우 직접 만들어서 사용하면 된다. 

#### 컬렉션과 람다

CF의 메서드에 일부가 함수형 인터페이스를 매개변수로 사용한다. 

```java
ArrayList<Integer> list = new ArrayList<>();
for(int i=0; i<10; i++) list.add(i);
list.forEach(i -> System.out.print(i + ", ")); 
//forEach는 함수형 인터페이스를 매개변수로 하기에 람다식을 넣어주면 된다.
// 물론 함수형 인터페이스로 만들어 람다식을 참조하는 참조변수를 넣어도 무관하다.
list.removeIf(x -> x%2==0 || x%3==0); // 지우기
list.replaceAll(i -> i*10); // 변경하기
```

#### Function합성

함수형 인터페이스에는 람다식에 사용되는 추상메서드 이외에도 다른 메서드가 몇개있다. 이는 응용이 가능하게 해준다.

합성함수는 수학에서도 사용된다. 예를들어 h(x) = f(g(x)) 이렇게 말이다. 함수형 인터페이스는 이를 구현할 수 있게 만들어 놓았다. 

Function의 메소드로 andThen(), compose()가 있다. f(g(x)) = g.andThen(f) = f.compose(g) 이렇게 만들 수 있다.

예를들어 문자열을 16진수 숫자로 변환하는 함수 f, 숫자를 2진 문자열로 변환하는 함수 g가 있다면 아래와 같이 구현한다.

```java
Function<String, Integer> f = s -> Integer.parseInt(s, 16);
Function<Integer, String> g = i -> Integer.toBinaryStirng(i);
Function<String, String>  h = f.andThen(g);
```

또 identify() 가 있는데 이는 입력값과 출력값이 같은 항등함수다. map으로 변환작업시 사용한다.



Predicate()도 합성할 수 있다. and, or, negate로 연결한다. 논리연산자를 여러개 사용하는 것 처럼 사용하면 된다.

```java
Predicate<Integer> p = i -> i < 100;
Predicate<Integer> q = i -> i < 200;
Predicate<Integer> r = i -> i%2 == 0;
Predicate<Integer> notP = p.negate();

Predicate<Integer> all = notP.and(q).or(r); //100 <= i && i < 200 || i%2 == 0
```

#### 메서드 참조

이제 람다를 더욱 간단하게 만들어보자. 항상 할 수 있는건 아니나 하나의 메서드만 호출하는 경우에는 메서드 참조라는 방식으로 생략할 수 있다. 예를들어 문자열을 정수로 변환하는 람다식은 아래와 같다.

`Function<String, Integer> f = (String s) -> Integer.parseInt(s);`

어차피 제네릭으로 뭐 들어오는지 아는데 형식이 필요할까? 이렇게 바꿔보자.

`Function<String, Integer> f = Integer::parseInt;`

이렇게 생략할 수 있다. 제네릭이 선언되어 컴파일러가 해석하는데 큰 문제가 없다.

`BiFunction<String, String, Boolean> f = (s1, s2) -> s1.equals(s2);`

같은 원리로

`BiFunction<String, String, Boolean> f = String::equals;`

이렇게 바꿀 수 있다. s1이든 s2든 둘다 String이라는걸 알기 때문이다. 

만일 생성된객체의 경우에는 클래스 이름 대신 그 객체의 참조변수를 적으면 가능하다.

```java
MyClass obj = new MyClass(); // 객체 obj 생성
Function<String, Boolean> f = obj::equals; // x -> obj.equals(x);
```

> 하나의 메서드만 호출하는 람다식은 클래스이름:: 메서드이름 || 참조변수::메서드이름 으로 바꿀 수 있다.

생성자도 메서드 참조로 변환할 수 있고 배열을 생성 할 수도 있다.

`Supplier<MyClass> s = MyClass::new;`

`Function<Integer, int[]> f = int[]::new;``