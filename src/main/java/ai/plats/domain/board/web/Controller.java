package ai.plats.domain.board.web;


import ai.plats.domain.account.service.UserLoginService;
import ai.plats.domain.board.entity.Writing;
import ai.plats.domain.board.service.WritingService;
import ai.plats.domain.user.entity.User;
import ai.plats.domain.user.service.UserJoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Optional;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    WritingService writingService;

    @Autowired
    UserLoginService accountService;//로그인 서비스

    @Autowired
    UserJoinService userJoinService;//user 서비스



    @RequestMapping({"/goWriting"})
    public String writing(Model m, Principal p) {
        System.out.println(p.getName());
        Optional<User> c = userJoinService.findClientByEmail(p.getName());
        m.addAttribute("nick", ((User)c.get()).getNick());
        return "board/writing";
    }

    @RequestMapping({"/procWriting"})
    @ResponseBody
    public String procWriting(Writing writing) {
        System.out.println("글쓰기 입력받음 ===>");
        System.out.println("작성자 ===>" + writing.getWriter());
        System.out.println("내용 ===>" + writing.getTitle());
        System.out.println("내용 ===>" + writing.getContent());
        this.writingService.writing(writing);
        String success = "success";
        return success;
    }

    @RequestMapping({"/goViewWriting"})
    public String procWriting(Writing writing, Model m, Principal p) {
        System.out.println("viewWriting =======>?" + writing.getWritingIdx());
        Writing viewWriting = this.writingService.getMyWriting(writing.getWritingIdx());
        System.out.println("db 조회 후의 데이터 결과 ============>" + viewWriting.getWritingIdx());
        System.out.println("현재 글의 작성자 ====>"+viewWriting.getWriter());
        m.addAttribute("viewWriting", viewWriting);

        Optional<User> email = userJoinService.findClientByEmail(p.getName());
        System.out.println("현재 로그인한 유저 ====>"+email.get().getClientEmail());
        m.addAttribute("nick", email.get().getNick());

        return "/board/viewWriting";

    }

    @RequestMapping({"/goUpdateWriting"})
    public String goUpdateWriting(Writing writing, Model m) {
        System.out.println("수정 글 번호 =======>?" + writing.getWritingIdx());
        Writing updateWriting = writingService.getMyWriting(writing.getWritingIdx());
        m.addAttribute("updateWriting", updateWriting);

        return "board/updateWriting";
    }

    @RequestMapping({"/procUpdateWriting"})
    public String procUpdateWriting(Writing writing, Model m) {
        System.out.println("수정 글 번호 =======>?" + writing.getWritingIdx());
        System.out.println("수정 이전 글 내용 =======>?" + writing.getContent());
        Writing viewWriting = writingService.updateWriting(writing);
        System.out.println("수정 후의 글 내용===>"+viewWriting.getContent());

        return "redirect:goViewWriting?writingIdx="+writing.getWritingIdx();
    }




}
