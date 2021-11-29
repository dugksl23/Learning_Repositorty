package Sorting;

import java.util.Scanner;

public class InsertationSort {

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        System.out.println("배열의 Length를 설정해주세요.");
        int length = sc.nextInt();

        int[] arr = new int[length];

        for (int i = 0; i < arr.length; i++) {
            System.out.println("아무 숫자나 입력");
            arr[i] = sc.nextInt();
        }

        insertationSort(arr, length);
        System.out.println("오름차순 정렬 됩니다.");
        for (int i : arr) {
            System.out.println(i);
        }

    }

    public static void insertationSort(int[] arr, int length) {

        for (int i = 1; i < length; i++) {
            int j;
            int plusIndex = arr[i];
            for (j = i; j > 0 && arr[j - 1] > plusIndex; j--) { // for문을 돌면서 확인하는 게 아니라,
                // 이웃 요소의 값과 비교하는 if문의 역할을 하는 for문이다.
                // 한번만 쓰이고 버려진다.
                System.out.println("plusIndex : " + plusIndex);
                System.out.println("arr[j] : " + arr[j]);

                arr[j] = arr[j - 1]; // 즉 이전 요소값이 저 크기 때문에, 현재 요소값에 이전 요소값을 넣어준다.
                // 그리고 j는 -1 되기 떄문에, 3을 기준으로 하면 2,1,0까지 가면서 현재 요소값에
                // 이전 요소값 (큰값)을 대입하면서 swap을 진행한다.
                System.out.println("arr[j] : " + arr[j]);


            }
            arr[j] = plusIndex; // 그리고 이 로직은 무엇이냐고 하면, 위의 for문을 아예 타지도 않았을 때에는
            // 그 이전 요소값보다 currentIndex의 요솟값보다 작다는 것이므로
            // plusIndex의 값을 다시 현재 요소값으로 넣는다, 즉 여기까지 와야 swap이 완료.

            System.out.println(arr[j] + "최종 j의 값은?");
        }

    }

}
