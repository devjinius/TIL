# Counting Duplicates

## Count the number of Duplicates

Write a function that will return the count of *distinct* case-insensitive alphabetic characters and numeric digits that occur more than once in the input string. The input string can be assumed to contain only alphabets (both uppercase and lowercase) and numeric digits.

### Example

"abcde" -> 0 `# no characters repeats more than once`
"aabbcde" -> 2 `# 'a' and 'b'`
"aabBcde" -> 2 `# 'a' occurs twice and 'b' twice (`b`and`B`)`
"indivisibility" -> 1 `# 'i' occurs six times`
"Indivisibilities" -> 2 `# 'i' occurs seven times and 's' occurs twice`
"aA11" -> 2 `# 'a' and '1'`
"ABBA" -> 2 `# 'A' and 'B' each occur twice`

### 문제요약

문제를 요약하자면 case-insensitive하게 중복을 찾아서 중복된 (숫자/문자)의 개수를 반환하는 것이다.

"Indivisibilities"의 경우 I와S가 반복되어서 2를 반환하게 된다.

### Psuedo Code

1. 중복 값을 저장하는 반환 배열 선언
2. 모두 uppercase로 변환
3. 차례대로 값을 비교한 뒤 같은게 나오면 반환 배열들의 값과 비교한다.
4. 반환 배열에 없으면 그 값을 집어넣고 있으면 그냥 패스
5. 반환배열.length를 반환한다.

### 구현코드

```java
boolean flag = true;
ArrayList returnArr = new ArrayList();
char[] inputArr = text.toUpperCase().toCharArray();

for (int i = 0; i < inputArr.length-1; i++) {

  for (int j = i+1; j < inputArr.length; j++) {

    if(inputArr[i] == inputArr[j]){

      for (int k = 0; k < returnArr.size(); k++) {

        if (returnArr.get(k).equals(inputArr[i])) {flag = false;}
      }

      if(flag){returnArr.add(inputArr[i]);}

      flag = true;

    }
  }
}
return returnArr.size();
```

이렇게 구현했고 옳은 답이었다. 하지만 코드가 너무 가독성이 떨어진다. 그 이유는  for 문이 세번이나 쓰였고(물론 크게는 두번이지만), flag라는 boolean변수까지 등장했다. 분명 좋은 코드는 아니다.

다른 분의 답을 보고 느낀건데 나는 contains를 생각하지 못했다.  직접 같은지 비교하는 코드를 만들었다. 좋은 코드를 가져다 쓸 수 있는데 힘들게 구현했다. 생산성이 좋지 못하다. 그 덕분에 더 가독성이 떨어진 듯하다. contains()라고 하면 바로 포함되는지를 알아 볼 수 있기 때문에 더 좋다.

```java
int ans = 0;
text = text.toLowerCase();
while (text.length() > 0) {
  String firstLetter = text.substring(0,1);
  text = text.substring(1);
  if (text.contains(firstLetter)) ans ++;
  text = text.replace(firstLetter, "");
}
return ans;
```

이게 새로운 코드다. 로직을 살펴보면

1. lowercase로 바꾼다.
2. 받아온 text가 없을때까지 반복
3. firstLetter : 나머지로 substring
4. 나머지에 FL이 있다면 ans++
5. text에서 firstLetter를 모두 ""로 변환
6. 계속 반복

5번을 나는 절대 생각 못했다 ㅠㅠㅠ 모두 찾아서 지워버리면 된다. 나는 왜 지울 생각을 하지 못했을까

오늘도 하나 배워간다.