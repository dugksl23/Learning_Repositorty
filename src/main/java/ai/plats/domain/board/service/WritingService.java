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

        System.out.println("pageTotalCount"+pageTotalCount);
        System.out.println("cPage>>>"+currentPage);

        System.out.println("startNavi>>>"+startNavi);
        System.out.println("endNavi>>>"+endNavi);


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

        System.out.println(end);

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