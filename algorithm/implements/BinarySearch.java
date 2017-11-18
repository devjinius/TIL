/**
 * Created by eugene on 2017-02-01.
 * int 속성의 배열을 받아 이진 배열로 검사하여 값을 찾는 프로그램
 */


public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
        System.out.println(binarySearch(arr, 67));

    }

    // 배열을 매개변수로 받아 찾고자 하는 값의 인덱스를 반환하는 함수
    // x값이 arr에 없다면 -1을 반환할 것
    static int binarySearch(int[] arr, int target) {

        int min = 0;
        int max = arr.length;

        /* < Pseudocode >
        1. min = arr[0], max = arr[arr.length]
        2. guess = (int)((min + max) / 2); //절반으로 나누어 찾는 추측값
        2. min > max(찾고자 하는 값이 배열에 없는 경우 이렇게 된다.) return -1
        3. arr[guess] == target return guess
        4. arr[guess] > target max = arr[guess-1]
        5. arr[guess] < target min = arr[guess+1]
        6. 2번 반복
        */


        while (min <= max) {
            int guess = (int)((min + max) / 2); //절반으로 나누어 찾는 추측값
            if(arr[guess]==target) return guess;
            else if(arr[guess] > target) max = guess - 1;
            else if(arr[guess] < target) min = guess + 1;
        }
        return -1;
    }
}
