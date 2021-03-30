//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ai.plats.domain.board.service;

import ai.plats.domain.board.entity.Writing;
import ai.plats.domain.board.repository.WritingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WritingService {
    @Autowired
    private WritingRepository writingRepository;

    public WritingService() {
    }

    public Writing writing(Writing writing) {
        Writing result = this.writingRepository.save(writing);
        return result;
    }

    public List<Writing> boardList(int cPage, int size) {
        System.out.println(cPage+size);
        PageRequest pageRequest = PageRequest.of(cPage, size, Direction.DESC, "regDate");
        System.out.println(pageRequest.getPageNumber());

        List<Writing> boardList = this.writingRepository.findAll(pageRequest).getContent();
        System.out.println("return 된 게시글의 갯수"+boardList.size());
        return boardList;
    }

    public Writing getMyWriting(int cPage) {
        Writing result = this.writingRepository.findByWritingIdx(cPage);
        return result;
    }

    public Writing updateWriting(Writing writing) {
        Writing result = this.writingRepository.save(writing);
        return result;
    }


}
