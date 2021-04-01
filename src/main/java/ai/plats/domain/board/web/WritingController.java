package ai.plats.domain.board.web;


import ai.plats.domain.account.service.UserLoginService;
import ai.plats.domain.board.entity.Writing;
import ai.plats.domain.board.service.WritingService;
import ai.plats.domain.user.entity.User;
import ai.plats.domain.user.service.UserJoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class WritingController {
    @Autowired
    WritingService writingService;

    @Autowired
    UserLoginService accountService;//로그인 서비스

    @Autowired
    UserJoinService userJoinService;//user 서비스

    @Autowired
    HttpSession session;



    @RequestMapping({"/goWriting"})
    public String writing(Model model, Principal principal) {
        System.out.println(principal.getName());
        Optional<User> user = userJoinService.findUserByIdxUser(Integer.parseInt(principal.getName()));

        model.addAttribute("nick", ((User)user.get()).getUserNick());
        model.addAttribute("idxUser", ((User)user.get()).getIdxUser());

        return "board/writing";
    }

    @RequestMapping(value="/procWriting", method = RequestMethod.POST)
    @ResponseBody
    public String procWriting(Writing writing, String regDate) {

        this.writingService.writing(writing);
        String success = "success";
        return success;
    }

    @RequestMapping({"/goViewWriting"})
    public String procWriting(Writing writing, Model model, Principal principal) {

        Writing viewWriting = writingService.getMyWriting(writing.getIdxWriting());
        model.addAttribute("viewWriting", viewWriting);

        Optional<User> user = userJoinService.findUserByIdxUser(Integer.parseInt(principal.getName()));
        model.addAttribute("idxUser", user.get().getIdxUser());

        return "/board/viewWriting";

    }

    @RequestMapping(value="/goUpdateWriting")
    public String goUpdateWriting(Writing writing, Model m) {
        System.out.println("수정 글 번호 =======>?" + writing.getIdxWriting());
        Writing updateWriting = writingService.getMyWriting(writing.getIdxWriting());
        m.addAttribute("updateWriting", updateWriting);
        session.setAttribute("regDate", updateWriting.getRegDate());

        return "board/updateWriting";
    }

    @RequestMapping(value="/procUpdateWriting", method= RequestMethod.POST)
    @ResponseBody
    public String procUpdateWriting(Writing writing) {
        System.out.println(writing.getIdxWriting());
        writing.setRegDate((LocalDateTime) session.getAttribute("regDate"));
        Writing viewWriting = writingService.updateWriting(writing);

        String redirect_url="/goViewWriting?idxWriting="+viewWriting.getIdxWriting();
        return redirect_url;
    }


    @RequestMapping({"/goDelWriting"})
    public String delWriting(Writing writing) {

        Writing result = writingService.delWriting(writing);

        return "redirect:/";
    }



}
