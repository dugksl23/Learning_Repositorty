//package ai.plats.domain.board.dto;
//
//
//import ai.plats.domain.board.entity.Writing;
//import com.fasterxml.jackson.annotation.JsonFormat;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//import org.springframework.format.annotation.DateTimeFormat;
//
//import javax.persistence.Column;
//import java.time.LocalDateTime;
//
//public class WritingDto  extends Writing {
//
//
//    private String writer;
//
//    public WritingDto(int idxWriting, String title, String content, LocalDateTime regDate, LocalDateTime modiDate, String delWriting, String writer) {
//        super(idxWriting, title, content, regDate, modiDate, delWriting);
//        this.writer = writer;
//    }
//
//    public String getWriter() {
//        return writer;
//    }
//
//    public void setWriter(String writer) {
//        this.writer = writer;
//    }
//}
