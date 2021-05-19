package com.example.springvue_demo.account.dto;


import com.example.springvue_demo.account.entity.SignUpEntity;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class SignUpDto {

    @NotBlank(message = "필수 입력값입니다.")
    private String userName;
    @NotBlank(message = "필수 입력값입니다.")
    private String password;

    @NotBlank(message = "필수 입력값입니다.")
    @Email
    private String email;

    @Builder
    public SignUpDto(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public SignUpEntity toEntity() {

        return SignUpEntity.builder()
                .password(password)
                .userName(userName)
                .email(email)
                .build();
    }
}
