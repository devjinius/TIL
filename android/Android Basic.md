# 안드로이드 기본개념

### 프로젝트와 모듈

앱 제작에 필요한 각종 파일을 모아서 '모듈'이라고 부른다. 모듈은 프로젝트 안에 들어있고 한 프로젝트 안에 여러 모듈이 들어있다. 즉 프로젝트가 조금 더 큰 개념. 시계앱, 서버앱 등을 한 프로젝트로 구성할 수 있다. 

app모듈 안에는 build, libs, src 3개의 디렉토리가 있고 build.gradle 파일이 들어있다. build안의 파일은 SDK가 자동으로 만들어 주는 것. 라이브러리같은 도구는 lib디렉토리에 넣는다. 나머지는 src디렉토리에 들어간다.

### onCreate()

화면이 생성되는 순간 실행되는 부분이다. 자바 프로그램이 컴파일 후 실행할때 main이 호출되듯 안드로이드에서 oncreate가 실행된다. 이 때 화면은 activity라고 부른다. 

```java
protected void onCreate(Bundle savedInstanceState){
  super.onCreate(savedInstanceState); 
  //반드시 써야하는 규칙이다. 부모인 AppCompatActivity의 onCreate호출
  setContentView(R.layout.activity_main); 
  // resource파일인 activity_main.xml파일을 호출하여 화면에 사용한다.
}
```

### Layout

화면에 보여지는 부분이다. Layout은 app/src/main/res/layout/activity_main.xml에 있다.

레이아웃의 properties에서 component의 id값을 가지고 접근 할 수 있다. id값을 변경하여 소스코드에 가져오면 된다.

`TextView textView = (TextView) findViewById(R.id.text); `

이렇게 가져오면 된다. findViewById()라는 메서드를 사용한다. 이 메서드는 파라미터를 이용해 View 클래스 타입을 반환한다. 

### 값 변경

`textView.setText(Stirng str)` 로 변경한다. 숫자를 다룰때는 String 패키지의 valueOf()를 이용하자.