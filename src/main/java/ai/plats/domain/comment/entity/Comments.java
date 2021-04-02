package ai.plats.domain.comment.entity;


import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Check(constraints = "commDel in('Y','N')")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idxComm;

    @Column
    private int idxWriting;
    @Column(length = 500)
    private String commContent;
    @Column(length = 50)
    private String commWriter;

    @CreationTimestamp
    private LocalDateTime cotemmRegDate;
    @UpdateTimestamp
    private LocalDateTime commModDate;

    @Column
    private String commDel;

    public Comments() {
    }

    public Comments(int idxWriting, String commContent, String commWriter, String commDel) {
        this.idxWriting = idxWriting;
        this.commContent = commContent;
        this.commWriter = commWriter;
        this.commDel = commDel;
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

    public String getCommContent() {
        return commContent;
    }

    public void setCommContent(String commContent) {
        this.commContent = commContent;
    }

    public String getCommWriter() {
        return commWriter;
    }

    public void setCommWriter(String commWriter) {
        this.commWriter = commWriter;
    }

    public LocalDateTime getCotemmRegDate() {
        return cotemmRegDate;
    }

    public void setCotemmRegDate(LocalDateTime cotemmRegDate) {
        this.cotemmRegDate = cotemmRegDate;
    }

    public LocalDateTime getCommModDate() {
        return commModDate;
    }

    public void setCommModDate(LocalDateTime commModDate) {
        this.commModDate = commModDate;
    }

    public String getCommDel() {
        return commDel;
    }

    public void setCommDel(String commDel) {
        this.commDel = commDel;
    }
}
