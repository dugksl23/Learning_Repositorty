package ai.plats.domain.board.service;
import ai.plats.domain.board.entity.Writing;
import ai.plats.domain.board.repository.WritingRepository;
import ai.plats.domain.comments.entity.Comments;
import ai.plats.domain.comments.repository.CommentsRepository;
import ai.plats.domain.user.entity.User;
import ai.plats.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WritingService {
    @Autowired
    private WritingRepository writingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentsRepository commentsRepository;


    public Writing writing(Writing writing, Integer idxUser) {
        writing.setDelWriting("N");
        Optional<User> userVO = userRepository.findByIdxUser(idxUser);
        writing.setUser(userVO.get());
        Writing result = writingRepository.save(writing);
        return result;
    }

    public Page<Writing> boardList(Integer cPage, int size) {


        Pageable pageable = PageRequest.of(cPage, size, Direction.DESC, "regDate");
        Page<Writing> result = writingRepository.findByDelWriting("N", pageable);



//        List<Writing> boardList = result.getContent();
        return result;
    }

    public Map<String, Object> getPagination(Integer cPage, int totalRecord) {

        Integer currentPage=cPage;
        if (cPage==null||cPage==0) {
            currentPage=cPage = 1;
        }else{
            currentPage=cPage+1;
        }

        int pageTotalCount = (totalRecord/10);

        if (totalRecord%10>0) {
            pageTotalCount++;
        }

        if (currentPage < 1) {
            currentPage = 1;

        } else if (currentPage>pageTotalCount) {
            currentPage = pageTotalCount;
        }

        int startNavi = (((currentPage-1)/5)*5)+1;
        int endNavi = (startNavi+5)-1;

        if (endNavi>pageTotalCount) {
            endNavi=pageTotalCount;
        }

        boolean prev5=true;
        boolean next5=true;
        boolean end=true;


        if (startNavi==1) { prev5 = false; }
        if (endNavi==pageTotalCount) { next5 = false; }


        Map<String, Object> navi = new HashMap<>();
        navi.put("startNavi", startNavi);
        navi.put("endNavi", endNavi);
        navi.put("prev5", prev5);
        navi.put("next5", next5);

        if (endNavi!=1&&startNavi!=1) {
            System.out.println("endPage는 1이 아님");
            end = false;
            navi.put("end", end);
            navi.put("firstPage", 1);
            navi.put("endPage", pageTotalCount);
        }

        return navi;

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
        Writing result = writingRepository.findByIdxWriting(writing.getIdxWriting());
        result.setDelWriting("Y");
        result = writingRepository.save(result);


        //게시물 삭제시 , 해당 댓글 모두 삭제처리
        List<Comments> comments_list=commentsRepository.findByIdxWriting(writing.getIdxWriting());
        for(Comments i : comments_list){
            i.setDel("Y");
            commentsRepository.save(i);
        }

        return result;
    }
}