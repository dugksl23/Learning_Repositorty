

package ai.plats.domain.board.repository;


import ai.plats.domain.board.entity.Writing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WritingRepository extends JpaRepository<Writing, Integer> {

    Writing save(Writing writing);

    Writing findByIdxWriting(int idx);

    Page<Writing> findByDelWriting(String delWriting, Pageable pageble);





}
