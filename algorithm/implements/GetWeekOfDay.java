import java.time.LocalDate;
/**
 * Created by eugene on 2017-01-18.
 * 2016년의 월과 일을 받아서 요일을 출력하는 프로그램이다.
 */

class GetWeekOfDay
{
    public String getDayName(int a, int b)

    // 이부분부터 코딩

    /* < Pseudocode >
    1. String배열을 이용해서 요일을 정의한다.
    2. 매개변수 a와 b를 이용하여 LocalDate instance ab를 만든다. 이때 of 메서드 사용
    3. getDayOfWeek의 출력값이 1부터 7사이니까 -1해주어 출력한다.
    */

    {
        String[] weekOfDay = {"MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};
        LocalDate ab = LocalDate.of(2016, a, b);
        return weekOfDay[ab.getDayOfWeek().getValue()-1];  // 1<=x<=7;
    }

    // 여기까지

    public static void main(String[] args)
    {
        GetWeekOfDay test = new GetWeekOfDay();
        int a=5, b=24;
        System.out.println(test.getDayName(a,b));
    }
}

