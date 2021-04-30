package com.onboard.controller;


import com.onboard.dto.Member;
import com.onboard.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService mService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/login")
    public String login() {
        System.out.println("로그인 페이지 도착");
        return "login";
    }

    @RequestMapping("/signup")
    public String signup() {
        System.out.println("회원가입 페이지 도착");
        return "signup";
    }

    @RequestMapping("/signupProc")
    public String signupProc(Member m, Model model) {
        System.out.println("회원가입 진행중");
        m.setPassword(passwordEncoder.encode(m.getPassword()));
        Member member = mService.signUp(m);

        model.addAttribute("member", member);
        return "home";
    }

    @RequestMapping("/board")
    public String board() {
        return "board";
    }


}

