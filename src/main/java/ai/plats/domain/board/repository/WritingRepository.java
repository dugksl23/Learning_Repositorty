

package ai.plats.domain.board.repository;


import ai.plats.domain.board.entity.Writing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WritingRepository extends JpaRepository<Writing, String> {
    Writing save(Writing writing);

    Writing findByWritingIdx(int idx);


}
