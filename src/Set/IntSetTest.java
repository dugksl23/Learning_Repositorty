package Set;

public class IntSetTest {

    public static void main(String[] args) {

        IntSet set = new IntSet(5);
        IntSet arr = new IntSet(5);
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);

        set.add(5);
        set.add(6);
        set.add(7);
        set.add(8);
        set.add(9);
        set.add(10);

        System.out.println(set.copyTo(arr));
        System.out.println(set.toString());
        System.out.println(arr.toString());







    }

}
