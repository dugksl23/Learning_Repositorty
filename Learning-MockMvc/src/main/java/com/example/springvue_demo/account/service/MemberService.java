package com.example.springvue_demo.account.service;


import com.example.springvue_demo.account.entity.MemberEntity;
import com.example.springvue_demo.account.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public void signUpMember(MemberEntity memberEntity) {

        memberRepository.save(memberEntity);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MemberEntity member = memberRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 ID 입니다. Error :" + username));

        member.setLastLoginTime(LocalDateTime.now());
        member.setRole("role_user");

        return User.builder()

                .username(member.getIdx().toString())
                .password(member.getPassword())
                .roles(member.getRole())
                .build();
    }


}

