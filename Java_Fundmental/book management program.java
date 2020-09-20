package Day_11_OV;

import java.util.Scanner;

import Day_10_OOP.Library;


public class Assignment_02_0909 {
	private static Scanner sc=new Scanner(System.in); //scanner, 사실상 개발자용 전용 input용, 클래스 전용 private 전역변수로 활용.
	/* try-catch를 통한 유효성 검사. String은 키보드 내의 문자(숫자, 특수문자)를 대상으로만 입력받기 때문에 할 필요 없음.
	   int형 input에 한해서 try-catch를 통한 유효성 검사가 필요.
	 */
	
	public static int inputNum(String str) { // static은 이유는? 그이유는 바로 main에서 호출하며 static이외의 intance 멤버메소드 또는 변수를 불러올 때는 클래스 자체가 객체화 되어야지만 가능하다.
		for(;;) {     //int 형 유효성 검사를 위한 무한 루프
			int num; // return int형 변수
			try { // 숫자 이외의 문자열 강제
				System.out.print(str);
				num=Integer.parseInt(sc.nextLine()); // str 매개변수를 
				return num; //매개변수로부터 입력받은 값을 int num으로 return
			}catch(Exception e) { // 문자를 입력시 경고 메세지.
				System.out.println("숫자를 입력해주세요.");
			}
		//절자적으로 break;를 통한 for문 탈출, 다만 메소드 return이기에 불필요.	
		}
		
	}
	
	
	
	public static void main(String[] args) {	// 같은 형 자료를 모아놓은 모음집(배열) ex) Student[] str= Student 타입의 주소값을 0번배열부터 담음.
		Library[] li=new Library[5];           // class Library의 설계도로 new를 이용한 instance화(객체선언, 주소값이 부여), instance의 data는 heap에 저장, 주소값은 li 객체의 배열에 담김, 
		String bookCode, bookName; int price = 0, input, index = 0;  // input용 신규도서, 도서 코드, 도서 가격; menu input용 변수, 배열의 인덱스에 넣을 도서 정보 cnt;
	
		
	menu:for(;;) { 								 // menu로 돌아오기 위한 for(;;)
		System.out.print("=== 도서 관리 시스템 ===\r"  // menu
				+ "1.신규 도서 입력(최대 5권까지 가능)\r"
				+ "2.도서 목록 출력\r"
				+ "3.프로그램 종료\r");
		input=inputNum(">> ");
		
		if(input<1||input>3) { // menu input 범위 강제
			System.out.println("해당 범위를 벗어났습니다.");
			continue; //if문에서 해당 범위를 벗어났을 시, 가장 가까운 for(menu)문으로 회귀.
		} 
			
		switch(input) {
		case 1:
		if(index>=5) {   // 5권 이상 되었을 시 입력을 못하다록 강제 
		System.out.println("입력 가능한 도서 목록은 총 5권 입니다.");
		continue; // 5권 이상일 시 menu로 회귀.
				 // case 1번에 위치한 이유 : input 2번을 할 때, 도서 목록을 보여주기 위해서
				//절차적으로 input보다 후행적으로 실행이 되어야함.
		}	
		System.out.print("도서코드 입력해주세요. : ");
		bookCode=sc.nextLine(); // 도서코드 input part
		System.out.print("도서제목 입력해주세요. : ");
		bookName=sc.nextLine(); // 도서코드 input part
		input=inputNum("도서 가격 : "); // 도서 가격 input part, try-cath 유효성검사를 위한 메소드 호출 및 재활용
		li[index++]=new Library(bookCode, bookName, price);
		break;
		case 2:
		if(index==0) {
			System.out.println("등록된 도서 목록이 없습니다.");
		}else {
			System.out.println("< 도서 목록>");
			for(int i=0; i<index; i++) {
			System.out.printf("도서 코드 : %s\t도서 제목 : %s\t도서 가격 : %d\r", li[i].getBookCode(),li[i].getBookName(), li[i].getPrice());
			}
			}
		break;
		case 3:
			System.out.println("<< 종료 >>");
			System.exit(0);
		}
		}
	}
	
}
