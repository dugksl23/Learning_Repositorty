package Java_Fundmental_Program;

import java.util.Scanner;

public class Rock_paper_scissors {

	//public static void main(String[] args) {
	public Rock_paper_scissors() {
		int user, com;	

		Scanner sc=new Scanner(System.in);

		main:for(;;) {

			System.out.print("<< 가위 바위 보 게임 >>\r"
					+"1.가위 2.바위 3.보 4.종료\r>> ");
			try {
				user=Integer.parseInt(sc.nextLine());
			}catch(Exception e) {
				System.out.println("입력 범위를 벗어났습니다.");
				continue main;
			}
			com=(int)(Math.random()*(3-1+1)+1);

			switch(user) {				//플레이어별로 경우의수가 3가지이다.switch 문을 통해서 경우의 수를 만들고
			//두 대상을 if문을 통해서 비교해라.
			
			case 1:System.out.println("당신은 가위를 냈습니다.");
			break;
			case 2:System.out.println("당신은 바위를 냈습니다.");
			break;
			case 3:System.out.println("당신은 보를 냈습니다.");
			break;
			default:
				System.out.println("<< 종료합니다. >>");
				System.exit(0);
			}


			switch(com) {				//플레이어별로 경우의수가 3가지이다.switch 문을 통해서 경우의 수를 만들고
			//두 대상을 if문을 통해서 비교해라.
			case 1:System.out.println("컴퓨터는 가위를 냈습니다.");
			break;
			case 2:System.out.println("컴퓨터는 바위를 냈습니다.");
			break;
			case 3:System.out.println("컴퓨터는 보를 냈습니다.");
			break;
			}

			if(user==com) {
				System.out.println("==========");
				System.out.println("비겼습니다.");
			}else if(user<com) {
				System.out.println("==========");
				System.out.println("당신이 이겼습니다.");
			}else {
				System.out.println("==========");
				System.out.println("컴퓨터가 승리하였습니다.");
			}
			System.out.println("==========");
			
			continue main;

		}
	}
}
