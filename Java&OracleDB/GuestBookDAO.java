package JDBC_04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuestBookDAO {

	
	public Connection getConnection() throws Exception {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url="jdbc:oracle:thin:@localhost:1521:xe", 
		id="kh", pw="kh";
		return DriverManager.getConnection(url, id, pw);
	}
	
	public int insertData(GuestBookVO vo) throws Exception {
		// 1. insertData의 매개변수로 GuestBookVO DTO를 적용.
		Connection con=getConnection();
		String sql="insert into GuestBook values(seq_seq.nextval, ?,?,?,sysdate)";
		PreparedStatement pstat=con.prepareStatement(sql);
		// 2. db로부터 setter를 통해서 값을 받아온 인자값을 preparedStatement에 셋팅
		// 3. vo 객체를 매개변수로 받았기에. 객체의 생성자부터 입력받은 
		//    데이터들을 getter를 통해 꺼낸다.
		//    입력받은 값을 setting 한다. 즉 입력받는다라고 생각하면된다.
		//    입력받는 값이지만, vo 객체에 getter를 통해서 입력받은 값을 꺼내와서
		//    setter를 통해 preparedStatement의 인자로 셋팅한다.
		pstat.setString(1, vo.getWriter());
		pstat.setString(2, vo.getContents());
		pstat.setString(3, vo.getPw());
		// 2. 쿼리문 전달을 위해서 executeUpdate() 호출 및 int reuturn값을 int 변수에 담는다.
		int result=pstat.executeUpdate();
		con.commit();
		con.close();
		return result;
	}
	
	public List<GuestBookVO> selectAll() throws Exception{
	// 1. db로부터 데이터를 받아오기 위해서 db의 resultset의 column에 맞춰서
	//    생성자를 작성한 vo 객체를 <> generic을 통해서 데이터타입을 부여하고 ArrayList로
	//    데이터를 불러오고 배열에 저장한다.
	// 2. Connection을 하기 위해서 connection 객체변수 선언 및 getConnection() 호출.
	Connection con=getConnection();
	// 3. preparedStatement의 인자로 String sql변수에 select * 쿼리문 작성.
	String sql="select * from guestbook";
	// 4. preparedStatement로 worksheet에 전달할 인자 셋팅
	PreparedStatement pstat=con.prepareStatement(sql);
	// 5. pstat.executeQuery() 메소드로 인자를 전달. reutnr 값은 resultset의 boolean이다.
	ResultSet rs=pstat.executeQuery();
	// 6. list<GusetBookVO> VO 변수 선언.
	List <GuestBookVO> result=new ArrayList<>();// **new로 선언된 ArrayList 객체는 null 값이면 nullpoint 발생한다. 
	// 7. executeQuery() 쿼리문을 날려서 resultset에 출력이 되었다면.
	//   rs.next()를 통해서 해당 값들을 while(rs.next())동안 모조리 받아온다.
	//   executeQuery()일 때는 ResultSet 인터페이스에 종속된 getInt와 getString으로
	//   그 값들을 모조리 담아온다.
	// 8 . 담아온 값들은 list 변수인 result의 생성자의 매개변수의 값들로 다 셋팅한다.
	//     list 변수 add를 통해 0번 배열부터 하나씩 넣는다.
	while(rs.next()) {
	result.add(new GuestBookVO(rs.getInt(1), 
							   rs.getString(2),
							   rs.getString(3),
							   rs.getString(4),
							   rs.getTimestamp(5)));
							//for문을 돌면서 resultset의 column별로 해당 값들을 getter로 출력
	}
	// 9. List에 저장된 List 배열의 변수를 return하고,
	//    main에서도 List 배열의 <>을 통해 변수로 받아오고
	//    for each문을 통해 출력.
	con.close();
	return result;
	}
	
	public int deleteData(int seq) throws Exception {
		
		Connection con=getConnection();
		String sql="delete from guestbook where seq=?";
		PreparedStatement pstat=con.prepareStatement(sql);
		pstat.setInt(1, seq);
		int result=pstat.executeUpdate();
		con.commit();
		con.close();
		return result;
	}
	
	public boolean isCorrectOrNot(int seq, String pw) throws Exception {
		
		Connection con=getConnection();
		String sql="select * from guestbook where seq=? and pw=?";
		// 0. 인자 셋팅
		PreparedStatement pstat=con.prepareStatement(sql);
		pstat.setInt(1, seq);
		pstat.setString(2, pw);
		// 1. 쿼리문을 날리기 위한 ResultSet.
		ResultSet rs=pstat.executeQuery();
		// 2.결과를 확인하기 위한 boolean 타입 변수 선언.
		boolean result=rs.next();
		con.close();
		return result;
	}
	
	
	
	
	public boolean checkID(int seq) throws Exception {
		
		Connection con=getConnection();
		String sql="select * from guestbook where seq=?";
		PreparedStatement pstat=con.prepareStatement(sql);
		pstat.setInt(1, seq);
		
		ResultSet rs=pstat.executeQuery();
		boolean result=rs.next();
		con.close();
		return result;
	}
	
	
	
	
	



}
