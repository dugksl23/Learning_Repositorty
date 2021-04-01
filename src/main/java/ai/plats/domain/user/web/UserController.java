package ai.plats.domain.user.web;


import ai.plats.domain.board.entity.Writing;
import ai.plats.domain.board.service.WritingService;
import ai.plats.domain.user.entity.User;
import ai.plats.domain.user.service.UserJoinService;
import ai.plats.domain.user.service.UserUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    UserUpdateService userUpdateService;

    @Autowired
    UserJoinService userJoinService;


    @Autowired
    WritingService writingService;

    @RequestMapping({"/", "/goHome"})
    public String goHome(Model m, Integer cPage, Integer size) {

        if (cPage == null|| cPage == 1 || cPage == 0) {
            System.out.println("cpage ====>" + cPage);
            cPage = 0;
            size = 10;
        } else {
            cPage=cPage-1;
            size = 10;
        }
        System.out.println("request된 페이징"+cPage);
        Page<Writing> pageOfWriting = writingService.boardList(cPage, size);
        List<Writing> writingList = pageOfWriting.getContent();
        System.out.println("조회된 최종 게시물 record"+pageOfWriting.getTotalElements());

        Map<String, Object> navi = writingService.getPagination(cPage,  (int) pageOfWriting.getTotalElements());

        m.addAttribute("writingList", writingList);
        m.addAttribute("navi", navi);

        return "home/home";
    }


    @GetMapping("/goJoin")
    public String goJoin() {
        System.out.println(">>" + "Join");

        return "join/signUp";
    }

    @GetMapping("/goMyInfo")
    public String goMyInfo(Principal principal, Model model) {
        System.out.println(">>" + "MyInfo");
        String username = principal.getName();
        System.out.println("username --> " + username);
        Optional<User> vo = userUpdateService.findUserByEmail(username);

        if (vo.isPresent()) {

            model.addAttribute("userInfo", vo.get());

            return "myInfo/myInfo";
        } else {
            System.out.println("myinfo 정보 조회 오류 발생");
            return "home/home";
        }

    }

    @GetMapping("/goInfoUpdate")
    public String goInfoUpdate(Principal principal, Model model) {
        System.out.println(">>" + "goInfoUpdate");
        String username = principal.getName();
        System.out.println("username --> " + username);
        Optional<User> vo = userUpdateService.findUserByEmail(username);

        if (vo.isPresent()) {
            model.addAttribute("userInfo", vo.get());
            return "myInfo/myInfoUpdate";
        } else {
            System.out.println("myinfo 정보 수정 페이지 접근 오류 발생");
            return "myInfo/myInfo";
        }

    }

    @PostMapping("/procWithDraw")
    public String procWithDraw(Principal principal) {
        System.out.println(">>" + "procWithDraw");
        String username = principal.getName();
        System.out.println("withdraw username --> " + username);
        Optional<User> vo = userUpdateService.findUserByEmail(username);

        if (vo.isPresent()) {
            userUpdateService.withDraw(vo.get());
            return "home/home";
        } else {
            System.out.println("회원탈퇴 접근 오류 controller");
            return "home/home";
        }

    }


    @RequestMapping(value = "/procJoin", method = RequestMethod.POST)
    @ResponseBody
    public String chkEmail(HttpServletRequest request) {
        String email = request.getParameter("email");
        String nick = request.getParameter("nick");
        String pwd = request.getParameter("pwd");

        Optional<User> vo = userJoinService.findClientByEmail(email);

        if (vo.isPresent() == false) {
            User joinVo = userJoinService.joinClient(new User(email, pwd, nick, "N"));
            if (joinVo != null) return "가입 성공";
            else return "가입 실패 ! 오류 발생 !";
        } else {
            return "중복된 이메일 입니다.";
        }
    }

    @RequestMapping(value = "/procUpdateMyInfo", method = RequestMethod.POST)
    @ResponseBody
    public String updateMyInfo(HttpServletRequest request, Principal principal) {


        // 이제 기존에 있는 값을 principal로 찾아서 원본값으로 조회하고 save 해주면될듯

        String content = request.getParameter("content");
        String kinds = request.getParameter("kinds");
        Optional<User> vo;
        //이메일 중복검
        if (kinds.equals("email")) {
            vo = userUpdateService.findUserByEmail(content);  //새로운 정보(바꿀 email) 기준으로 중복 탐색

            if (vo.isPresent()) {
                return "중복된 이메일입니다. 다른 이메일을 입력해주세요.";
            } else {
                Optional<User> changeVo = userUpdateService.findUserByEmail(principal.getName());
                changeVo.get().setUserEmail(content);
                changeVo.get().setModDate(LocalDateTime.now());
                userUpdateService.updateMyInfo(changeVo.get());
                return "이메일 변경 완료";
            }

        }

        if (kinds.equals("nick")) {
            //nick 변경시에는 vo를 이용했음  , nick 은 중복검사를 하지 않아서
            vo = userUpdateService.findUserByEmail(principal.getName());
            vo.get().setUserNick(content);
            vo.get().setModDate(LocalDateTime.now());
            userUpdateService.updateMyInfo(vo.get());
        }
        return "닉네임 변경 완료";
    }

    @RequestMapping(value = "/procChangePwd", method = RequestMethod.POST)
    @ResponseBody
    public String procChangePwd(HttpServletRequest request, Principal principal, Model model) {

        String ori_pwd = request.getParameter("ori_pwd");
        String new_pwd = request.getParameter("new_pwd");
        String result = userUpdateService.changePwdLogic(principal.getName(), ori_pwd, new_pwd);

        if (result.equals("변경 성공")) {
            return "변경 성공";
        } else {
            return "변경 실패";
        }
    }


}
