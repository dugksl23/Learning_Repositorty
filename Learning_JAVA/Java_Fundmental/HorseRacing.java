package Java_Fundmental_Program;

import java.util.Scanner;

public class HorseRacing {

	public static void main(String[] args) {

		int menu, horse_choice, betting = 0, deposit, total = 0; 
		int win_horse, win_com; //우승한 말과 사용자의 말을 비교하기 위한 변수
		int horse1, horse2, horse3;
		String first = null;
		Scanner sc=new Scanner(System.in);

		main : for(;;) {
			System.out.printf("\r<< 경마 게임 >>\r"
					+"1. 게임 시작\r"
					+"2. 잔액조회\r"
					+"3. 잔액 충전\r"
					+"4. 게임 종료\r"
					+">>");
			try {
				menu=Integer.parseInt(sc.nextLine()); //== menu input용
			}catch(Exception e) {
				System.out.println("입력 대상이 아닙니다.\r");
				continue main;
			}

			switch(menu) {
			case 1:
				choice_horse:while(true) {
					System.out.println("<< 경주마를 선택해주세요. >>");
					System.out.println("1.적토마 2.우승하지마 3.확!마 ");
					try {
						horse_choice=Integer.parseInt(sc.nextLine());

					}catch(Exception e) {
						System.out.println("입력 대상이 아닙니다.\r확인 후 다시 입력해주세요.");
						continue choice_horse;
					}

					System.out.print("<< 배팅 금액을 입력해주세요. >>\r금액 : ");


					betting:while(true) {
						try {
							betting=Integer.parseInt(sc.nextLine());
							break;
						}catch(Exception e) {
							System.out.println("입력 대상이 아닙니다.");
							continue betting;
						}
					}

					if(betting>total) {
						System.out.println("잔액을 초과하였습니다.\r금액을 충전해 주십시오.");
						continue main;
					}

					horse1=(int)(Math.random()*(100-1+1)+1);
					horse2=(int)(Math.random()*(100-1+1)+1);
					horse3=(int)(Math.random()*(100-1+1)+1);

					if(horse1>horse2&&horse1>horse3) {
						win_horse=horse1;
						win_com=1;
						first="적토마";
						total+=betting;
					}else if(horse2>horse1&&horse2>horse3) {
						win_horse=horse2;
						win_com=1;	
						first="우승하지마";
						total+=betting;
					}else if(horse3>horse1&&horse3>horse2){
						win_horse=horse3;
						win_com=1;	
						first="확!마";
						total+=betting;
					}

					switch(horse_choice){
					case 1: System.out.printf("(%s)가 1등으로 들어오고 있습니다.\r", first);
					System.out.printf("(%d)원의 상금을 획득하셨습니다.", betting*2);
					break;
					case 2:	System.out.printf("(%s)가 1등으로 들어오고 있습니다.\r", first);
					System.out.printf("(%d)원의 상금을 획득하셨습니다.", betting*2);
					break;
					case 3 :System.out.printf("(%s)가 1등으로 들어오고 있습니다.\r", first);
					System.out.printf("(%d)원의 상금을 획득하셨습니다.", betting*2);
					break;
					}
					continue main;
				}
			case 2:
				System.out.println("<< 잔액 조회 >>");
				System.out.printf("현재 잔액은 (%d) 원입니다.", total);
				continue main;
			case 3:
				deposit:while(true) {
					System.out.println("<< 잔액 충전 >>");
					System.out.print("충전 금액 : ");
					try {
						deposit=Integer.parseInt(sc.nextLine());
						total+=deposit;
						continue main;
					}catch(Exception e){
						System.out.println("다시 확인 후, 입력해주세요.");
					}
					continue deposit;
				}
			case 4:System.out.println("<< 종료 >>");
			System.exit(0);
			break;	
			}

		}
	}

}
