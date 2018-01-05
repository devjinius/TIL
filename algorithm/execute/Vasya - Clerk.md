# Convert string to camel case

The new "Avengers" movie has just been released! There are a lot of people at the cinema box office standing in a huge line. Each of them has a single `100`, `50`or `25` dollars bill. An "Avengers" ticket costs `25 dollars`.

Vasya is currently working as a clerk. He wants to sell a ticket to every single person in this line.

Can Vasya sell a ticket to each person and give the change if he initially has no money and sells the tickets strictly in the order people follow in the line?

Return `YES`, if Vasya can sell a ticket to each person and give the change with the bills he has at hand at that moment. Otherwise return `NO`.

###Examples:

```java
Line.Tickets(new int[] {25, 25, 50}) // => YES 
Line.Tickets(new int []{25, 100}) // => NO. Vasya will not have enough money to give change to 100 dollars
```

### 문제요약

거스름돈에 관련한 문제다. 티켓값은 25달러이고 잔고가 0원으로 시작한다. 중간에 거슬러 줄 수 없으면 "NO", 끝까지 모두 거슬러준다면 "YES"를 반환하는 문제다.

### 접근 방법

처음에는 int 타입의 balance를 0으로 잡고 단순히 더하고 빼면 될 것 같았다. 하지만 당연하게도 문제가 생겼다. 100원을 받으면 -75가 아니라 +25다. 결국 balance는 어떤 돈을 받던 +25가 된다. 그래서 다르게 생각했다. map을 이용해서 25원과 50원의 개수를 세기로 했다. 100원을 세지 않는 이유는 100원을 거슬러 줄 경우가 없다. 또한 그냥 배열을 사용해도 무관하지만 확장성을 위해 map을 사용해봤다.

### Psuedo code

1. 25, 50달러의 개수를 센다.
2. 들어온 값이 25달러면 25달러 +1
3. 들어온 값이 50달러면 25달러 -1, 50 달러 +1
4. 들어온 값이 100달러인 경우
   1. 50달러가 있다면 50: -1, 25: -1
   2. 50달러가 없다면 25: -3
   3. 여기서 100달러는 세지 않습니다.
5. 0보다 작은 경우가 한 번이라도 발생하면 NO를 반환합니다.

### 구현코드

#### version1

```java
public class ConvertString {

  static HashMap<String, Integer> balance = new HashMap();

  // balance를 조절하는 메서드
  // 달러의 종류와 변경 수치를 받는다.
  static void balanceMod(String key, int val){
    balance.put(key, balance.get(key)+val);
  }

  public static String Tickets(int[] peopleInLine) {

    //초기화
    balance.put("25", 0);
    balance.put("50", 0);
	
    for (int curval: peopleInLine) {

      if(curval == 25) {
        balanceMod("25", 1);
      } else if(curval == 50) {
        balanceMod("25", -1);
        balanceMod("50", 1);
      } else {
        if (balance.get("50") > 0) {
          balanceMod("50", -1);
          balanceMod("25", -1);
        } else {
          balanceMod("25", -3);
        }
      }

      for (int val: balance.values()) {
        if(val < 0) {return "NO";}
      }
    }

    return "YES";
  }
}
```





