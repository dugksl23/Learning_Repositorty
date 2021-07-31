package Sorting;

import java.util.Arrays;
import java.util.Scanner;

public class Partiiton {

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

        partionSort(arr, input);
        System.out.println("오름차순 정렬 됩니다.");
        for (int i : arr) {
            System.out.println(i);
        }


    }


    public static void partionSort(int[] arr, int length) {

        int pl = 0; //0번 인덱스
        int pr = length - 1; // 배열의 마지막 인덱스
        int pivot = arr[length / 2];

        do {
            while (arr[pl] < pivot) {//[5,4,1,2,3]
                pl++;
                System.out.println(pl);
            }
            // pl = 5
            while (arr[pr] > pivot) {
                pr--;
            }
           // pl = 2
            if (pl <= pr) { //pl(피벗보다 큰값)이 pr(피벗보다 작은 값)보다 크거나 같을 동안 swap을 한다.
                swap(arr, pl++, pr--);
            }
        } while (pl <= pr); // 분할이 pr(우측) 값이 pl(좌측)보다 클동안 계속 된다. => pl값이 우측으로 분할된다는 뜻.
        System.out.println("피벗의 값은 " + pivot + "입니다.");
        Arrays.stream(arr).forEach(System.out::println);


    }

    public static void swap(int[] arr, int idx1, int idx2) {

        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("배열을 나눕니다.");
//        System.out.print("요솟수 : ");
//        int input = scanner.nextInt();
//        int[] x = new int[input];
//
//        for (int i = 0; i < input; i++) {
//            System.out.print("x[" + i + "] : ");
//            x[i] = scanner.nextInt();
//        }
//
//        partition(x, input);
//    }
//
//    public static void partition(int x[], int input) {
//        int pl = 0;
//        int pr = input - 1;
//        int pivot = x[input / 2];
//
//        do {
//            while (x[pl] < pivot) pl++;
//            while (x[pr] > pivot) pr--;
//            if (pl <= pr) swap(x, pl++, pr--);
//        } while (pl <= pr);
//
//        System.out.println("피벗의 값은 " + pivot + "입니다.");
//
//        System.out.println("피벗 이하의 그룹");
//        for (int i = 0; i <= pl - 1; i++) {
//            System.out.println(x[i] + " ");
//        }
//        System.out.println();
//
//        if (pl > pr + 1) {
//            System.out.println("피벗과 일치하는 그룹");
//            for (int i = pr + 1; i <= pl - 1; i++) {
//                System.out.println(x[i] + " ");
//            }
//            System.out.println();
//        }
//
//        System.out.println("피벗 이상의 그룹");
//        for (int i = pr + 1; i < input; i++) {
//            System.out.println(x[i] + " ");
//        }
//        for (int i =  0; i < input; i++) {
//            System.out.println(x[i] + " ");
//        }
//
//
//    }
//
//    public static void swap(int[] x, int idx1, int idx2) {
//        int temp = x[idx1];
//        x[idx1] = x[idx2];
//        x[idx2] = temp;
//    }
}



