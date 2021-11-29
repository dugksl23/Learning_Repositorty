package Sorting;

import java.util.Arrays;
import java.util.Scanner;

public class QuickSort {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("단순 선택 정렬");
        System.out.print("요솟수 : ");
        int input = scanner.nextInt();
        int[] arr = new int[input];

        for (int i = 0; i < arr.length; i++) {
            System.out.println("아무 숫자나 입력");
            arr[i] = scanner.nextInt();
        }

        quickSort(arr, 0, input - 1);
        System.out.println("오름차순 정렬 됩니다.");
        for (int i : arr) {
            System.out.println(i);
        }


    }


    public static void quickSort(int[] arr, int left, int right) {

        int pl = left; //0번 인덱스
        int pr = right; // 배열의 마지막 인덱스
        int pivot = arr[(pl + pr) / 2];

        do {
            while (arr[pl] < pivot) { // 1. pl은 pivot 보다 작은 값이 나올 때까지 좌측에서 우측으로 +1 씩 연산
                pl++;                 //    while이 멈치는 최종값은, pivot 보다 큰 배열의 index
            }
            System.out.println("마지막 pl : " + pl);

            while (arr[pr] > pivot) { // pr은 pivot 보다 클 때까지, 우측에서 좌측으로 -1 연산한다.
                pr--;                 // while이 멈치는 최종값은, 우측에서 pivot보다 작은 배열의 index
            }
            System.out.println("마지막 pr : " + pr);
            if (pl <= pr) swap(arr, pl++, pr--);
            // 교환 조건
            // 1. pr값이 pl값보다 높은 인덱스를 가지고 있다면 인덱스 요소값을 교환
        } while (pl <= pr);
        //  종료 조건
        //  pr이 pr보다 더 높은 인덱스를 가지고 있다면 pivot을 기준으로 이상 이하 그룹으로 나뉘었다는 뜻.


        if (left < pr) quickSort(arr, left, pr); // 1. 좌측 : pr이 x[0]보다 오른쪽에 있다면(left < pr) 왼쪽 그룹을 나눕니다.
        if (pl < right) quickSort(arr, pl, right); //2.  우측 : pl이 x[8]보다 왼쪽에 있다면(pl < right) 오른쪽 그룹을 나눕니다.
        Arrays.stream(arr).forEach(System.out::println);


    }

    public static void swap(int[] arr, int idx1, int idx2) {

        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }
}
