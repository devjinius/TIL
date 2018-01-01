# Convert string to camel case

Complete the method/function so that it converts dash/underscore delimited words into camel casing. The first word within the output should be capitalized only if the original word was capitalized.

Examples:

```
// returns "theStealthWarrior"
toCamelCase("the-stealth-warrior") 

// returns "TheStealthWarrior"
toCamelCase("The_Stealth_Warrior")
```

### 문제요약

'-'(hyphen)나 '_'(underscore)가 있다면 이를 camel case로 바꾸어주는 메서드를 생성하는 것이다. 이미 대문자로 되어있다면 상관없고 소문자라면 대문자로 변환해주어야 한다.

### 접근 방법

우선 replace를 생각했다. 정규표현식을 사용해서 _ 와 -를 모두 찾아낸 뒤 이를 공백으로 바꿔주었다. 그랬더니 camel case가 문제였다. 먼저 str을 바꾸면 이를 대문자로 바꿀 수가 없었다. 따라서 선행작업이 먼저 필요했다. uppercase를 사용하지 않고 어떻게 대문자로 한 글자만 바꾸지 고민하다가 그냥 uppercase 메서드를 쓰면 되잖아! 로 바뀌었다.

레퍼런스를 뒤져보니 replace뒤에 함수가 들어갈 수 있었고 이를 uppercase로 바꿔 반환하면 되겠다고 생각했다.

### Psuedo code

1. str을 정규표현식으로 걸러낸다.
2. 이 때 정규표현식으로 하이픈이나 언더스코어가 포함되었을때 그 뒤의 한 글자까지 가져온다. 예를들어 "abc-def"의 경우 "-d"를 가져온다.
3. 걸러낸 친구들에서 하이픈이나 언더스코어는 빼버리고 뒤의 한글자만 대문자로 고쳐 반환한다.

### 구현코드

#### version1

처음엔 uppercase를 한 뒤 -, _를 없앴다.

```javascript
function toCamelCase(str){
  var filteredStr = str.replace(/-.|_./g, (match) => {return match.toUpperCase()});
  return filteredStr.replace(/-|_/g, "");
}
```

version2

이걸 한 줄로 수정했다.

```javascript
function toCamelCase(str){
  return str.replace(/-.|_./g, (match) => {return match.slice(1).toUpperCase()});
}
```



