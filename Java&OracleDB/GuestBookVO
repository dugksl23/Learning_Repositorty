package JDBC_04;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class GuestBookVO {
	
	
	private int seq;
	private String writer;
	private String contents;
	private String pw;
	private Timestamp write_date;
	
	

	public GuestBookVO(int seq, String writer, String contents, String pw2, Timestamp write_date) {
		super();
		this.seq = seq;
		this.writer = writer;
		this.contents = contents;
		this.pw = pw2;
		this.write_date = write_date;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public Timestamp getWrite_date() {
		return write_date;
	}
	
	
	// 1. date type을 String으로 return 한다.
	public String getFormatDate() {
		// 2. SimpleDateFormat 클래스를 new 하여
		//    메소드콜로 date의 형식을 잡아준다.
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy년MM월dd일 hh시mm분ss초");
												//MM월은 분과 차이를 두기 위해서 대문자
		return sdf.format(write_date.getTime());
		// 3. SimpleDateFormat 변수에 종속된.format();을 통해서 
		//    db에서 받아온 timestamp를 String으로 변한하고, 종속 메소드인 getTime()으로 시간을 가져옴.
	}
	
	public void setWrite_date(Timestamp write_date) {
		this.write_date = write_date;
	}


}
