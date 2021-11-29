package com.example.springvue_demo.account.dto;


import com.example.springvue_demo.account.entity.MemberEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {

    @NotBlank(message = "필수 입력값입니다.")
    private String userName;
    @NotBlank(message = "필수 입력값입니다.")
    private String password;

    @NotBlank(message = "필수 입력값입니다.")
    @Email
    private String email;
    @NotBlank(message = "필수 입력값입니다.")
    private String phoneNo;

    private String useYn = "Y";
    private String role;

    public MemberDto(String userName, String password, String email, String useYn) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.useYn = useYn;
    }

    public MemberEntity toEntity() {

        return MemberEntity.builder()
                .phoneNo(phoneNo)
                .userName(userName)
                .password(password)
                .role(role)
                .email(email)
                .useYn(useYn)
                .build();
    }
}
