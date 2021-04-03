package ai.plats.domain.comments.repository;


import ai.plats.domain.comments.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, String> {

   public List<Comments> findByIdxWriting(int idx);


   public  List<Comments> findByIdxWritingAndDel(int idx,String del);

   public Comments findByIdxComments(int idxComments);
}
