# Selection Sort

## 선택정렬이란

정렬 알고리즘의 한 종류다. 로직은 아래와 같다.

1.  리스트에서 가장 작은 값을 찾아 index 0번과 swap한다.
2.  0번을 제외한 나머지 리스트에서 가장 작은 값(second smallest)을 찾아 1번과 swap한다.
3.  0번과 1번을 제외한 나머지 리스트에서 가장 작은 값(third smallest)을 찾아 2번과 swap한다.
4.  이런식으로 array.length-1 (it means, n-1)번 반복한다.

1번을 실행한 뒤 다음으로 작은 항목을 선택(select)하여 제자리로 바꾸는 과정이기 때문에 선택정렬이라고 부른다.

### 구현방법

선택정렬을 구현하는데 필요한 단계를 크게 나누면 세가지로 나눌 수 있다. 물론 나누지 않고 문제를 해결할 수 있다. 다만 문제 해결 과정을 단순화하여 쪼개고 객체단위로 나누는 것이 사람한테나 컴퓨터에게나 모두 효율적인 코딩이지 않을까?

1. Swap
2. Find smallest number
3. Loop

#### Swap

이건 뭐 쉽다. 새로운 변수 tmp를 선언하고 바꾸면 된다.

```java
static void swap(int[] array,int firstIndex,int secondIndex){
  int tmp = array[firstIndex];
  array[firstIndex] = array[secondIndex];
  array[secondIndex] = tmp;
}
```

#### Find smallest number

```java
static int indexOfMinimum(int[] array, int startIndex) {
  int minValue = array[startIndex];
  int minIndex = startIndex;
  // 자기자신과 비교할 필욘 없으니 startIndex + 1 부터 비교한다.
  for (int i = startIndex+1; i < array.length; i++) {
    if (minValue > array[i]) {
      minValue = array[i];
      minIndex = i;
    }
  }
  return minIndex;
}
```

### Loop

2번 -> 1번 과정을 n-1번 반복하는 selectionSort 메서드를 구현한다.

```java
static int[] selectionSort(int[] arr) {
  for (int i = 0; i<arr.length-1; i++) // 마지막 수는 비교할 필요가 없으니 n-1번 반복한다.
    swap(arr, i, indexOfMinimum(arr, i));
  return arr;
}
```

## 효율분석

위에서 한번 정리했듯 선택정렬에는 세가지 프로세스가있다.

1. Swap
2. Find smallest number
3. Loop

1번에는 반복문이 없다. 따라서 상수인 Θ(1)이다.

3번에는 한번의 반복문이 존재하고 총 n-1번이 실행된다. 따라서 Θ(n)이다. 그런데 3번에서 1번을 호출하므로 두개를 합치면 Θ(n)이 되고 똑같다.

2번에는 한번의 반복문이 존재한다. 따라서 n번 실행된다. 그런데 3번에서 호출한 1번이 2번을 호출하는 구조다. 3 -> 1 -> 2 이런 구조다. 따라서 2번은 n번실행되는데 3번 때문에 1번이 n번 실행되는 구조다.

다시 간단하게 정리하면 3(n) -> 1(1) -> 2(n) 실행되는 구조다. 따라서 n번을 n-1번 반복하는 구조다. 계산 과정을 조금 더 구체적으로 들여다보자.

10개의 사이즈를 가진 배열이 있다고 하자. 

1. 10개의 방에 들어가 가장 작은 값을 찾아 0번과 swap  -> 10번
2. 0번을 제외한 9개의 방에 들어가 가장 작은 값을 찾아 1번과 swap  -> 9번
3. 0, 1번을 제외한 8개의 방에 들어가 가장 작은 값을 찾아 2번과 swap -> 8번
4. ...
5. 8번과 9번을 비교하고 작은걸 8번에 정렬 -> 1번

즉 10 + 9 + 8 + 7 + ... + 1 번 비교하게 된다. 그렇다면 n개의 배열이 있다면

> n + (n-1) + (n-2) + ... + 1 = (n+1) * n/2

가 될 것이다. 

그러므로 We can say that Selection-Sort runs in Θ(n^2) time in all cases. 라고 할 수 있다.