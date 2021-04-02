package ai.plats.domain.board.entity;
import ai.plats.domain.user.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name="writing")
@Check(constraints = "delWriting in ('Y', 'N')")
//@NamedQuery(name="Writing.testquery",query = "SELECT w FROM Writing w JOIN User u ON w.idxUser = u.idxUser")
public class Writing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idxWriting;



    @Column(nullable = false)
    private String title;
    @Column(length = 5000, nullable = false)
    private String content;
    @CreationTimestamp
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate;
    @UpdateTimestamp
    private LocalDateTime modiDate;
    private String delWriting;


    @ManyToOne
    @JoinColumn(name="writer_idx")
    private User user;




    public Writing() {
    }
    public Writing(int idxWriting,  String title, String content, LocalDateTime regDate, LocalDateTime modiDate, String delWriting,User user) {
        this.idxWriting = idxWriting;
        this.title = title;
        this.content = content;
        this.regDate = regDate;
        this.modiDate = modiDate;
        this.delWriting = delWriting;
        this.user = user;
    }
    public int getIdxWriting() {
        return idxWriting;
    }



    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public LocalDateTime getRegDate() {
        return regDate;
    }
    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }
    public LocalDateTime getModiDate() {
        return modiDate;
    }
    public void setModiDate(LocalDateTime modiDate) {
        this.modiDate = modiDate;
    }
    public String getDelWriting() {
        return delWriting;
    }
    public void setDelWriting(String delWriting) {
        this.delWriting = delWriting;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}