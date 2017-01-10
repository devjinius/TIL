# Generics

JDK1.5부터 도입된 큰 변화다. 아직은 좀 어렵지만 반복해서 학습하자!

### 도입배경

제네릭은 쉽게 말하면 메서드의 파라미터처럼 클래스나 메서드의 타입을 담는 박스다. 

`class Box {Object item}` 이럴 경우 item은 어느 타입이나 들어올 수 있다. 

이와 비슷하게 Collection Framework도 그 내부에 여러 타입의 객체를 담을 수 있다. 앞에서 한번 얘기했는데 이는 장점이자 단점이다. 장점은 코드 중복을 제거하는 것이지만 단점은 타입 안정성 (Type Safety)이 손상된다. 이를 해결하는 것이 제네릭이다. 제네릭은 이렇게 생겼다.

```java
//제네릭 아님
class Box{
  Object item;
  void setItem(Object item){this.item = item;}
  Object getItem() {return item;}
}
```

```java
//제네릭 도입
class Box<T>{
  T item;
  void setItem(T item){this.item = item;}
  T getItem() {return item;}
}
```

저 T자리에 아무 String이나 들어갈 수 있다. 위의 코드와 다른점은 Object의 경우 setItem의 파라미터인 item은 멤버변수 item과 타입이 다를 수 있다. 그렇게 되면 추후에 다른 코드 실행에서 문제가 생길 수 있다. 버그의 위험을 안고있고, 타입 형변환을 일일히 해주어야 한다. 그렇기에 제네릭을 사용하면 좋다.

### 장점

1. 타입안정성 제공
2. 타입체크와 형변환을 생략하여 코드가 간결해지고 유지보수에 용이

제네릭은 이렇게 두가지 장점을 가진다.

### 제네릭 제한

1. static멤버에 타입 변수 T를 사용할 수 없다.
2. 제네릭 타입의 배열을 생성할 수 없다.
3. instanceof의 피연산자로 사용할 수 없다.

왜일까 생각해보면 간단하다. 제네릭은 인스턴스별로 다른 타입을 가지게 하려고 만들어졌다. instance level에서 작동한다. 그렇기에 static에서 사용할 수 없는게 당연하다.

제네릭타입의 배열생성이 안되는 이유는 new 연산자 때문이다. new가 실행되려면 컴파일 시점에 타입을 알아야 하는데 T가 사용되면 알 수 없다.  instanceof도 마찬가지 이유다.

` T[] tmpArr = new T[10]; // 에러. `

이와는 비슷하게 생겼지만

`ArrayList<T> list = new ArrayList<T>();`는 가능하다. 

### 제네릭 사용

`ArrayList<T> list = new ArrayList<T>();` 의 경우 T가 달라지면 안된다. 상속관계여도 마찬가지다. 그러나

`Box<Apple> appleBox = new FruitBox<Apple>();`는 사용이 가능하다. 이때 FruitBox는 Box 의 자손이라고 가정한다.



JDK 1.7부터는 추정 가능한 경우 생략이 가능하다.

`Box<Apple> appleBox = new Box();` 이렇게 말이다.



만일 Apple이 Fruit의 자손이라고 가정할 경우,

```java
Box<Fruit> fruitBox = new Box();
fruitBox.add(new Fruit());
fruitBox.add(new Apple()); // 이상없다. void add(T item)
```

------

## Enum

열거형이란 같은 객체를 하나의 클래스로 묶는 역할을 한다.

예를들어 이렇게 코드 절약을 할 수 있다.

```java
class Card{
  // static final int Clover = 0;
  // static final int Heart = 1;
  // ...
  // final int kind;
  enum Kind{Clover, Heart, Diamond, Spade}
  final Kind Kind;
}
```

열거형은 상수 간 비교시 == 사용이 가능하다. 다른 비교연산자(<,>)는 안된다. 따라서 compareTo를 사용하자.

switch문의 조건식에도 상수로 사용이 가능한데 이때는 열거형이름은 적지 않고 상수 이름만 적어야 한다. `dir.East`은 안되고  `East`는 된다.

#### 출력

enum에 담긴 값을 출력할 때는 `.values()`를 호출하여 배열에 담아 반환한다. 

```java
Direction[] dArr = Direction.values();
for(Direction d : dArr)
  s.out.printf("%s=%d%n", d.name(), d.original());
```

original 메서드는 열거형 상수가 정의된 순서(index와 같은)를 반환하며 0부터 시작된다. 하지만 이는 내부적 용도로 사용되는 것이 바람직하다. 따라서 값을 지정해 주는 것이 좋다.

```java
enum Direction{
  EAST(1), SOUTH(5), WEST(-1), NORTH(10);
  
  private final int value; // 정수를 저장할 인스턴스 변수 추가
  Direction(int value){this.value = value}
  public int getValue(){return value;} // 외부에서 접근할 수 없게 final로 변수선언했으니 get메서드 작성
}// 이렇게 하나의 클래스처럼 만들어주면 name을 저장하면서 index도 지정할 수 있다.
```

열거형 같은경우 사실 상수 하나하나가 다 새로운 객체이다. 위의 코드를 예로 들으면 EAST, SOUTH, WEST, NORTH 는 모두 Direction의 객체인 것이다. `static final Direction EAST = new Direction("EAST");`



------

## annotation

애너테이션은 프로그램의 소스코드 안에 다른 프로그램을 위한 정보를 미리 약속된 형식으로 포함시킨 것이다.

소스코드의 주석인 /** ~ */안에 @이렇게 생긴 친구들을 이야기한다.

쉬운 예로 @Override가 있다.

```java
class Parent {void parentMethod(){}}
class Child extends Parent{ void parentmethod(){}} // m이 소문자다.
```

이런경우 override가 아니라 그냥 다른 이름의 메서드를 선언한 꼴이 된다. 단순 오타로 일어난 실수이나 이를 컴파일러가 알 수 있는 방법은 없다. 따라서 디버깅 작업 시 찾아내야 하는데 찾기가 쉽지 않을게 눈에 선하다.

하지만 메서드 앞에 @Override를 붙이게 되면 컴파일러가 '아 이거 오버라이드하려는 거구나 근데 왜 부모 클래스에 같은 메서드가 없지..? 이거 이상해요! '라고 에러를 발생시킨다.

