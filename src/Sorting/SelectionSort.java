package Sorting;

import java.util.Scanner;

public class SelectionSort {

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

        selectionSort(arr, input);
        System.out.println("오름차순 정렬 됩니다.");
        for (int i : arr) {
            System.out.println(i);
        }



    }

    public static void selectionSort(int[] arr, int input) {

        for (int i = 0; i < input; i++) { // 1. 배열의 총 요소수 만큼 비교를 한다.
            int min = i;                  // 2. 첫번째 요소의 값을 최소의 값이라고 가정한다.
            for (int j = i; j < input; j++) {
                if (arr[j] < arr[min]) { // 3. 첫번째 요소의 값을 최소값이라고 두고, 해당 값보다 작은 값들을 찾아서 다시 배열의 요소를 for문을 돈다.
                    min = j;             // 4. 0번째 요소값보다 작은 요소의 값이 나온 index를 min에 대입한다. 바뀐 후의 arr[min]이 요솟값이 가장 큰값이 된다.
                                         // 5. min에 대입된 요소의 인덱스는 중요하지 않다. 다만 그 숫자가 가장 큰 숫이고, 그 숫자보다 작은 값이
                                         //    0번부터 for문을 도는 요소값 arr[i]가 arr[min]의 값보다 작다면, 그자리에서
                                         //    for문을 도는 현재 j 값에 해당하는 요소와 min 요소값을 교환한다.
                }
                swap(arr, i, min); // 5. 비교값들끼리 swap을 진행한다.
            }
        }

    }

    public static void swap(int[] arr, int priorMin, int currentMin) {

        int temp = arr[priorMin];
        arr[priorMin] = arr[currentMin];
        arr[currentMin] = temp;
    }

}