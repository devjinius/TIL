import java.util.Arrays;

/**
 * Created by eugene on 2017-03-11.
 */
public class QuickSort {
    public static void main(String[] args) {
//        int[] array = {8, 1, 3, 11, 7, 5};
        int[] array = {9, 7, 5, 11, 2, 14, 3, 10, 6, -1, 0};
        quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }
    // quicksort method
    static void quickSort(int[] array, int p, int r){
        if(p<r){
            // 오류 수정부분
            // 이 부분에서 애를 많이 먹었다.
            // 변수 pivot 선언을 14줄의 if문 밖으로 설정했더니 값이 이상하게 나왔다.
            // 그 이유는 p=0, r=1인 상태로 if문을 돌고 나오면 quickSort(0,0)이 실행된다. 그 때는 pivot이 재 선언 되면 안된다.
            // 그러나 if문 밖에 있으므로 partition이 한번 더 실행된다.
            int pivot = partition(array, p, r);
            // 재귀를 이용해 값을 도출한다.
            quickSort(array, p, pivot-1);
            quickSort(array, pivot + 1, r);
        }
    }

    // swap method
    static void swap(int[] array, int a, int b){
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    // partition method
    // 하위배열을 나누고 피벗의 올바른 index를 반환하는 함수다.
    // j는 just index
    // q는 피벗의 위치가 되며 q를 기준으로 왼쪽과 오른쪽이 pivot보다 작은 값, 큰 값으로 나뉜다.
    static int partition(int[] array, int p, int r) {
        //최초로는 q = p = j
        int q = p;
        //for문의 인덱스는 j다. j는 p부터 r-1까지 돈다.
        for (int j = p; j<r; j++) {
            // 반복 인덱스의 값과 피벗값을 비교한다.
            // 비교값이 피벗보다 작거나 같은 경우 q와 j의 위치를 바꾼다. q가 경계니까 경계를 한칸 미뤄준다는 느낌이다.
            // 비교값이 크면 가만히 놔둔다. q보다 j가 크기때문에 가만히 놔두면 q오른쪽에 위치한다.
            if (array[j] <= array[r]) {
                swap(array, j, q);
                q++;
            }
        }
        // 반복문을 마치면 q는 피벗의 위치가 되고 현재 q의 값은 피벗보다 크다.
        // 따라서 피벗과 q의 값을 바꿔주면 된다.
        swap(array, r, q);
        return q;
        /*
        아래 코드는 위의 오류 수정부분에 에러가 나서 고치려고 접근방식을 다르게 해서 풀은 부분이다.
        위의 코드는 비교값이 크면 놔두는건데 여기는 비교값이 작으면 놔두는 것이다.
        또한 while문을 사용하였다.
        피벗의 경계선을 r-1부터 --로 잡아나가는 방식이다.
         */
//        int i = p;
//        int j = r-1;
//        int pivot = array[r];
//        while(i<=j){
//            if (array[i] <= pivot) {
//                i++;
//            } else {
//                swap(array, i, j--);
//            }
//        }
//        swap(array, i, r);
//        return i;
    }
}