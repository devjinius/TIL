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

#####ChoiceFormat
특정범위에 속하는 값을 문자열로 변환해주는 클래스다.

`ChoiceFormat form = new ChoiceFormat(limits, grades);`
// limits = 60, 70, 80, 90  grades = D, C, B, A
`form.format(scores[i]);`
// scores배열을 차례로 집어넣으면 점수 범위에 맞게 grades반환
*주의할 점은 limits에 값이 오름차순으로 정렬되어야 한다.
*또한 limits와 grades의 순서와 개수를 맞추어 주어야 한다.

#####MessageFormat
미리 정해진 양식대로 출력하거나 파싱하게 도와주는 클래스다. 다수의 데이터를 같은 양식으로 출력할 때 사용하면 좋다. Database와 연동할 경우 SQL문을 반복하여 값을 집어넣을때 사용하면 좋을 것 같다.

```java
String msg = "INSERT INTO "+ table + " VALUES('{0}', '{1}', '{2}');
Object src = {"이자바","02-123-5678", "28"};
String result = MessageFormat.format(msg, src);
```

##java.time 패키지
Date와 Calendar의 단점을 해소하기 위해 java.time패키지가 등장하였다. 큰 특징은 String클래스 처럼 immutable하기 때문에 값을 바꾸는 경우 새로운 객체가 생성되어 대입 연산자 이용해야 한다. 또 immutable하기에 thread-safe하다.

* 핵심클래스로는 `LocalTime // 날짜` 과 `LocalDate // 시간`가 있다. 
* 둘을 합치면  `LocalDateTime` 이고 time-zone을 합치면 `ZonedDateTime`이 된다. 
* Period와 Duration은 날짜와 시간의 간격을 표현하기 위한 클래스다. 날짜는 * Period, 시간은 Duration을 사용한다.
* 객체는 now(), of()로 생성한다. now는 현재고 of(2016,12,31)처럼 생성한다.
* 날짜와 시간의 단위를 정의해 놓은 것이 TemporalUnit인터페이스이고 이를 구현한게 ChronoUnit이다.
* 비슷하게 년, 월, 일 등 날짜와 시간의 필드를 정의해 놓은 것이 TemporalField이고 이를 구현한 것이 ChronoField다.

#####LocalDate와 LocalTime

사용방법은 위에서 설명한대로 매우 간단하다. 
parse의 경우 이렇게 사용한다.
LocalDate birthDate = LoclDate.parse("1999-12-31");
 그 외에도 사용하는 메서드들은 이러하다.
 * get(TemporalField field) // 값을 가져온다.
 * with(TemporalField field, long newValue) // 값을 변경한다.
 * plus(long amountToAdd, TemporalUnit Unit) // 값을 더한다.
 
#####Instant
Instant는 에포크타임(1970-01-01 00:00:00 UTC)로부터 경과된 시간을 나노초 단위로 표현하였다. 단일진법을 사용하기 때문에 계산하기 편하다. 
#####LocalDateTime 과 ZonedDateTime
`LocalDateTime dt = LocalDateTime.of(LocalDate date, LocalTime time)`
`LocalDateTime dt2 = date.atTime(time);`
`LocalDateTime dt3 = time.atDate(date);`
// 이렇게도 생성 가능하다.

`ZonedID zid = ZoneId.of("Asia/Seoul")`
`ZonedDateTime zdt = dateTime.atZone(zid);`

#####TemporalAdjusters
plus()나 minus()로 날짜를 구할 수 있다. 다만 이번달의 3번째 금요일, 다음주 토요일이 몇일인지 구하고 싶을 경우 계산하기 불편하여 생긴 클래스다. 
`LocalDate today = LocalDate.now();`
`LocalDate nextMonday = today.with(TemporalAdjusters.next(DayOfWeek.MONDAY))`
다음주 월요일의 날짜를 구하는 코드다.


