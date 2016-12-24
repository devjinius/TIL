# Starting Java

----------

###java.lang패키지
java lang패키지는 기본이 되는 클래스를 포함하는 패키지다.

| 메서드     | 설명     |
| :-------- | --------:|
| clone()   |   복제 |
| equals()  |   비교 |
| getClass()|   class 반환 |
| toString() |   문자열 반환 |
 등이 있다.
총 11개의 메서드를 가지고 있다.

----------

####equals()

equals메서드는 boolean값을 반환하며 참조변수의 값을 비교하는 메서드다.
중요한것은 참조변수의 **주소값이 같은지를 판단하는 기능**만을 하기 때문에 **equals메서드를 오버라이딩**하여 값을 비교하도록 해야 한다.

* 오버라이딩해서 값을 비교하도록 만들어야한다.
* String이나 Date, File 같은 클래스는 이미 오버라이딩 되어있다.
  * 그러나 StringBuffer클래스는 오버라이딩 되어있지 않다.

####toString()
인스턴스 정보를 문자열로 제공하는 것인데 Object에 정의된 function은 '클래명@hashValue' 를 얻게된다. 
ex) Card@19e0bfd

따라서 이 역시 오버라이딩 해서 재정의해야 한다. 주의할점은 Object Class`s Access Modifier 가 public이기 때문에 public으로 오버라이드 해야 한다.

####clone()
이 메서드는 자신을 복제하여 새로운 인스턴스를 생성하는 메서드다. 기존의 값을 보존하고 생성하는데 이는 기본 타입일 경우이고 참조변수타입(배열포함) 일 경우 기존의 주소값을 같이 가르키기 때문에 원본을 수정하게 된다. 
* 이렇게 원본값을 변경하는 경우를 얕은복사라고 한다.
* 값을 복사해오는 것을 깊은 복사라고하며 구현방법은 다음과 같다.
```java
public Circle deepCopy()
	object obj = null;
	try{
		obj = super.clone();
	} catch (CloneNotSupportedException e) {}
	
	//이 부분이 깊은 복사의 핵심
	Circle C = (Circle) obj; // 새로운 객체를 생성하고
	c.p = new Point(this.p.x, this.p.y);  // 값을 복사해 할당하는 과정을 넣어준다.
	
	return c;
```
* clone은  cloneable을 구현한 클래스의 인스턴스만 메서드 사용이 가능하다.
* 또한 clone을 호출할 경우 try-catch문을 작성해야 한다.

####String과 StringBuffer

String은 값을 바꾸면 새로운 객체를 만들어 주소를 할당하는 것이고 StringBuffer는 실제로 값을 변경함.

자주쓰는 String Method
* charAt()
* contains()
* indexOf()
* split()
* trim()
* valueOf()

*int ch 인 경우 인티저가 아니라 유니코드 보충문자를 포함한 20비트 문자형을 의미한다.*
