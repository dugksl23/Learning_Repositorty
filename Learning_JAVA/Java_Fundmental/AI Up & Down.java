package Java_Fundmental_Program;

import java.util.Scanner;

public class Ai_up_down {

	public static void main(String[] args) {

		int max, min;
		int user, com_user = 0, com_ran;
		int menu;
		int current_score = 0, bestscore = 0;
		Scanner sc=new Scanner(System.in);

		menu:for(;;) {

			System.out.printf(""
					+ "\r<< Up & Down >>\r"
					+ "1.Game Start\r"
					+ "2.Game Score\r"
					+ "3.Game End\r"
					+ ">>");
			try {
				menu=Integer.parseInt(sc.nextLine());
			}catch(Exception e) {
				System.out.println("입력 대상이 아닙니다.\r확인 후에 다시 입력해주세요.");
				continue menu;
			}

			switch(menu) {
			case 1:

				com_ran=(int)(Math.random()*(100-1+1)+1); //랜덤수 생성.
				System.out.printf("<< 100 이내의 수가 생성되었습니다. [%d]>>", com_ran);

				user_input:for(;;) { /// === player turn ===
					System.out.print("\r<< 숫자를 입력해주세요. >>\r"
							+ ">>");
					try {
						user=Integer.parseInt(sc.nextLine());
						current_score++;
					}catch(Exception e) {
						System.out.println("입력 대상이 아닙니다.\r다시 입력해주세요.");
						continue user_input; 
					}

					if(user==com_ran) {
						System.out.printf("당신이 이기셨습니다.\r랜덤 수는 (%d)입니다.", com_ran);
						System.out.printf("");
						if(current_score<bestscore||bestscore==0) {
							bestscore=current_score;
						}
						//						
						//						if(score<new_score) {   //기존 스코어보다 현재스코어다 작을 경우에 대입한다. 
						//							score=new_score;


						continue menu;
					}else if(user>com_ran) {
						System.out.println("<< DOWN >>");
						max=user-1;
						com_user=(int)(Math.random()*(max-1+1)+1);

					}else{
						System.out.println("<< UP >>");
						min=user+1;
						com_user=(int)(Math.random()*(100-min+1)+min);
					}



					if(com_user==com_ran) {/// === com_user turn ===
						System.out.printf("user1이 이기셨습니다.\r랜덤 수는 (%d)입니다.\r", com_ran);
						System.out.printf("");
						continue menu;
					}else if(com_user>com_ran) {
						System.out.println("\r<< user가 입력하였습니다. >>");
						System.out.println("<< DOWN >>");
					}else{
						System.out.println("\r<< user가 입력하였습니다. >>");
						System.out.println("<< UP >>");
					}
				}
			case 2:
				System.out.println(String.format("현재 스코어는 %s회입니다.\r", bestscore));	
				continue menu;	
			case 3:
				System.out.println("<< 게임 종료 >>");
				System.exit(0);	
			}

		}
	}

}
