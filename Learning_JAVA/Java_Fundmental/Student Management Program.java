package Day_11_OV;

import java.text.DecimalFormat;
import java.util.Scanner;

import Day_10_OOP.Student_02;

public class Assingment_01_0909 {   
	private static Scanner sc=new Scanner(System.in); //== 스캐너를 static 변수로 고정하여 메소드에 호출.

	public static int inputNum(String str) {

		for(;;) {  //num의 올바른 입력을 위한 유효성 검사 무한 루프.
			try {
				System.out.print(str); //전달받은 str 매개변수의 "~~해주세요."를 전달받고 출력.
				int num=Integer.parseInt(sc.nextLine()); //input 변수에서 문자열을 return받음.
				return num;                              //int형 num을 return
			}catch(Exception e) {	                 // 문자열 예외 강제.
				System.out.println("숫자를 입력해주세요."); 
			}
			// 문자 or 문자열 입력시에는 for문으로 회귀.
	 }	
	}
	// ==> years를 입력 받을 때도 int형을 리턴시키기 때문에 호출가능.

	//위의 코드는 years를 받기 위한 코드.
	//아래의 코드는 학점을 받기 위해서 실수형이 필요하여 double형의 return 메소드는 만듬.
	//인자의 형식을 다르게 하여 호출할 수 있지만 return 데이터 타입이 다르므로, overloading은 불가.
	// 새로 만듬.
	public static double inputYears(String str ) { // static은 이유는? 그이유는 바로 main에서 호출하며 static이외의 intance 멤버메소드 또는 변수를 불러올 때는 클래스 자체가 객체화 되어야지만 가능하다.

		for(;;) { //유효성 검사를 무한루프
			try {	// 숫자이외의 문자열 강제
				System.out.println(str);	
				double num=Double.parseDouble(sc.nextLine()); // 전달받은 학번용 변수(Year)의 전달받음. 
				return num;									  // years를 return
				// 유효성 검사에서 true일 시, num을 return하기에
				// break; 필요없음.
			}catch(Exception e) {
				System.out.print("학점을 입력해주세요.");
		}

	 }
	}

	public static void main(String[] args) {


		Student_02[] st=new Student_02[5];  // Student_02 설계도를 이용한 배열의 인스턴스 변수 선언. 인스턴스화한 클래스의 주소값을 배열에 담는다.
		int input=0; 						// menu input용 변수
		int index=0;   						// 입력한 학생수에 따라서 증가하는 배열 index 번호

		menu:for(;;){//menu, 전체 무한루프
		    System.out.println("<< 학생부  >>");
			System.out.print("1.신규 정보 등록(최대 5명까지 등록)\r"
					+ "2.학생 목록 출력\r"
					+ "3.프로그램 종료\r");
			// == menu, input 입력 및 메소드호출
			input=inputNum(">> ");  

			// 메뉴 input용에 근거하여 switch문
			switch(input) {

			case 1: //== 신규정보 등록 part
				maxFive:for(;;) { // == 신규정도등록 무한루프	
					if(index>4) { // 5명(인덱스 4)초과 등록시 menu로 회귀시키기 위한 유효성 검사.
						System.out.println("신규 학생 정보 등록은 최대 5명까지 가능합니다."); //5명 초과시 출력
						break;   // 이 break는 5명이 초과하였을 경우, 가장 가까운 for문(maxFive)를 탈출하기 위한 용도.
					}

					String name="";			// 이름 입력용 변수	
					int years=0;  			// 학번 입력용 변수	
					String department="";	// 학과 입력용 변수
					double grade=0;			//학점을 입력용 변수


					//== 입력 part 시작.
					System.out.print("이름을 입력해주세요 :");
					name=sc.nextLine(); // 이름을 입력받을 변수에 inputName 메소드 호출.
					// String(키보드 문자열)을 입력받기 때문에 예외 처리 (無).
					years=inputNum("학번을 입력해주세요 : ");// 인자로 "학번입력"을 보내고 출력받음.
					// 메소드호출하여 int returne 받는다.

					System.out.print("학과 입력해주세요 : ");
					department=sc.nextLine(); //학번용 변수, String(키보드 입력범위)라서 예외처리 및 for(;;)문 필요없음.

					grade=inputYears("학점을 입력해주세요 : "); //인자로 String형(return type 실수)를 전달, 유효성 검사후
					//유효성 검사 후 메소드를 호출하고, double형 타입 return받음. 
					st[index++]=new Student_02(name, years, department, grade); 
					//Student_02 class(설계도)를 객체화하여, 생성자에게 4개의 인자를 전달.
					//Student_02 class 참조형 데이터타입의 배열을 선언(heap에 저장된 data의 주소값 생성)
					//배열의 인덱스[0]에 new를 통해 instance화된 st의 주소값을 담음.
					break; // case 1 의 input 종료시,break에서 가장 가까운 for(maxFive)문을 나가고, menu로 점프. 
					// menu로 점프
				}
			break; //case 1를 나가는 브레이크
			case 2:
		            System.out.println("<< 학생부 출력 >>");
		            if(index>0) {   // 한명이라도 입력 받으면 index는 1 이상
		               for(int i=0; i<index; i++) {

		                  System.out.printf("이름 : %s\t학번 : %d\t학과 : %s\t학점 : %.2f\r",
		                        st[i].getNames(), st[i].getYears(), st[i].getDepartment(), st[i].getGrade());
		               }
		            }else {
		               System.out.println("등록된 최신 정보가 없습니다.");
		            }
		            break;
 
			case 3:
			System.out.println("<< 종료 >>");
			System.exit(0);
			
		}
		
  }
 }
}
