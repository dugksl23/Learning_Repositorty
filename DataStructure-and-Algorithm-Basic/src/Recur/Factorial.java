package Recur;

import static java.lang.System.out;

public class Factorial {

    public static void main(String[] args){
        out.println(factorial(5));

    }

    public static int factorial(int num){
        out.println("입력된 숫자는 : "+num);
        if (num>0) {
            int res = num * (factorial(num-1));
            System.out.println("num : "+num+"의 최종 연산값 : "+res);
            return res;
        }
        return 1;
    }




}
