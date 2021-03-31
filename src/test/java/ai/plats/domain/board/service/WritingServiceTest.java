package ai.plats.domain.board.service;

import ai.plats.domain.board.entity.Writing;
import ai.plats.domain.board.repository.WritingRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class WritingServiceTest {

    @Autowired
    WritingService writingService;

    @Autowired
    WritingRepository writingRepository;

    @Test
    @DisplayName("보드 페이지를 갯수만큼 가지고 온다")
    void board_list_test() {
        // given
        writingRepository.save(new Writing("1", "2"));
        writingRepository.save(new Writing("1", "2"));
        writingRepository.save(new Writing("1", "2"));

        // when
        List<Writing> writings = writingService.boardList(0, 10);

        // then
        assertThat(writings.size()).isEqualTo(3);


    }

}