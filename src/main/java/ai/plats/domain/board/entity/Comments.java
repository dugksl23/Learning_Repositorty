package ai.plats.domain.board.entity;


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
    private int idxComm;

    @Column
    private int idxWriting;
    @Column(length = 500)
    private String content;

    @Column
    private int idxUser;

    @CreationTimestamp
    private LocalDateTime RegDate;
    @UpdateTimestamp
    private LocalDateTime ModDate;

    @Column
    private String del;

    public Comments() {
    }

    public Comments(int idxWriting, String content, int  idxUser, String del) {
        this.idxWriting = idxWriting;
        this.content = content;
        this.idxUser = idxUser;
        this.del = del;
    }

    public int getIdxComm() {
        return idxComm;
    }

    public void setIdxComm(int idxComm) {
        this.idxComm = idxComm;
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

    public int getIdxUser() {
        return idxUser;
    }

    public void setIdxUser(int idxUser) {
        this.idxUser = idxUser;
    }

    public LocalDateTime getRegDate() {
        return RegDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        RegDate = regDate;
    }

    public LocalDateTime getModDate() {
        return ModDate;
    }

    public void setModDate(LocalDateTime modDate) {
        ModDate = modDate;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }
}
