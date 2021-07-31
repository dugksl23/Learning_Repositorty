package Sorting;

import java.util.Scanner;

public class BubbleSort {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("배열의 Length를 설정해주세요.");
        int length = sc.nextInt();

        int[] arr = new int[length];

        for (int i = 0; i < arr.length; i++) {
            System.out.println("아무 숫자나 입력");
            arr[i] = sc.nextInt();
        }

        bubbleSort(arr, length);
        System.out.println("오름차순 정렬 됩니다.");
        for (int i : arr) {
            System.out.println(i);
        }


    }


    public static void bubbleSort(int[] x, int input) {

        for (int i = 0; i < input; i++) {//1. length만큼 모두다 검사를 한다.
            for (int j = input - 1; j > i; j--) {// 2. bubleSort는 제일 마지막 index에서부터 검색을 시작한다.
                // 마지막에서부터 한칸씩 비교해가면서 swap을 한다.
                if (x[j - 1] > x[j]) {
                    swap(x, j-1, j);
                }
            }
        }

    }


    public static void swap(int[] x, int a, int b){

        int temp = x[a];
        x[a] = x[b];
        x[b] = temp;
    }

}
