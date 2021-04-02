package ai.plats.domain.board.repository;


import ai.plats.domain.board.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, String> {


   public  List<Comments> findByIdxWriting(int idx);
}
