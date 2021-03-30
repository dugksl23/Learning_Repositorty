package ai.plats.domain.board.entity;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Check(constraints = "delWriting in ('Y', 'N')")
public class Writing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int writingIdx;
    private String writer;
    @Column(nullable = false)
    private String title;
    @Column(length = 5000, nullable = false)
    private String content;
    @CreationTimestamp
    private LocalDateTime regDate;
    @CreationTimestamp
    private LocalDateTime modiDate;
    private String delWriting;


    public Writing() {

    }

    public Writing(int writingIdx, String writer, String title, String content, LocalDateTime regDate, LocalDateTime modiDate, String delWriting) {
        this.writingIdx = writingIdx;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.regDate = regDate;
        this.modiDate = modiDate;
        this.delWriting = delWriting;
    }

    public int getWritingIdx() {
        return writingIdx;
    }

    public void setWritingIdx(int writingIdx) {
        this.writingIdx = writingIdx;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
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
}


