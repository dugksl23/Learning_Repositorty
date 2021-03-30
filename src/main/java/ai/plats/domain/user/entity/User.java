package ai.plats.domain.user.entity;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;




@Entity
@Check(constraints = "withDraw in('Y','N')")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idxClient;
    @Column(length = 50)
    private String clientEmail;
    @Column(length = 255)
    private String clientPwd;
    @Column(length = 30)
    private String nick;

    @Column(length = 10)
    private String withDraw;

    //
    @CreationTimestamp
    private LocalDateTime regDate;
    private LocalDateTime modDate;


    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    public User() {

    }


    public User(String clientEmail, String clientPwd, String nick, String withDraw, LocalDateTime regDate, LocalDateTime modDate) {
        this.clientEmail = clientEmail;
        this.clientPwd = clientPwd;
        this.nick = nick;
        this.withDraw = withDraw;
        this.regDate = regDate;
        this.modDate = modDate;
    }

    public int getIdx_client() {
        return idxClient;
    }

    public void setIdx_client(int idx_client) {
        this.idxClient = idx_client;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientPwd() {
        return clientPwd;
    }

    public void setClientPwd(String clientPwd) {
        this.clientPwd = clientPwd;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }


    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public LocalDateTime getModDate() {
        return modDate;
    }

    public void setModDate(LocalDateTime modDate) {
        this.modDate = modDate;
    }
}
