package ai.plats.domain.comments.entity;


import ai.plats.domain.user.entity.User;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Check(constraints = "del in('Y','N')")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idxComments;

    @Column
    private int idxWriting;
    @Column(length = 500)
    private String content;

    @ManyToOne
    @JoinColumn(name="writer_idx")
    private User user;



    @CreationTimestamp
    private LocalDateTime regDate;
    @UpdateTimestamp
    private LocalDateTime modDate;

    @Column
    private String del;

    public Comments() {
    }

    public int getIdxComments() {
        return idxComments;
    }

    public void setIdxComments(int idxComments) {
        this.idxComments = idxComments;
    }

    public int getIdxWriting() {
        return idxWriting;
    }

    public void setIdxWriting(int idxWriting) {
        this.idxWriting = idxWriting;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }
}
