#날짜와 시간

## Calendar와 Date
Date Class - Calendar Class - java.time Pakage 순으로 점점 발전했다.
현재는 java.time이 유용하지만 예전 버전도 공부해두자.

#####calendar 사용방법
`Calendar cal = new Calendar() //에러남! 추상클래스이므로 인스턴스 생성불가.`
`Calendar cal = Calender.getInstance() // 정상 실행`

#####Calendar와 Date의 형변환
1. Calender -> Date
`Date d = new Date(cal.getTimeInMillis());  // Date(long date)`
2. Date -> Calendar
`cal.settime(d)`


##형식화 클래스

날짜와 관련해서 그냥 쓰면 불편하니까 패턴을 정해서 쓰자. 정규화와 비슷하다.

#####DecimalFormat
숫자를 형식화 하는데 사용하는 클래스다.
일정한 형식으로 숫자를 표현하거나 일정한 형식의 데이터를 변환하는데 사용된다.
아래는 사용 예제다.
```java
double number = 1234567.89;
DecimalFormat df = new DecimalFormat("#.#E0"); // 패턴작성
String result = df.format(number); // format으로 변환

//혹은 이렇게도 사용가능하다.
Number num = df.parse(number);
```

#####SimpleDateFormat
날짜를 표현할 때 사용하는 방법이다. 사용방식은 DecimalFormat과 동일하다.
