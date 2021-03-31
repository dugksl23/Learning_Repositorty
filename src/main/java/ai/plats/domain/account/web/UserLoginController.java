package ai.plats.domain.account.web;

import ai.plats.domain.account.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserLoginController {

    @Autowired
    UserLoginService userLoginService;

    @GetMapping("/goLogin")
    public String goLogin() {
        System.out.println(">>" + "login");

        return "login/signIn";
    }






}
