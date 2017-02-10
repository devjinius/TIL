# OOP

현재 2회독 중이기에 1회독에서 아는 내용은 서술하지 않고 2회독에서 새롭게 발견 혹은 잘 몰랐던 부분을 서술할것.

## 가변인자 varargs

메서드 매개변수를 동적으로 지정하는 것을 이야기 한다. 대표적으로 PrintStream클래스의 printf()와 concatenate()가 있다.

사용방법은 이렇다. 타입... 변수명 의 형식으로 선언한다.

```java
public PrintStream printf(String format, Object... args){}
```

이 때 가변인자를 가장 마지막에 선언해야 한다. 그렇지 않으면 컴파일 에러가 발생한다.

가변인자는 내부적으로 배열을 이용한다. 받은 인자를 배열로 처리한다. 즉 호출 시 계속 배열을 생성하는 것이다. 비효율이 있으므로 필요한 경우에만 사용하도록 하자.

`String concatenate(String[] str){}`

이렇게 선언해도 되지 않나 싶지만 이럴 경우 인자를 반드시 넘겨야 한다. 즉 null 이나 new String[0]을 넘겨주어야 한다. 값이 없는 `concatenate()`는 에러가 발생한다. 



## 생성자 constructor

다른생성자를 호출할 때 this()를 사용하는건 알고 있다. 그러나 또다른 조건이 있다. 

바로 첫줄에서만 호출이 가능하다는 점이다!!!

```java
Car(String color){
  door = 5;
  this(); //에러 발생 첫째줄이 아님
}
```

그 이유는 생성자 내에서 초기화 작업 도중에 다른 생성자를 호출하게 되면 다른 생성자 내에서도 다시 초기화가 이루어 질 것 이다. 따라서 이전의 호출화 작업이 무의미해질 수 있기 때문에 그렇다.

#### this를 사용할 수 있는것은 인스턴스 멤버 뿐이다.

static메서드에서는 인스턴스 멤버를 사용할 수 없는 것 처럼 this도 사용할 수 없다.!!

#### 초기화 블럭

초기화 블럭은 인스턴스, 클래스 초기화 블럭이 각각 존재한다. 사용하는 이유는 아래와 같다.

```java
Car(){
  count++;
  serialNo = count;
  color = "white";
  gearType = "Auto";
}

Car(String color, String gearType){
  count++;
  serialNo = count;
  this.color = color;
  this.gearType = gearType;
}
 
// 이럴 경우 각 생성자의 두 줄은 공통으로 들어간다. 따라서 인스턴스가 생성될 때 수행하게끔 공통부분을 묶어주면 좋을 것 같다.
{
  count++;
  serialNo = count;
} // 이렇게 {}안에 묶어주면 초기화 블럭으로 된다.
```

객체지향의 가장 큰 목적인 중복의 제거! 다.

초기화의 실행 순서는 다음과 같다.

> static 기본값(default) -> static 명시적 초기화 -> static 초기화 -> main -> instance생성 -> instance 기본값 -> instance 명시적 초기화 -> instance 초기화 -> 생성자

기본값 -> 명시적 -> 초기화 블럭 -> 생성자 순이며 static이 instance보다 항상 우선순위다.

일련번호(serial)과 같은건 인스턴스가 생성될 때마다 초기화 블럭을 이용해 처리하면 될 것이다.

#### 조상클래스의 생성자

자손클래스의 생성자에는 super()가 반드시 첫줄에 포함되어야 한다. 그 이유는 자손 클래스의 인스턴스는 조상 클래스의 멤버를 이용하기 때문이다. 따라서 우리가 첫 줄에 super()를 호출하지 않으면 컴파일러가 자동으로 실행한다.



## 클래스 관계

#### 포함관계 composite

클래스의 멤버변수로 다른 클래스 타입의 참조변수를 선언하는 것을 뜻한다.

단위 클래스별로 코드가 나뉘기 때문에 유지보수가 쉬워진다. 이를 포함 composite라고한다.

#### 상속과 포함

is-a 와 has-a의 관계다.

> Circle is a Point.
>
> Circle has a Point. 이게 더 자연스럽다.

그렇기 때문에 상속보다는 포함이 더 맞다.

쉬워보이지만 클래스가 다양해지면 헷갈릴 수 있다. 그럴수록 원칙을 세우고 원칙에 맞게 행동해야 한다.



## Type Casting in referencial variable

자손 -> 조상의 경우 생략가능하고 조상 -> 자손의 경우는 생략이 불가한 것은 알고있다. 명시적으로 표기해주더라도 컴파일은 가능하나 실행이 안되는 경우가 있다.

먼저 되는 경우다. 

```java
Car car = null;
FireEngine fe = new FireEngine();
FireEngine fe2 = null;
car = fe;  // 조상타입의 참조변수가 자손타입의 인스턴스를 가지고 있다.
fe2 = (FireEngine)car;  // 자손타입의 참조변수에 조상타입의 참조변수(자손타입의 인스턴스를 가짐)가 형변환하여 들어가는것은 가능하다.
```

즉 조상타입의 참조변수가 자손타입의 인스턴스를 가지고 있을 때 이 조상타입 참조변수를 명시적인 형변환으로 바꾸는 건 가능하다.

조참이 자인을 가질 때 이 조참이 명시적인 형변환으로 자참으로 바뀌는 것이 가능하다.

이제 안되는 경우다.

```java
Car car = new Car();  // car 인스턴스 선언
Car car2 = null;
FireEngine fe = null;
fe = (FireEngine) car;  // Error
car2 = fe; // no problem
```

이게 안되는 경우다. 참조변수 car는 조상 인스턴스를 가진다. 이 때 자손인 FireEngine으로 타입 캐스팅을 시도하여 자손 참조변수에 저장할 경우 컴파일은 가능하지만 실행이 되지 않는다.

조상타입의 참조변수에 자손타입의 인스턴스를 참조하는것은 가능하지만 그 반대인 자손타입의 참조변수에 조상타입의 인스턴스를 참조할 수 없기 때문에 이게 불가능 한 것이다.

## 조상 참조변수와 자손 참조변수의 멤버사용

결론부터 얘기하자면 조상타입의 참조변수에 자손타입의 인스턴스를 사용할 경우 동일하게 정의된 멤버 변수를 호출하면 조상변수를, 자손타입의 참조변수에 자손타입의 인스턴스를 사용할 경우 자손의 변수를 반환한다. 메서드의 경우 똑같이 오버라이딩 된 자손 타입의 메서드를 호출하게 된다. 말로하면 더 헷갈리고 코드로 보자.

```java
class SuperThis{
  public static void main(String[] args){
    Parent p = new Child(); // 조 -> 자
    Child c = new Child();  // 자 -> 자
   
    System.out.println(p.x); // 100  조상 참조변수라 조상의 멤버변수를 호출한다.
    System.out.println(c.x); // 200
    p.method();  // Child 200   메서드는 똑같이 자손이기 때문에 자손의 메서드를 호출하고 자손의 메서드 안에서 사용되는 x도 당연히 자손의 멤버변수를 사용한다.
    c.method();  // Child 200
  }
}
class Parent{
  int x = 100;
  void method(){System.out.println("Parent "+ x);}
}
class Child extends Parent{
  int x = 200;
  void method(){System.out.println("Child " + x);}
}
```

p.method()의 x가 200인게 헷갈릴 수 있다. 까먹지 말자!

