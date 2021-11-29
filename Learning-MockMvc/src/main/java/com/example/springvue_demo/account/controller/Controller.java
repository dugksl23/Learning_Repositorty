package com.example.springvue_demo.account.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
@RequestMapping("/member")
public class Controller {

    @GetMapping("/login")
    public String loginMemberPage() {
        return "/member/login";
    }

    @GetMapping("/signUp")
    public String signUpMemberPage() {
        return "/member/signUp";
    }


}
