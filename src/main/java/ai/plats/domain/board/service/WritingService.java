package ai.plats.domain.board.service;

import ai.plats.domain.board.entity.Writing;
import ai.plats.domain.board.repository.WritingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WritingService {
    @Autowired
    private WritingRepository writingRepository;

    public WritingService() {
    }

    public Writing writing(Writing writing) {
        writing.setDelWriting("N");
        Writing result = this.writingRepository.save(writing);
        return result;
    }

    public Page<Writing> boardList(Integer cPage, int size) {

        Pageable pageable = PageRequest.of(cPage, size, Direction.DESC, "regDate");
        Page<Writing> result = writingRepository.findByDelWriting("N", pageable);
        System.out.println("total elements ===>" + result.getTotalElements());
        System.out.println("total page ===>" + result.getTotalPages());
        System.out.println("total page===>" + result.getPageable());

        return result;
    }

    public Map<String, Object> getPagination(int cPage, int totalRecord) {

        int currentPage=cPage+1;
        int pageTotalCount = totalRecord/5;

        if (currentPage < 1) {
            currentPage = 1;
            System.out.println("들어오나요? 1보다 작거나 같아");
        } else if (currentPage>pageTotalCount) {
            currentPage = pageTotalCount;
        }

        int startNavi = (((currentPage-1)/5)*5)+1;
        int endNavi = (startNavi+5)-1;

        if (endNavi>pageTotalCount) {
            endNavi=pageTotalCount;
        }

        boolean prev=true;
        boolean next=true;

        if (startNavi==currentPage) { prev = false; }
        if (endNavi==pageTotalCount) { next = false; }
        if (endNavi<currentPage) { next = false; }


        System.out.println(pageTotalCount);
        System.out.println("cPage>>>"+currentPage);

        System.out.println("startNavi>>>"+startNavi);
        System.out.println("endNavi>>>"+endNavi);

        System.out.println("prev>>>"+prev);
        System.out.println("next>>>"+next);

        System.out.println(pageTotalCount);
        Map<String, Object> navi = new HashMap<>();
        navi.put("prev", prev);
        navi.put("next", next);

        navi.put("prePage", currentPage-1);
        navi.put("nextPage", currentPage+1);


        navi.put("startNavi", startNavi);
        navi.put("endNavi", endNavi);


        return navi;

    }


    public Writing getMyWriting(int cPage) {
        Writing result = this.writingRepository.findByIdxWriting(cPage);
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