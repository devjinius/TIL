CSS의 position, display, z-index, float, clear에 대해서 알아보자

## Position

말 그대로 포지션이다. 포지션은 4가지의 value를 가질 수 있다.

* static(기본값)
* relative
* absolute
* fixed

### relative

위치를 직접 지정할 수 있게된다. top, botttom, left, right의 offset을 설정할 수 있다. static일때를 기준으로 offset만큼 이동하게 됩니다.

### absolute

이것도 역시 위치를 지정할 수 있다. relative는 static을 기준으로 움직이는데 이 absolute는 부모를 기준으로 움직이게 됩니다. 이 때의 부모의 기준은 부모 요소 중에 static이 아닌 요소입니다. 부모가 모두 static이라면 body가 부모가 됩니다.

### fixed

스크롤 하더라도 위치를 고정시킵니다. 정말로 화면에 절대적인 위치를 갖습니다.

##z-index

layer의 속성이다. 즉 서로 컨텐츠가 겹칠 때 우선순위를 나타냅니다. 값을 정수값을 주어 더 큰값이 위에 오게 됩니다.

## display

한 줄을 기준으로 옆에 다른 요소를 둘 수 있는지를 정의하는 속성이다.

예를들어 p 엘리먼트는 한줄을 모두 차지한다. 자동으로 줄바꿈이 된다. 그러나 a태그나 span태그는 줄바꿈이 되지 않고 div태그는 또 줄바꿈이 된다. 이들은 display속성이 다르기 때문이다.

* inline
* block
* inline-block

의 값이 있다.

### inline

em, a 태그와 같은 요소들은 기본값이 inline입니다. 옆에 다른 요소와 함께 공존합니다.	넓이와 높이는 지정할 수 없습니다.

### block

동일한 줄에 같이 표시가 안되는 요소들을 block이라고 합니다. h1, p, div와 같은 요소들이 있습니다. 넓이와 높이를 지정할 수 있습니다.

### inline-block

인라인과 블록요소를 합친것입니다. inline처럼 다른요소와 공존하면서 넓이와 높이를 지정할 수 있습니다. img 요소가 대표적인 inline-block 요소입니다.

## float와 clear

float는 이미 알고있듯 떠다니는 속성이다. 이에 서로 겹치거나 충돌하여 문제가 발생하는 경우가 있는데 이 때 clear를 사용한다.

clear에는 left, right, both, none 값이 있다.

 left, right, both는 모두 자신이 속한 영역과 같은 부분에 만나는 왼쪽, 오른쪽, 양쪽 엘리먼트를 치워버립니다.