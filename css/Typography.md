### 서체

`font-family: Garamond;`로 변경한다. 몇가지 가이드라인이 있다.

1. 기본 서체는 Times New Roman이다.
2. 웹페이지에 2~3개의 폰트로 통합하는것이 좋다.
3. 두 단어 이상의 폰트이름은 ""로 묶어줘야 인식한다.("Courier New")

### font-weight

기본적으로 bold, normal이 있지만 숫자로 줄 수 있다. 100 ~ 900 사이의 100의 배수로 주면 된다.

```css
header {
font-weight: 800;
}

footer {
font-weight: 200;
}
```

### word spacing

단어 사이의 간격을 띄우는 일이 많지는 않지만 혹시 있을 수 있다.

`word-spacing: 0.3em;`을 사용하면 된다. 기본적으로 0.25em이며 px보다는 em을 많이 사용한다.

### letter spacing

개별 글자간의 사이는 letter spacing이라고 한다. 기술용어로는 kerning이라고 한다.

사용법은 `letter-spacing: 0.3em;`이다.

### Text Transpormation

대문자와 소문자 속성을 지정할 수 있다.

`text-transform: uppercase;`와 같이 사용합니다.

값에는 uppercase, lowercase, capitalize 값이 있습니다. 각각 대문자, 소문자, 첫글자만 대문자입니다.

### line-height

이 속성은 줄 높이입니다.  leading + font-size = line-height 입니다. 즉 폰트사이즈가 남은 윗부분이 leading이고 이 둘을 합치면 line-height라고 합니다.

값으로는 숫자(현재글자에서 몇배), 길이(px, em, pt), % 등이 있습니다. 숫자의경우 보통 1.0 ~ 1.2배입니다.

### serif vs sans-serif

명조체처럼 글자끝이 갈고리처럼 휘어지는 것이 serif체입니다. 고딕체처럼 글자 끝에 장식선이 없으면 sans-serif입니다. sans는 프랑스어로 ~없이입니다.

serif체의 경우 가독력을 높여주고, sans-serif는 강조의 역할을 합니다.

```css
h1 {
  font-family: "Garamond", "Times", serif;
}
```

The CSS rule above says:

1. Use the Garamond font for all `<h1>` elements on the web page.
2. If Garamond is not available, use the Times font.
3. If Garamond and Times are not available, use any serif font pre-installed on the user's computer.

그래서 이러한 경우 이런 룰을 따르게 됩니다. 이렇게 예비 폰트를 두는것을 fallback font라고 합니다.

### @font-face

표시하려고 하는 글꼴이 시스템에 없는 경우 웹 서버에서 글꼴을 내려받아 화면에 표시해 주는 방법입니다.

링크를 걸지않고도 이런식으로 사용합니다.

```css
@font-face{
  font-family:ng;
  src:url(NanumGothic.eot);
  src:local(※), url(NanumGothic.woff) format(‘woff’)
} 

body{
  font-family:'나눔고딕', 'NanumGothic', ng
}
```

이런식으로 사용하게 됩니다. 안의 내용은 링크에 들어가는 부분을 직접 주소창에 쳐도 얻을 수 있습니다. 혹은 서버의 로컬에 있다면 로컬주소를 사용하면 됩니다.



