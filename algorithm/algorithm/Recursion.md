# Recursion

## 재귀란 무엇인가

아주 좋은 예는 러시아 인형이다. 똑같이 생긴 인형이 계속 안에 있다. 인형이 작아져서 더 이상 작아지지 못할 때 인형은 마지막이 된다. 

이 마지막이 되는 조건이 재귀의 탈출조건이라고 부른다. 다른 값에 대한 같은 함수를 계산하는 케이스를 재귀 조건이라고 한다. 

즉 마지막 인형이 탈출조건, 큰 인형아래의 다른 인형들 즉 하위 문제들이 재귀조건이 된다.

## 팩토리얼

n! 이게 우리가 배운 팩토리얼이다. 5! 은 5 * 4 * 3 * 2 * 1이다. 

```java
static int factorial(int n){
  int  result = 1;
  for(int i = 0; i<n; i++){
      result *= (n-i);
  }
  return result;
}
```
그런데 잘 생각해보면  5! = 5 * 4!이다. 또 4! = 4 * 3!이다. 마치 러시아 인형처럼 반복된다. n! = n * (n-1)! 인 것이다.

그렇다면 이를 재귀함수로 만들 수 있다. 우선 n의 경우에 따라 조금 다를것이다. 우선 n이 0이면 반환값은 1이다. n이 양수일 경우 n * (n-1)! 를 반환한다. 

탈출조건은 n이 0일때가 되고 재귀조건은 (n-1)!을 호출하면 된다.

```java
// factorial 을 구현한 함수
// f(n) = n * f(n-1)이 재귀조건이며 탈출조건은 n이 0이 되면 1을 반환한다.
public static int factorial(int n){
  if(n == 0) {return 1;}
  // n이 0보다 작은경우 값이 절대 나오지 않는다.
  if(n < 0) {return -1;}
  return n * factorial(n-1);
}
```

코드가 훨씬 간결해졌다.

## 팰린드롬

앞으로도 뒤로도 같은 단어를 팰린드롬이라고 한다. 예를들자면 기러기, 토마토, rotor, redder 이런 단어들 말이다. 이런 단어도 재귀로 처리가 가능하다. 

우선 1글자나 0글자(empty string)는 무조건 팰린드롬이다. 앞으로도 뒤로도 같기 때문이다.

이제 2글자 이상의 경우다. 우선 맨 앞자리 글자와 맨 뒷자리 글자가 같아야 할 것이다. 다르면 당연히 팰린드롬이 아닐 것이다. 그럼 다음은 어떻게 해야 할까? 

맨 앞자리와 맨 뒷자리를 제외한 나머지 단어들에서 맨 앞자리와 맨 뒷자리가 같은지 비교하면 된다.

razor라는 단어를 보자. 2글자 이상의 경우고 맨 앞자리와 맨 뒷자리가 같다. 다음으로는 앞뒤 한글자 씩 빼고 azo 의 앞자리와 뒷자리가 같은지 보는 것이다. 앞자리와 뒷자리가 같은지 보는 프로세스가 같다. 재귀함수인 것이다.

탈출조건은 1글자나 0글자가되면 팰린드롬이다. 

재귀조건은 앞 뒤 글자를 비교하고 같으면 잘라내고(strip) 다시 비교한다.

```java
// palindrome 을 구현한 함수
// 탈출조건은 글자의 길이가 1 또는 0이다. f(앞뒤 글자를 자른 나머지) 가 재귀조건이다.
public static boolean palindrome(String str){
  // charAt과 subString에 사용되는 index 선언
  int index = str.length()-1;
  // index에 1더하면 length이고 탈출조건
  if (index+1 <= 1) {
    return true;
  } else {
    if (str.charAt(0) == str.charAt(index)) {
      //재귀조건
      return palindrome(str.substring(1, index));
    } else {
      return false;
    }
  }
}
```

## 다른 재귀함수들

쉽게 생각해서 거듭제곱도 재귀함수로 구현 할 수 있다.

x ^ n = x* x ^(n-1)이기 때문이다. x^0 = 1을 탈출조건으로 놓고 재귀문을 n이 0이되게 반복하면 된다. 만일 n이 음수라면 양수처럼 계산하고 1/x^-n으로 바꿔주면 된다.

Sierpinski Gasket라고하는 것도 재귀함수로 구현 가능하다. 

![Sierepinski Gasket](https://s3.amazonaws.com/ka-cs-algorithms/sierpinski-full-6.jpg)

이렇게 생긴 앤데 이게 잘 보면 계속해서 반복되는 구조인 것을 알 수 있다.