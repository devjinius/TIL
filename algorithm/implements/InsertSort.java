import java.util.Arrays;

/**
 * Created by eugene on 2017-02-13.
 * 삽입정렬을 구현한 코드다.
 */
public class InsertSort {

    // n+1개의 배열을 가지고 있고 마지막 인덱스의 값이 새로 추가하는 값이라고 가정할 때 올바른 자리에 삽입하는 메서드다.
    static void insert(int[] array, int rightIndex, int value) {
        int key = array[value]; // key라는 새로운 변수로 추가하는 값을 복사한다.
        int i = rightIndex; // rightIndex부터 i--해서 반복할 것이다.
        while (i >= 0 && array[i] > key) { // key가 제자리를 찾을 때까지 비교한다.
            array[i + 1] = array[i];       // slide를 구현했다. 오른쪽 인덱스에 자신의 값을 복사한다.
            i--;
        }
        array[i+1] = key;                   // 복사한 key를 제자리에 넣어준다.
    }

    // insert를 반복하여 전체 n개의 배열을 정렬하는 메서드다.
    static void insertSort(int[] array){
        // 자기자신을 비교할 필욘 없으니 1부터 n-1번 반복한다.
        for (int i = 1; i<array.length; i++) {
            insert(array, i - 1, i);
        }
    }

    public static void main(String[] args) {
        int[] array = {22, 11, 99, 88, 9, 7, 42};
        insertSort(array);
        System.out.println(Arrays.toString(array));
    }
}
