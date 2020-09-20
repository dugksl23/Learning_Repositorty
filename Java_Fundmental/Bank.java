package Secodn_Weeks_review;

import java.util.Scanner;

public class Bank{

	
	
	static int total=0;
	
	public Bank() {//Bank의 생성자


		public static void main(String[] args) {

		
			int deposit, withdraw;
			total=0;
			
		Scanner sc=new Scanner(System.in);

		menu:for(;;) {
			int menu=0;
			System.out.print(""
					+"\r"
					+"<< KH Bank >>\n"
					+"1. 입금\r"
					+"2. 출금\n"
					+"3. 잔액조회\r"
					+"4. 종료\r"
					+">> ");
			try { // //=== menu input ====
				menu=Integer.parseInt(sc.nextLine()); 
			}catch(Exception e) {
				System.out.println("입력 범위를 벗어났습니다.\r확인 후 다시 입력하여 주세요.");
			}

			switch(menu) {
			case 1: // == deposit 입금 input
				deposit:while(true) { //== 재입금을 위한 무한루프
					System.out.println("<< 입금할 금액을 입력해주세요. >>");
					System.out.print(">> ");
					try {
						deposit=Integer.parseInt(sc.nextLine()); 
						if(deposit<=0) {
							System.out.println("금액을 재확인 후에 입력해주세요.");
							continue menu;//재입금을 유도하는 continue;

						}else {
							total=total+deposit;
						}
						continue menu;
					}catch(Exception e) {														
						System.out.println("입력 범위를 벗어났습니다.\r확인후 다시 입력해주세요.");					
						continue deposit;
						//try-cath는 if문의 역할과 비슷하다. 다만, if문은 조건의 분기점의 역할을 하기에
						//해당 범위 이내의 문제를 대상에게 강제하기 위한 용도로는 try-catch 구문이 적절하다.
					}

				}
			case 2://=== 출금용 input part
				withdraw:while(true) { //== 재입금을 위한 무한루프
					System.out.println("<< 출금할 금액을 입력해주세요. >>");
					System.out.print(">> ");
					try {
						withdraw=Integer.parseInt(sc.nextLine()); 
						if(withdraw>total) {
							System.out.println("잔액을 초과하였습니다. 다시 확인 후 출금하여 주십시오.");
							continue menu;//재입금을 유도하는 continue;
						}else {
							total=total-withdraw; // 출금 금액보다 잔액이 적다면 원금에서 출금액을 차감.
						}
						continue menu;
					}catch(Exception e) {														
						System.out.println("입력 범위를 벗어났습니다.\r확인후 다시 입력해주세요.");					
						continue withdraw;
					}
				}
			case 3:
				System.out.println("<< 잔액 조회 >>");
				System.out.print("현재 잔액은 : "+total+"\r");
				continue menu;
			case 4:
				System.out.println("<< 종료 >>");
				System.exit(0);
			}

		}
	}
}
