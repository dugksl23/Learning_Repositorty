package ai.plats.domain.board.web;


import ai.plats.domain.account.service.UserLoginService;
import ai.plats.domain.board.entity.Comments;
import ai.plats.domain.board.entity.Writing;
import ai.plats.domain.board.repository.CommentsRepository;
import ai.plats.domain.board.service.CommentsService;
import ai.plats.domain.board.service.WritingService;
import ai.plats.domain.user.entity.User;
import ai.plats.domain.user.service.UserJoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Controller
public class WritingController {
    @Autowired
    WritingService writingService;

    @Autowired
    UserLoginService accountService;//로그인 서비스

    @Autowired
    UserJoinService userJoinService;//user 서비스


    @Autowired
    CommentsService commentsService;


    @RequestMapping({"/goWriting"})
    public String writing(Model model, Principal principal) {
        System.out.println(principal.getName());
        Optional<User> user = userJoinService.findUserByIdxUser(Integer.parseInt(principal.getName()));
        model.addAttribute("nick", ((User)user.get()).getUserNick());
        model.addAttribute("idxUser", ((User)user.get()).getIdxUser());
        return "board/writing";
    }

    @RequestMapping(value = {"/procWriting"},method = RequestMethod.POST)
    @ResponseBody
    public String procWriting(Writing writing,HttpServletRequest req) {
        System.out.println("글쓰기 입력받음 ");

        String res;
        if(writingService.writing(writing,Integer.parseInt(req.getParameter("idxUser")))!=null){
            res = "success";
        }
        else{
            res="fail";
        }
        return res;
    }

    @RequestMapping({"/goViewWriting"})
    public String procWriting(Writing writing, Model model, Principal principal) {
        Writing viewWriting = writingService.getMyWriting(writing.getIdxWriting());
        model.addAttribute("viewWriting", viewWriting);
//        Optional<User> user = userJoinService.findUserByIdxUser(Integer.parseInt(principal.getName()));

        List<Comments> list=commentsService.findByIdxWriting(viewWriting.getIdxWriting());

        model.addAttribute("commetsList",list);
        model.addAttribute("idxUser", principal.getName());
        return "/board/viewWriting";
    }




    @RequestMapping(value="/goUpdateWriting")
    public String goUpdateWriting(Writing writing, Model m) {
        System.out.println("수정 글 번호 =======>?" + writing.getIdxWriting());
//        System.out.println("수정 글의 작성자 idx =======>?" + writing.getIdxUser());

        Writing updateWriting = writingService.getMyWriting(writing.getIdxWriting());
        m.addAttribute("updateWriting", updateWriting);
//        session.setAttribute("regDate", updateWriting.getRegDate());

        return "board/updateWriting";
    }





    @RequestMapping(value="/procUpdateWriting", method= RequestMethod.POST)
    @ResponseBody
    public String procUpdateWriting(Writing writing,String regDateStr) {
        System.out.println("없데이뚜 ");

        LocalDateTime parsedLocalDateTime = LocalDateTime.parse(regDateStr);
        writing.setRegDate(parsedLocalDateTime);
        writingService.updateWriting(writing);

        return "게시물 수정 완료 ! ";
    }



    @RequestMapping({"/goDelWriting"})
    public String delWriting(Writing writing) {
        System.out.println("삭제 글 번호 =======>?" + writing.getIdxWriting());
        Writing result = writingService.delWriting(writing);

        return "redirect:/";
    }



}
