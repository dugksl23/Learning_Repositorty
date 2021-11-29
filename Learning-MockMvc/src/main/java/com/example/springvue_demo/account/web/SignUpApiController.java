package com.example.springvue_demo.account.web;


import com.example.springvue_demo.account.dto.MemberDto;
import com.example.springvue_demo.account.entity.MemberEntity;
import com.example.springvue_demo.account.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SignUpApiController {

    private MemberService memberService;
    private Logger logger = LoggerFactory.getLogger(SignUpApiController.class);

    @PostMapping(value = "/signUp")
    //consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
    private ResponseEntity<MemberEntity> signUp(@Valid @RequestBody MemberDto memberDto) {
        System.out.println("들어와요?");
        logger.error("message {},"+"들어오나요?");
        Optional<MemberEntity> signUpEntity = Optional.of(memberDto.toEntity()); //of를 통해서 nullpoint Exception을 발생시킨다.
        memberService.signUpMember(signUpEntity.get());
        //SignUpEntity signUpEntity = entity.orElseGet(SignUpEntity::new);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
