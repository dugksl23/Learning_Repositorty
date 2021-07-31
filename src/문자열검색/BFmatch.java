package 문자열검색;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class BFmatch {

    // 브루트-포스트법으로 문자열 검색하는 메서드
    public static int bfMatch(String text, String pattern) {
        // 이 메소드는 결국, 찾고자 하는 단어의 길이가 일치하는 첫 문자열을 return 한다.
        int textPointer = 0; // text pointer
        int patternPointer = 0; // pattern pointer

        while (textPointer != text.length() && patternPointer != pattern.length()) {
            // 각각의 pointer 0번 인덱스부터 max까지 비교할때까지 while문이 도는 조건문이다.
            if (text.charAt(textPointer) == pattern.charAt(patternPointer)) {
                textPointer++;    // patter의 마지막글자까지 일치하는 카운트까지 ++한다.
                patternPointer++; //patterhnPointer는 length는 고정적이다.
            } else {
                textPointer = textPointer - patternPointer + 1;
                // if문을 타고, 일치하지 안으면
                System.out.println("TextPointer : " + textPointer);
                // tp = 검사할 위치를 1칸 뒤로 이동 +1
                patternPointer = 0;
                // pp = 는 검사를 중단하고 다시 0부터 검사 시작.
            }
        }
        if (patternPointer == pattern.length()) {// 검색 성공
            // 검색 문자열 길이와, patterPointer의 갯수가 동일하다는 것/ 찾았다는 것을 의미한다.
            System.out.println("검색 성공");
            System.out.println("textPointer : " + textPointer);
            System.out.println("patternPointer : " + patternPointer);
            System.out.println(textPointer - patternPointer);

            return (textPointer - patternPointer);
        }

        return -1;

    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("text : ");
        String text = sc.next();

        System.out.println("pattern : ");
        String pattern = sc.next();

        int idx = bfMatch(text, pattern);

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

