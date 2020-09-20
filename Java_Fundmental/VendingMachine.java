package Java_Fundmental_Program;

import java.util.Scanner;



public class VendingMachine {
	
	public VendingMachine() {
	//public static void main(String[] args) {
		int total=Bank.total;
		int menu = 0, order;
		int coke = 1000, sprite = 800, masil = 1500;	
		int cokeCnt = 0, spriteCnt = 0, masilCnt = 0;
		Scanner sc=new Scanner(System.in);

		menu:for(;;) {
			System.out.println("<< 자판기 시뮬레이터 >>\r"
					+"1.주문하기\r"
					+"2.잔액조회\r"
					+"3.종료하기\r"
					+"[0.소지품 확인]");
			System.out.print(">> ");
			try {
				menu=Integer.parseInt(sc.nextLine());//사용자 order input
			}catch(Exception e) {
				System.out.println("입력 대상이 아닙니다.\r확인 후 다시 입력해주세요.");
				continue menu;
			}

			switch(menu) {
			case 1:
				order:while(true) {
					System.out.printf("<< 음료수를 골라주세요.>> \r"
							+ "1.콜라(%d) 2.스프라이트(%d) 3.멕주(%d)\n"
							+ ">> ", coke,sprite,masil); //(%d)숫자의 대상을 10진수로 표기.
					try {
						order=Integer.parseInt(sc.nextLine()); // ===oder input
					}catch(Exception e) {
						System.out.println("입력 대상이 아닙니다.\r다시 확인후 입력해주세요.");
						continue order;
					}

					if(order==1) {
						if(total<coke) {
							System.out.println("잔액을 초과하였습니다.\r잔액을 충전 후, 다시 이용해주세요.");
							continue menu;
						}else {
						System.out.printf("<< 콜라를 구매하셨습니다. >>\r"
								+"콜라(%d)", coke);
						cokeCnt++;
						total=total-coke;	
						continue menu;
						}
					}else if(order==2) {
						if(total<sprite) {
							System.out.println("잔액을 초과하였습니다.\r잔액을 충전 후, 다시 이용해주세요.");
							continue menu;
						}else {
						System.out.printf("<< 스프라이트를 구매하셨습니다. >>\r"
								+"스프라이트(%d)", sprite);
						spriteCnt++;
						total=total-sprite;	
						continue menu;
						}
					}else if(order==3) {
						if(total<sprite) {
							System.out.println("잔액을 초과하였습니다.\r잔액을 충전 후, 다시 이용해주세요.");
							continue menu;
						}else {
						System.out.printf("<< 스프라이트를 구매하셨습니다. >>\r"
								+"스프라이트(%d)", sprite);
						spriteCnt++;
						total=total-sprite;	
						continue menu;
						}
					}
				}
					
			case 2:
				System.out.println("<< 잔액 조회 >>");
				System.out.print("현재 잔액은 : "+total+"\r");
				continue menu;
			case 3:
				System.out.println("<< 종료 >>");
				System.exit(0);
			case 4:
				System.out.printf("<< 소지품 확인 >>\r"
						+"1.콜라(%d) 2.스프라이트(%d) 3.매실차(%d)\r>>", cokeCnt, spriteCnt, masilCnt);
				continue menu;	
			}
		}


	
	}
}
