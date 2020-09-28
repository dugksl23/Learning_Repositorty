package JDBC_04;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;
import java.util.Scanner;

public class GuestBookMain {
	
	public static int inputValidNum(String str) {
		Scanner sc=new Scanner(System.in);
		int num;
		while(true) {
			try {
			System.out.print(str);
			num=Integer.parseInt(sc.nextLine());
			return num;
			}catch(Exception e) {
				System.out.println("입력 범위를 벗어났습니다.");
			}
		}
	}
	
	  public static String getSHA512(String input){

	      String toReturn = null;
	      try {
	          MessageDigest digest = MessageDigest.getInstance("SHA-512");
	          digest.reset();
	          digest.update(input.getBytes("utf8"));
	          toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      
	      return toReturn;
	       }

	
	public static void main(String[] args) {
		
		GuestBookDAO dao=new GuestBookDAO();
		//DAO= Data Access Object for input and output with data.
		
		Scanner sc=new Scanner(System.in);
		menu:while(true) {
		System.out.println("=== 방명록 프로그램 ===\r"
				+ "1. 방명록 작성\r"
				+ "2. 방명록 목록 보기\r"
				+ "3. 방명록 삭제하기\r"
				+ "4. 종료하기\r");
		int menu=inputValidNum(">> ");
		
		if(!(menu>0&&menu<5)) {
			System.out.println("입력 범위를 벗어났습니다.");
			continue menu;}
		
		switch(menu) {
		
		case 1:
			
		System.out.print("작성자 : ");
		String writer=sc.nextLine();
		System.out.print("내용 : ");
		String contents=sc.nextLine();
		System.out.print("비밀번호 : ");
		String pw=sc.nextLine();
		
		// 1.  DTO : Data Transfer Object = VO 
		//     Value Object는 값을 보관하는 역할을 하지만, 반대로 data를 전달하는 instance로도 쓰인다.
		GuestBookVO vo=new GuestBookVO(0, contents, writer, pw, null);
		// 2. seq는 sequence에 따라서 값이 1부터 자동으로 증가하기 때문에 0을 넣어도 상관없다.
		//    또한 timestamp 데이터 타입은, 자바에서 db를 연동할 경우,
		//    해당 컬럼이 not null이 아니라면 null 입력 가능하며, 이미 default로 sysdate이기에
		//    null 값을 부여하여도 입력된 시간으로 자동 기입이 된다.
		// 3. 입력 순서는 db의 column순이기에, vo 생성자의 매개변수 또한 column순으로 작성하자!!! 
		// 4. insertData() 호출하여 vo를 DAO에 전달.
		// 5. dao.insertData() data타입은 dto로서 GuestBookVO 이기에 해당 객체를 바로 인자로 전달.
			try {
				dao.insertData(vo);
				System.out.println("입력되었습니다.");
				continue menu;
			} catch (Exception e) {
				System.out.println("Error가 발생하였습니다.\r해당 Error는 관리자에게 문의바랍니다.");
				e.printStackTrace();
			}
			continue menu;
		
		case 2:
			try {
				// 0. List<GuestBookVO> list 변수 선언 및 dao에서 selectAll() 호출하여, 
				//    list 변수에 reutrn.
				List<GuestBookVO> list=dao.selectAll();
				// 1. forEach문을 통해 모두 출력.
				// 2. timestamp는 따로 getter 메소드를 만들어서 모양을 만들어내자.
				for(GuestBookVO e:list) {
					System.out.printf("글 번호 : %d\t작성자 : %s\r내용 : %s\t입력 시간 : %s\r",
							e.getSeq(), e.getWriter(), e.getContents(), e.getFormatDate());
				}
				continue menu;
			} catch (Exception e) {
				System.out.println("Error가 발생하였습니다.\r해당 Error는 관리자에게 문의바랍니다.");
				e.printStackTrace();
			}
			continue menu;
		
		case 3:
			// 1. 글번호 체크 검사.
			try {
				int checkID=inputValidNum("글 번호 : ");
				if(!(dao.checkID(checkID))){
					System.out.print("등록된 글이 없습니다.");
					continue menu;}
			// 2. 글 번호와 비밀번호 검사.
				System.out.print("비밀 번호 : ");
				String password=getSHA512(sc.nextLine());
				if(!(dao.isCorrectOrNot(checkID, password))){
					System.out.print("비밀번호를 다시 한 번 확인해주세요.");
					continue menu;
				}else {
			// 3. 글 삭제	
					dao.deleteData(checkID);
					System.out.print("삭제되었습니다.");
					continue menu;
				}
			} catch (Exception e) {
				System.out.println("Error가 발생하였습니다.\r해당 Error는 관리자에게 문의바랍니다.");
				e.printStackTrace();
			}
		continue menu;
		
		case 4:
		System.out.println("=== 프로그램 종료 ===");	
		System.exit(0);	
			
			
		}
		}
	}
}
