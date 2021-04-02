package ai.plats.domain.board.service;
import ai.plats.domain.board.entity.Writing;
import ai.plats.domain.board.repository.WritingRepository;
import ai.plats.domain.user.entity.User;
import ai.plats.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WritingService {
    @Autowired
    private WritingRepository writingRepository;

    @Autowired
    private UserRepository userRepository;


    public Writing writing(Writing writing,int userIdx) {
        writing.setDelWriting("N");
        Optional<User> userVO = userRepository.findByIdxUser(userIdx);
        writing.setUser(userVO.get());
        Writing result = writingRepository.save(writing);
        return result;

    }


    public List<Writing> boardList(int cPage, int size) {
        System.out.println(cPage + size);
        Pageable pageable = PageRequest.of(cPage, size, Direction.DESC, "regDate");

        Page<Writing> result = writingRepository.findByDelWriting("N", pageable);
        List<Writing> boardList = result.getContent();



        return boardList;
    }


    public Writing getMyWriting(int idx) {
        Writing result = this.writingRepository.findByIdxWriting(idx);
        return result;
    }


    public Writing updateWriting(Writing writing) {
        Writing result = this.writingRepository.save(writing);
        return result;
    }


    public Writing delWriting(Writing writing) {
        System.out.println("삭제 글 번호 =======>?" + writing.getIdxWriting());
        Writing result = writingRepository.findByIdxWriting(writing.getIdxWriting());
        System.out.println("삭제될 게시글의 번호 ====>" + result.getIdxWriting());
        result.setDelWriting("Y");
        result = writingRepository.save(result);
        return result;
    }
}