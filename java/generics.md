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





