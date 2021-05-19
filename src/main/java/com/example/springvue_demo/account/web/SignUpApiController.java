package com.example.springvue_demo.account.web;


import com.example.springvue_demo.account.dto.SignUpDto;
import com.example.springvue_demo.account.entity.SignUpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class SignUpApiController {

    @PostMapping(value="/signUp",consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SignUpDto> signUp(@Valid @RequestBody SignUpDto signUp) {

        Optional<SignUpEntity> entity = Optional.ofNullable(signUp.toEntity());
        SignUpEntity signUpEntity = entity.orElseGet(SignUpEntity::new);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
