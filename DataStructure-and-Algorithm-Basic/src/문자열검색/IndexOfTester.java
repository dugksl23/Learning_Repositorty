package 문자열검색;

import java.util.Scanner;

public class IndexOfTester {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        System.out.println("text : ");
        String text = sc.next();

        System.out.println("pattern : ");
        String pattern = sc.next();

        int idx1 = text.indexOf(pattern);
        int idx2 = text.lastIndexOf(pattern);

        System.out.println(idx1);
        System.out.println(idx2);
        System.out.println(text.substring(idx1, idx1+pattern.length()));



    }



}
