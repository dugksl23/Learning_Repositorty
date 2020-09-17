package FTP;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPDataTransferListener;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPFile;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import it.sauronsoftware.ftp4j.FTPListParseException;

public class FTP_Program {

	


	
	

 public static int validInputNum(String str) {
  
	 
	 
	 
	 
	 
	 
	 
	 
	 Scanner sc=new Scanner(System.in);
  int num;

  for(;;) {
   try {
    System.out.print(str); 
    num=Integer.parseInt(sc.nextLine());
    return num;
   }catch(Exception e) {
    System.out.println("잘 못 입력하셨습니다.");
    continue;
   }
  }

 }




 
 

 public static void main(String[] args) {
  Scanner sc=new Scanner(System.in);
  int input;
  FTPClient client = new FTPClient();

  menu:for(;;) {
   System.out.print("=== FTP Client Program ===\r"
     + "1.Connext FTP Server\r"
     + "2.Exsit program\r");

   input=validInputNum(">> ");
   if(!(input>0&&input<3)) {
    System.out.println("입력 범위를 초과하였습니다.");
    continue menu;
   }


   switch(input) {// === FTP 연결 시작

   case 1:
    
    FTPFile[] list = null;

    String uRl;
    int port = 0;
    String log, pw;
    String downFile, upFile = null, delFile;
    String fileName;


   url:for(;;) {
    System.out.print("Input URL\r>> ");
    uRl=sc.nextLine();
    port=validInputNum("Input Port\r>> ");
    System.out.println("try to connect : "+uRl+"(port:"+port+")");

    try {
     client.connect(uRl,port);
    }catch(Exception e){
    System.out.println("잘못 입력하셨습니다.");
    continue url;
    }
    break;
    }
    System.out.println("FTP Server is connected");
    
    //==== login part
    System.out.print("ID : ");
    log=sc.nextLine();
    System.out.print("PW : ");
    pw=sc.nextLine();


    try {
     client.login(log, pw);
    } catch (Exception e) {
     System.out.println("ID 또는 PW가 틀렸습니다.");
     continue menu;
    }

    System.out.println("Login Success");
    // === index ===

    list:for(;;){// === file index ===
     System.out.println("1.Upload File\r"
       + "2.DownLoad File\r"
       + "3.Delete File\r"
       + "4.Dissconnect FTP Server\r");
     input=validInputNum(">> ");
     switch(input) {
     //======================= 시작 ==============================
     case 1:
    upload:for(;;) {
     
      try {// === upload ====
       System.out.print("Input File >> ");
       upFile=sc.nextLine();
       client.upload(new java.io.File(upFile));
       
      } catch (Exception e1) {
       System.out.println("파일이 존재하지 않습니다.");
       continue upload;
      }
      System.out.println("업로드 되었습니다.");
      continue list;
      }
     
     
      
     case 2:// ==== 다운로드 =======================================
      
     down:for(;;) {

       try {
        list = client.list();
        for(int i=0; i<list.length; i++) {
         System.out.println(list[i]);
        }
       } catch (Exception e2) {
       }
       
      
       try {
        System.out.print("choose File >> ");
        downFile=sc.nextLine();
        System.out.print("title >>");
        fileName=sc.nextLine();
        client.download(downFile, new java.io.File(fileName));
        
       } catch (Exception e1) {
        System.out.println("파일이 존재하지 않습니다.");
        continue list;
       } 
       System.out.println("파일이 다운로드 되었습니다.");
       continue list;
      }

     case 3:// ==== download =====================================
     del:for(;;) {

       try {
        try {
         list = client.list();
         for(int i=0; i<list.length; i++) {
          System.out.println(list[i]);
         }
        } catch (Exception e2) {
        }
        
        System.out.print("choose File >> ");
        delFile=sc.nextLine();
        client.deleteFile(delFile);
        
       } catch (Exception e) {
        System.out.println("파일이 존재하지 않습니다.");
        continue list;
       }
       
       System.out.println("파일이 삭제되었습니다.");
       continue list;
       
       

      } 
     case 4://================= disconnect ==========================
      try {// ==== disConnect ====
       client.disconnect(true);
       continue menu;
      } catch (Exception e) {

      }

     }//swith 구문 종료
     break; 
    }//for문 list
   //======================== 프로그램 종료===================================
   case 2:
    System.out.println("=== 프로그램 종료 ===");
    System.exit(0);
   }
  }
 }

}
