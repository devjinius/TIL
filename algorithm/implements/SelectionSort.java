import java.util.Arrays;

/**
 * Created by eugene on 2017-02-10.
 * 선택정렬을 구현한 코드다.
 */
public class SelectionSort {

    // startIndex 부터 가장 작은 수를 찾아 그 수의 index 를 반환하는 함수
    // 이 함수의 반환값과 startIndex를 파라미터로 swap 하면 될 것임
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

    static void swap(int[] array,int firstIndex,int secondIndex){
        int tmp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = tmp;
    }

    static int[] selectionSort(int[] arr) {
        for (int i = 0; i<arr.length-1; i++)
            swap(arr, i, indexOfMinimum(arr, i));
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {5, 10, 3, 1, -2, 10};
        System.out.println(Arrays.toString(selectionSort(arr)));
    }
}
