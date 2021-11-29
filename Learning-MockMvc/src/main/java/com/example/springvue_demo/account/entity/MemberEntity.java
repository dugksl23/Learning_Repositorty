package com.example.springvue_demo.account.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String userName;
    private String password;
    private String email;
    private String phoneNo;
    private String role;

    @CreationTimestamp
    private LocalDateTime createdDate;
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    private String useYn;

    private LocalDateTime lastLoginTime;
    @Builder
    public MemberEntity(Long idx, String userName, String password, String email, String phoneNo, String role, LocalDateTime createdDate, LocalDateTime updatedDate, String useYn, LocalDateTime lastLoginTime) {
        this.idx = idx;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phoneNo = phoneNo;
        this.role = role;
        this.useYn = useYn;
        this.lastLoginTime = lastLoginTime;
    }
    private String bCryptPasswordEncoder(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }

}
