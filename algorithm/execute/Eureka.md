# 유레카

## Take a Number And Sum Its Digits Raised To The Consecutive Powers And ....¡Eureka!!

The number `89` is the first integer with more than one digit that fulfills the property partially introduced in the title of this kata. What's the use of saying "Eureka"? Because this sum gives the same number.

In effect: `89 = 8^1 + 9^2`

The next number in having this property is `135`.

See this property again: `135 = 1^1 + 3^2 + 5^3`

We need a function to collect these numbers, that may receive two integers `a`, `b` that defines the range `[a, b]` (inclusive) and outputs a list of the sorted numbers in the range that fulfills the property described above.

Let's see some cases:

```java
sum_dig_pow(1, 10) == [1, 2, 3, 4, 5, 6, 7, 8, 9]

sum_dig_pow(1, 100) == [1, 2, 3, 4, 5, 6, 7, 8, 9, 89]
```

If there are no numbers of this kind in the range [a, b] the function should output an empty list.

```java
sum_dig_pow(90, 100) == []
```

Enjoy it!!

### 문제요약

1~n까지의 범위를 받아서 그 사이에 Eureka가 되는 수를 모두 반환한다.

유레카는 다음과 같다.`89 = 8^1 + 9^2` 이런 수를 말한다.

몇 제곱을 해야하는지 정해야하는데 요걸 처음에는 String.length로 접근하려고 했으나 그러지 않아도 된다는 사실을 알았다.

### Psuedo Code

1. 반환 배열 선언
2. a부터 b까지 for문
3. i가 10보다 작다면 모두 포함
4. 10보다 크다면 string[]로 바꾸어 제곱하며 더한다.
5. 더한값이 i와 같다면 추가한다.

### 구현코드

```java
List<Long> returnList = new ArrayList<>(); // #1
  
for (long i = a; i < b; i++) {             // #2

  long sum = 0;
  int index = 1;

  if(i < 10){                              // #3
    returnList.add(i);
  } else {

    String[] strArr = String.valueOf(i).split("");  // #4

    // strArr이 [8, 9]라면 하나씩 가져와서 제곱해서 sum에 저장
    for (String str: strArr) {sum += Math.pow(Long.parseLong(str), index++);}

    if(sum == i){returnList.add(i);}       // #5
  }

}
return returnList;
```

처음에는 이렇게 구현했다.

str.length로 접근하지 않고 a~b인 for문이 돌때마다 index를 주어 index만큼 제곱하였다. 그랬더니 새로운게 보였다. 어차피 1의자릿수인 숫자(1,2,3,4..)는 제곱할게 1이다. index 가 1이기 때문이다. 따라서 아래와 같이 코드가 바뀌었다.

```java
List<Long> returnList = new ArrayList<>();

for (long i = a; i < b; i++) {

  long sum = 0;
  int index = 1;

  String[] strArr = String.valueOf(i).split("");

  for (String str: strArr) {sum += Math.pow(Long.parseLong(str), index++);}

  if(sum == i){returnList.add(i);}
}
return returnList;
```

if문을 그냥 빼버렸다. 그래도 문제가 없이 잘만 돌아간다. 처음에 짤 때 이렇게  짜면 좋았을 텐데 조금 아쉽다.