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
* substring()
* indexOf()



*int ch 인 경우 인티저가 아니라 유니코드 보충문자를 포함한 20비트 문자형을 의미한다.*

#### Wrapper Class
래퍼클래스는 기본형 값을 객체로 다루는 것을 이야기한다. Integer, Character, Byte, Long 처럼 첫글자가 대문자인 기본형들이다.
원래 객체지향에서는 모든 것이 객체여야 하는데 자바는 기본형 8개가 그렇지 않다. 그래서 완전한 객체지향은 아니며 대신 성능을 얻었다.

#####Number Class
number class는 래퍼클래스들의 부모인 추상클래스다. Byte, Long, Double등을 자손으로 가진다.

+ 문자열을 숫자로 변환하기
문자열을 숫자로 변환하는 방법은 세가지가 존재한다.
1. `int i = new Integer("100").intValue()`
2. `int i2 = Integer.parseInt("100") // 반환 값이 기본형`
3. `Integer i3 = Integer.valueOf("100") // 반환 값이 래퍼클래스`

+ 오토박싱으로 인해 2번과 3번은 사실 큰 차이가 없으며 성능은 valueOf가 조금 더 느리다.

#####오토박싱과 언박싱
기본형과 참조형을 차이 없이 사용가능하도록 컴파일러가 코드를 넣어주는 것을 이야기한다.
오토박스 : 기본형 => 래퍼클래스
언박싱 : 래퍼클래스 => 기본형
```java
int i = 10
Integer intg = i; //Integer intg = (Integer)i;
Long lng = 100L; // Long lng = new Long(100);
int i2 = intg + 10; // 참조형과 기본형과의 연산 가능
int i3 = intg2 //int i3 = (int) intg2 이렇게 자동 형변환
```

#### java.util
##### regax
정규표현식이다. 텍스트 데이터를 패턴을 이용해서 파싱하거나 인덱싱하는 방법이다. 매우 강력한 툴이지만 내용이 방대하므로 따로 응용할 수 있게 공부해 두어야 한다.
#####scanner
화면, 파일, 문자열과 같은 소스로부터 데이터를 읽어오는데 도움을 준다. 스캐너는 정규표현식을 사용하여 검색도 지원한다. console클래스도 있지만 이클립스에서 작동하지않아 많이사용하지 않는다.

`String line = sc.nextLine();`
`Scanner sc2 = new Scanner(line).useDelimiter(",") `
구분자로 한 라인에 여러 데이터가 저장되어 있을 경우 라인별로 읽은 후 useDeli를 사용해 구분자로 구별해낸다.

#####stringTokenizer
긴 문자열을 지정된 구분자를 기준하여 토큰으로 자른다. 단 이 클래스의 경우 구분자로 문자열을 사용하지 못하고 문자(char)만 가능하므로 긴 구분자의 경우 String의 split이나 Scanner의 useDelimiter를 사용해야한다.

`StringTokenizer st = new StringTokenizer(src, "+-*/=()", true);`
패턴 안의 한 문자씩 총 7개의 delimiter를 사용했다. 마지막을 true로 주면 구분자도 반환한다. default는 false다.