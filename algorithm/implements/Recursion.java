/**
 * Created by eugene on 2017-02-15.
 */
public class Recursion {
    // factorial 을 구현한 함수
    // f(n) = n * f(n-1)이 재귀조건이며 탈출조건은 n이 0이 되면 1을 반환한다.
    public static int factorial(int n){
        if(n == 0) {return 1;}
        // n이 0보다 작은경우 값이 절대 나오지 않는다.
        if(n < 0) {return -1;}
        return n * factorial(n-1);
    }

    // palindrome 을 구현한 함수
    // 탈출조건은 글자의 길이가 1 또는 0이다. f(앞뒤 글자를 자른 나머지) 가 재귀조건이다.
    public static boolean palindrome(String str){
        // charAt과 subString에 사용되는 index 선언
        int index = str.length()-1;
        // index에 1더하면 length이고 탈출조건
        if (index+1 <= 1) {
            return true;
        } else {
            if (str.charAt(0) == str.charAt(index)) {
                //재귀조건
                return palindrome(str.substring(1, index));
            } else {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(factorial(5));
        System.out.println(palindrome("razor"));
        System.out.println(palindrome("redder"));
        System.out.println(palindrome("tomato"));
        System.out.println(palindrome("rotor"));

    }
}
