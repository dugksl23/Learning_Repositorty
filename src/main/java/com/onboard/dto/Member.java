package com.onboard.dto;

import javax.persistence.*;
import java.sql.Date;

@Entity //Entity는 member 클래스가 db에 있는 member테이블에 상응하는 클래스임을 표시하는 주석이다.
@Table(name = "Member")
public class Member {

    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;
    @Id//primary key 기능의 annotaion

    private String email;
    private String password;
    private String nickname;
    private Date reg_date;
    private Date modi_date;
    private String widthrawal;


    public Member() {
    }

    public Member(int no, String email, String password, String nickname, Date reg_date, Date modi_date, String widthrawal) {
        this.no = no;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.reg_date = reg_date;
        this.modi_date = modi_date;
        this.widthrawal = widthrawal;
    }


    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public Date getModi_date() {
        return modi_date;
    }

    public void setModi_date(Date modi_date) {
        this.modi_date = modi_date;
    }

    public String getWidthrawal() {
        return widthrawal;
    }

    public void setWidthrawal(String widthrawal) {
        this.widthrawal = widthrawal;
    }
}
