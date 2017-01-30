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