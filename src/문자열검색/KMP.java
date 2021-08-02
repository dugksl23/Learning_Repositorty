package 문자열검색;

import java.util.Scanner;

public class KMP {

    public static int kmpMatch(String text, String pattern) {

        int tp = 1;
        int pp = 0;
        int[] skip = new int[pattern.length()+1];
        // 동일한 패턴이 문자열이 몇번째까지 같은지 저장하는 용도

        skip[tp] = 0;
        while (tp != pattern.length()) {
            // 텍스트 포인터가 패턴의 size보다 작을 동안 계속 돈다.
            // 후위 연산자이기에 tp는 늘 후에 +1을 한다. 그렇기에
            // 같다라는 의미는 검색 성공임을 나타낸다.
            System.out.println("실행됨 첫번쨰 while문");
            if (pattern.charAt(pp) == pattern.charAt(tp)) {
                skip[++tp] = ++pp;
                // 1. 같은 문자열을 찾으면 한칸씩 이동한다. patternPointer +1을 한다.
                // 2. 동일한 패턴의 문자열 index를 skip에 그대로 저장한다.
                // -> 동일한 문자열 패턴의 마지막 index를 저장하기 위함이다.

            } else if (pp == 0) {
                // 첫번째 if문에서 동일 패턴의 문자열을 찾지 못했다면
                // patternPointer가 0과 같은 경우에는
                skip[++pp] = pp;
            }else{
                pp = skip[pp];
            }
        }
        // 건너 뛰기표 만들기

        tp = pp = 0;
        while (tp != text.length() && pp != pattern.length()) {
            System.out.println("실행됨 첫번쨰 while문");
            if (text.charAt(tp) == pattern.charAt(pp)) {
                tp++;
                pp++;
            } else if (pp == 0) {
                tp++;
                // 패턴 포인터가 일치하는 게 없다면, 텍스트 포인터를 +1 한다.
            }
        }
        // 검색

        if (pp == pattern.length()) {
            return tp - pp;
        } else {
            return -1;
        }

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("text : ");
        String text = sc.next();

        System.out.println("pattern : ");
        String pattern = sc.next();

        int idx = kmpMatch(text, pattern);

        if (idx == -1) {
            System.out.println("일치하는 패턴이 없어요.");
        } else {

            int length = idx + pattern.length();
            System.out.println((idx + 1) + "번째 문자부터 일치합니다.");
            System.out.println("텍스트 : " + text);
            System.out.printf("pattern : " + text.substring(idx, length));


        }

    }
}