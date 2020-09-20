package Java_Fundmental_Program;

import java.util.Scanner;

public class Calculator{
	
		public static void main(String[] args){
		
		int left,right;
		String op;

		Scanner sc=new Scanner(System.in);



		menu:for(;;) {

			System.out.print(""
					+ "<< 계산기 프로그램 >>\r"
					+ "연산자 기호를 입력해주세요. ( +, -, *, / 종료[Q/q])\r"
					+ ">> ");

			op=sc.nextLine();//nextLine 메소드는 String의 리턴값을 가지고 있다.
			if(op.contentEquals("q")||op.contentEquals("Q")) {
				System.out.println("종료");
				System.exit(0);
				System.exit(0);
			}else if(!op.contentEquals("+")&&!op.contentEquals("-")&&!op.contentEquals("/")&&!op.contentEquals("*")) {
				System.out.println("연산자의 범위 내에서 선택하여 주십시오.");
				continue menu;
			}


			System.out.println("<< 좌항과 우항의 값을 입력해주세요. >>");//=== 연산자 파트 ===

			while(true) {
				try {
					System.out.print("좌항 : ");
					left=Integer.parseInt(sc.nextLine());//좌항의 입력값.
					break; //true라면 나가는 것이다.
				}catch(Exception e) {
					System.out.println("숫자를 기입하여 주세요.");
					continue;
				}
			
			}

			while(true) {
				try {
					System.out.print("우항 : ");		
					right=Integer.parseInt(sc.nextLine());//우항의 입력값.
					break;
				}catch(Exception e) {
					System.out.println("숫자를 기입하여 주세요.");
					continue;
				}
				
			}
			
			
			System.out.println("<< 결과  >>");
			switch(op) {
			case "+":System.out.println(left+right);
			break;
			
			case "-":System.out.println(left-right);
			break;
			
			case "*":System.out.println(left*right);
			break;
			
			case "/":System.out.println(left/right);
			break;
			
			//case 별로 중괄호를 열고 닫을 수 있으며,그차이는 없으며 break가 필요하다.
			


		}
		}
}

}
