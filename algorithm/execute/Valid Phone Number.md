# Convert string to camel case

Write a function that accepts a string, and returns true if it is in the form of a phone number. 
Assume that any integer from 0-9 in any of the spots will produce a valid phone number.

Only worry about the following format:
`(123) 456-7890` (don't forget the space after the close parentheses) 
Examples:

```
validPhoneNumber("(123) 456-7890")  =>  returns true
validPhoneNumber("(1111)555 2345")  => returns false
validPhoneNumber("(098) 123 4567")  => returns false
```

### 문제요약

쉬운 문제였다. 정규표현식을 사용해서 거르면 되는 문제다. 정규 표현식이 아직은 자유롭게 다룰 수 없었는데 연습해보는 좋은 기회가 되었다.

괄호-세 숫자-괄호-공백-세 숫자-'-'-네 숫자 의 패턴을 찾으면 됐다.

(123) 456-7890 이렇게 말이다.

### Psuedo code

1. 패턴을 정규표현식으로 만든다.
2. test()메서드를 이용해 true or false를 반환한다.

### 구현코드

#### version1

```javascript
function validPhoneNumber(phoneNumber){
  
  var pattern = /\(\d{3}\) \d{3}\-\d{4}/;
 
  return pattern.test(phoneNumber);
}
```

처음엔 이렇게 했다. 그런데 안되는 예외 경우가 생겼다.

`abc(123) 456-7890` 의 경우는 false를 반환해야 하는데 true를 반환했다. 따라서 이 문자만 오게끔 만들어야 했다.

version2

```javascript
function validPhoneNumber(phoneNumber){
  
  var pattern = /^\(\d{3}\) \d{3}\-\d{4}$/;
 
  return pattern.test(phoneNumber);
}
```

`^` 와 `$`를 추가했다. `^`의 경우에 이 글자로 시작하는 것만 검색하고 `$`의 경우에는 이 글자로 끝나는 것만 검색한다. 따라서 test에 들어오는 파라미터가 오직 이 글자여야만 통과할 수 있게 된다.

