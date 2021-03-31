package ai.plats.domain.account.web;

import ai.plats.domain.account.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserLoginController {

    @Autowired
    UserLoginService userLoginService;

    @GetMapping("/goLogin")
    public String goLogin() {
        System.out.println(">>" + "login");

        return "login/signIn";
    }

    @PostMapping("/goLoginError")
    public String goLoginError(Model model) {
        System.out.println(">>" + "goLoginError redirect");
        model.addAttribute("errorMsg","잘못된 로그인 정보입니다.");

        return "login/signIn";
    }




}
