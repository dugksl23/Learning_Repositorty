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
    private int idxUser;
    @Column(length = 50)
    private String userEmail;
    @Column(length = 255)
    private String userPwd;
    @Column(length = 30)
    private String userNick;

    @Column(length = 10)
    private String withDraw;

    //
    @CreationTimestamp
    private LocalDateTime regDate;
    private LocalDateTime modDate;


    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    public User() {

    }

    public User(String userEmail, String userPwd, String userNick, String withDraw, LocalDateTime regDate, LocalDateTime modDate) {
        this.userEmail = userEmail;
        this.userPwd = userPwd;
        this.userNick = userNick;
        this.withDraw = withDraw;
        this.regDate = regDate;
        this.modDate = modDate;
    }

    public int getIdxUser() {
        return idxUser;
    }

    public void setIdxUser(int idxUser) {
        this.idxUser = idxUser;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getWithDraw() {
        return withDraw;
    }

    public void setWithDraw(String withDraw) {
        this.withDraw = withDraw;
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
