package Recur;

public class recur {

    public static void recur(int num){

        if(num>0){
            recur(num-1);
            System.out.println("입력된 숫자는 : "+num);

            recur(num-2);

        }

    }

    public static void main(String[] args){

        recur(5);

    }


}
