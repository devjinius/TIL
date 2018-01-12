# Grid

라이브러리를 불러와 그리드를 사용은 해봤지만 css를 이용해서도 그리드를 만들 수 있다.

여러 단계를 생략하고 실제로 사용될 만한 것들만 뽑아보았다.

###준비

```html
  <div class="grid">
    <div class="box a">A</div>
    <div class="box b">B</div>
    <div class="box c">C</div>
    <div class="box d">D</div>
    <div class="box e">E</div>
  </div>
```

```css
.grid {
  border: 2px blue solid;
  height: 500px;
  width: 500px;
  display: grid;             /*  여기요  */
}

.box {
  background-color: beige;
  color: black;
  border-radius: 5px;
  border: 2px dodgerblue solid;
}
```

저렇게 display를 grid로 만들면 준비는 끝입니다.

### fr

fr은 비율입니다. 열의 너비나 행의 높이등을 정할 때 사용됩니다. 세개의 열이 각각 `1fr 3fr 1fr`의 너비를 가진다면 1/5, 3/5, 1/5의 너비를 각각 가지게 됩니다.

이는 다른 크기의 단위들과도 공존합니다. 예를들어 `1fr 50% 2fr`의 경우 50%를 주고 나머지를 3등분하여 나누어가집니다.

### 결론부터

```css
.grid {
  border: 2px blue solid;
  height: 500px;
  width: 500px;
  display: grid;
  grid-template: 25% 25% 2fr 1fr / 200px 200px;
  grid-gap: 10px / 15px;
}

.box {
  background-color: beige;
  color: black;
  border-radius: 5px;
  border: 2px dodgerblue solid;
}

.a {
  grid-area: 1 / 1 / 3 / span 2;
}
```

이 친구들을 분석해보겠습니다.

### grid-template

총 몇개의 column / row인가를 볼 수 있습니다. 

### grid-gap

각각 그리드의 셀끼리의 간격을 정할 수 있습니다.

### grid-area

a클래스가 그리드에 차지하는 크기입니다.

각각 start row / start column / end row / end column입니다. `span`의 경우 차지하는 크기를 의미하게 됩니다.



## upgrade

```css
.container {
  display: grid;
  max-width: 900px;
  position: relative;
  margin: auto;
  grid-template-areas: "head head"
                       "nav nav" 
                       "info services"
                       "footer footer";
  grid-template-rows: 300px 120px 800px 120px;
  grid-template-columns: 1fr 3fr; 
}

header {
  grid-area: head;
} 

nav {
  grid-area: nav;
} 

.info {
  grid-area: info;
} 

.services {
  grid-area: services;
}

footer {
  grid-area: footer;
}
```

이렇게 하면 화면구성을 할 수 있다. grid-template-areas로 이름을 준 뒤 이에맞게 grid-area 를 이름으로 설정하면 가능하다.

## 암시적 그리드

결과로 몇개가 오는지 모를때 그리드를 그린다면 이때 암시적 그리드를 사용합니다.

### `grid-auto-rows` and `grid-auto-columns`.

각각 행과 열의 암시적인 크기를 지정합니다.

```css
body {
  display: grid;
  grid: repeat(2, 100px) / repeat(2, 150px); 
  grid-auto-rows: 50px;
}
```

예를들어 현재 2 * 2의 그리드에 5개의 셀이 들어가야한다고 생각합시다. 그 경우 마지막 셀은 행이 50px로 추가됩니다.

속성을 지정하지 않으면 컨텐츠의 크기로 맞추어 추가됩니다.