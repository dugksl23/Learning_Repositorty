package Day_11_OV;

import java.util.Scanner;

import Day_10_OOP.Student_02;

public class Exam_03 {

	//학생 정보 검색.
	//검색기능.
	// 검색할 학생의 학번 :
	// 학번 이름 국어 영어
	// 1001 번 jack 10 30 if문을 통한 학번 검사를 통해 true면 모두 출력.  
		private static Scanner sc=new Scanner(System.in); //== 스캐너를 static 변수로 고정하여 메소드에 호출.

			
		public static int validInputNum(String str) {
		for(;;) {	
			try {
			System.out.print(str);	
			int num=Integer.parseInt(sc.nextLine());
			return num;
			}catch(Exception e){
				System.out.println("숫자를 입력해주세요.");
			}
		}
		}
		
		
		
		public static void main(String[] args) {
			New_Student_Vo[] st=new New_Student_Vo[5];
			String name; 
			int kor, eng, years, input, index = 0, searching=0;
			
		menu:for(;;) {
			System.out.print("<학생 관리 시스템>\r"
					+ "1.신규 정보 등록(최대 5명까지 등록 가능)]\r"
					+ "2.학생 목록 출력\r"
					+ "3.학생 정보 검색\r"
					+ "4.프로그램 종료\r");
			
			
			input=validInputNum(">>");
			
			if(input<=0&&input>=5) { 
			System.out.println("해당 입력 범위 내에서 숫자를 입력해주세요.");	
			continue;
			}
			
			switch(input) {
			
			case 1:
				if(index>=5) {
					System.out.println("신규 등록은 최대 5명까지 입니다.");
					continue;
				}
				System.out.print("이름을 입력해주세요 : ");
				name=sc.nextLine();
				
				years=validInputNum("학번을 입력해주세요 : ");
				kor=validInputNum("국어 점수를 입력해주세요 : ");
				eng=validInputNum("영어 점수를 입력해주세요 : ");
				st[index++]=new New_Student_Vo(name, years, kor, eng);
				
			break;
			case 2:
			System.out.println("<< 학생 목록 출력 >>");
			if(index==0) {
				System.out.println("등록된 학생 정보가 없습니다.");
			}else {
				for(int i=0; i<index; i++) {
					System.out.printf("학생 이름 : %s\t학 번 : %d\t영어 : %d\t수학 : %d\r", st[i].getName(),st[i].getYears(), st[i].getKor(),st[i].getEng());
				}
			}
			break;
			case 3:
			if(index==0) {
				System.out.println("등록된 학생이 없습니다.");
			}else {
					searching=validInputNum("학번을 입력해주세요 : ");//검색 대상을 
					for(int i=0; i<index; i++) {
						if(searching==st[i].getYears()) {
						System.out.printf("학생 이름 : %s\t학 번 : %d\t영어 : %d\t수학 : %d\r", st[i].getName(),st[i].getYears(), st[i].getKor(),st[i].getEng());
					} else {
						System.out.println("등록된 학생 번호가 없습니다.");
						continue menu;
					}
				break;	
				}			
			}
			break;
			case 4:
			System.out.println("<< 프로그램 종료 >>");	
			System.exit(0);
			}
			
			
			
//			
//			while(true) {
//				System.out.println("학번 : ");
//				String id=sc.nextLine();
//				if(id.contentEqauls(stds[i].getId()) {
//					break; //입력받은 아이디와 인덱스내에 저장된 각각의 주소값을 검색하여 일치하는 학번(String일 경우에만, 만약 int면 ==를 이영)이 있을 경우 break를 통해 가장 가까운 for문의 무한루 탈출;
//				}else if(id.contentEqauls(stds[i].getId()){
//					System.out.println("이미 존재하는 ID 입니다.");
//					continue;
//				}
//			}
//			
//			
			}	
		}
}
