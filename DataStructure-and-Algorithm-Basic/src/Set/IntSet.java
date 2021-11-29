package Set;

public class IntSet {

    private int currentSize;
    private int max;
    private int[] set;

    public IntSet() {
    }

    public IntSet(int capacity) {
        this.currentSize = 0; // 집합 속에 입력된 요소의 갯수
        this.max = capacity; // 집합의 최대 크기
        this.set = new int[max];
    }


    public int capacity() {

        return max;

    }

    public int currentSize() {
        return currentSize;
    }

    // 입력된 value의 index를 반환
    public int indexOf(int value) {

        for (int i = 0; i < set.length; i++) {
            if (set[i] == value) {
                return i;
            }
        }
        return -1;
    }

    // 집합에서 n이 존재하는지 확인
    public boolean contains(int value) {

        return indexOf(value) != -1;
        // 존재한다면 true, 없으면 false


    }

    // 집합에서 n을 추가
    public boolean add(int value) {

        if (currentSize >= max || contains(value)) {
            //currentSize(입력된 요솟수)가 max보다 크거나,
            // 입력된 value 가 존재한다면 false
            // => 가득 찼거나 이미 n이 존재한다면 false를 return
            return false;

        } else {

            set[currentSize++] = value;
            // 가장 마지막 자리에 추가한다.

            return true;
        }
    }

    // 집합에서 n을 삭제
    public boolean remove(int value) {

        int index = 0;  // n이 저장된 요소의 index


        if (currentSize <= 0 || (index = indexOf(value)) == -1) {
            // currentSize가 0보다 작고, 삭제할 value가 -1이라면...
            // => 배열이 비어있거나, index 내에 value가 존재하지 않는다.

            return false;

        } else {

            set[index] = set[--currentSize];
            // 마지막 요소를 삭제한 곳으로 옮긴다.

            // indexOf를 통해서 삭제할 value를 마지막 요소로 옮긴다..
            // 물론 마지막 currentSize에 위치한 index에는 값이 25로 그대로 있다.
            // 다만 다음에 add될 곳의 index는 -1 된 currentSize에 대입이 되기에
            // 25가 있던 6번 index에 새로 add 된 숫자를 덮어쓰게 되는 것이다.

            return true;
        }
    }

    public String toString() {
        StringBuffer temp = new StringBuffer("{");

        for (int i = 0; i < currentSize; i++) {
            temp.append(set[i]);
        }

        temp.append("}");
        return temp.toString();
    }


    // 현재 집합을 집합 s에 복사.
    public boolean copyTo(IntSet b) {
        // *전제는 복사가 이뤄지는 곳으 배열의 max 값에는 변동이 없어야 한다는 것이다.

        //1. 복사할 요소 개수
        //   this의 currentSize가 s의 max보다 크다 할지라도, s의 max를 return 한다.
        //   만약 s의 max가 크다면, a의 currentSize를 return 하고 for문을 돌면 된다.
        int n = (b.max < currentSize) ? b.max : currentSize;

        // 2. s의 크기(index의 수)에 따라서 for 문을 돌면서,
        //    s 배열의 요소값에 하나씩 복사.
        for (int i = 0; i < n; i++) {
            b.set[i] = set[i];
        }

        // 현재 배열
        b.currentSize = n;
        // -> a의 max가 b의 currentSize보다 작다면, b의 max를 유지한채로
        //    for문을 돌려서 a의 요소값들을 b 배열로 옮기면 된다.
        return false;
    }

    // s를 배열을 현재 배열에 복사.
    public boolean copyFrom(IntSet b) {
        // 1. b의 배열을 a의 배열로 복사하는 것이기에 a의 max는 고정이여야 한다.

        // 2. a의 max가 b의 currentSize보다 작다 할지라도, a의 max값을 유지.
        //    b의 currentSize가 a의 max값보다 작다면, a의 max를 유지한 채로, b의 currentSize 만큼
        //    for문을 돌면서 b의 currentSize만큼, a 배열에 복사한다.
        int n = (b.currentSize > max) ? max : b.currentSize;
        for (int i = 0; i < n; i++) {
            set[i] = b.set[i];
        }

        // 3. currentSize는 a.max 또는 b의 currentSize가 된다.
        currentSize = n;
        return false;
    }

    // 집합 s와 현재 집합이 같은지 확인.
    public boolean equalTo(IntSet s) {

        if (currentSize != s.currentSize) {
            // 요소 개수가 같지 않으면 집합도 같지 않다.
            return false;

        } else {
            // 현재 배열의 0번부터 마지막 요소까지 돌면서
            for (int i = 0; i < currentSize; i++) {
                // a 배열을 i, b 배열을 j로 하여, a 배열의 i번 index를 기준으로
                // b 배열의 최대요소까지(j)를 선형검색으로 돌린다.
                int j = 0;

                // 현재 배열의 0번 요소값부터, 비교배열의 0번부터 미자믹 요소값까지 비교한다.
                for (; j < s.currentSize; j++) {
                    // 같을 경우에는 for문에서 나오고, j++한다.
                    if (set[i] == s.set[j]) {
                        break;
                    }
                }
                // j의 마지막 인덱스(currentSize-1) 값과
                // b 배열의 currentSize가 같으면
                // false 이지만 그럴 경우는 없다.
                if (j == s.currentSize) {
                    return false;
                }
            }
        }
        // 기본 return 값은 true이며, 배열의 크기와 요소값만 같다면 순서는 상관없다.
        return true;
    }

    // 집합 s1과 s2의 합집합을 구한다.
    public void union(IntSet s1, IntSet s2) {
        // 총 3개의 배열이 있는 셈이고, 인자로 s1과 s2의 합집합을 구하기 위해
        // s1을 먼저 합집합 배열에 복사를 한다.
        copyFrom(s1);
        // 그리고 합집합 배열에 s2의 currentSize까지 돌면서
        // s2의 값을 합집합 배열에 add를 한다.
        for (int i = 0; i < s2.currentSize; i++) {
            add(s2.set[i]);
        }

    }


}
