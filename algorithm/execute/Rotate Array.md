# Rotate Array

Create a method named "rotate" that returns a given array with the elements inside the array rotated n spaces.

If n is greater than 0 it should rotate the array to the right. If n is less than 0 it should rotate the array to the left. If n is 0, then it should return the array unchanged.

###Example:

```java
Object[] data = new Object[]{1, 2, 3, 4, 5};

rotate(data, 1)    =>    {5, 1, 2, 3, 4}
rotate(data, 2)    =>    {4, 5, 1, 2, 3}
rotate(data, 3)    =>    {3, 4, 5, 1, 2}
rotate(data, 4)    =>    {2, 3, 4, 5, 1}
rotate(data, 5)    =>    {1, 2, 3, 4, 5}

rotate(data, 0)    =>    {1, 2, 3, 4, 5}

rotate(data, -1)    =>    {2, 3, 4, 5, 1}
rotate(data, -2)    =>    {3, 4, 5, 1, 2}
rotate(data, -3)    =>    {4, 5, 1, 2, 3}
rotate(data, -4)    =>    {5, 1, 2, 3, 4}
rotate(data, -5)    =>    {1, 2, 3, 4, 5}
```

###문제요약

그리 어렵지 않은 문제였다. 배열과 int를 받으면 int의 수만큼 배열을 rotate 시키는 것. 다만 0일경우 그대로 반환, 양수의 경우 마지막을 앞으로, 음수의경우 앞을 마지막으로 보내는 것이다. stack과 queue를 배열에서 바로 구현 가능한 JS였다면 매우 쉽게 짰을 것 같다. (pop, push, add 등등...) 자바로 푼 나는 ArrayList를 이용했다.

### Psuedo code

1. 받아온 배열을 ArrayList 객체로 생성
2. 0이면 반환
3. 양수면 맨뒤의 값을 빼서 temp에 저장하고 배열에서 지운다.
4. 배열의 앞에 temp를 추가한다.
5. 음수면 맨앞의 값을 빼서 temp에 저장하고 배열에서 지운다.
6. 배열의 맨 뒤에 temp를 추가한다.
7. ArrayList를 Array로 변환하여 반환한다.

### 구현코드

```java
public static Object[] rotate(Object[] data, int n) {
		
	//#1 arrayList로 변환
	ArrayList listData = new ArrayList(Arrays.asList(data));
	int size = listData.size()-1; // 배열의 마지막 index 저장

	//#2 0이면 반환
	if(n == 0) {return data;}
	else if (n > 0) {

		// n번만큼 반복
		while (n>0){
			
			Object temp = listData.get(size);    //temp에 맨 뒤 값 저장
			listData.remove(size);               //방금 추출한 값 삭제
			listData.add(0, temp);               //맨 앞에 저장
			n--;
		}
	} else {
		
		while (n<0){
			
			Object temp = listData.get(0);       //temp에 맨 앞 값 저장 
			listData.remove(0);                  //방금 추출한 값 삭제
			listData.add(size, temp);            //맨 뒤에 저장
			n++;
		}
	}

	// arrayList를 object[]로 변환 후 반환
	return listData.toArray();
}
```

