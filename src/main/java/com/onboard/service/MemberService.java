package com.onboard.service;


import com.onboard.dao.MemberRepository;
import com.onboard.dto.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(MemberService.class);
    private final MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }//스프링 5부터 autowird 역할을 하며 주입을 한다.


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        List<GrantedAuthority> authorities = new ArrayList<>();
        System.out.println("loadUserByUserName에서의 username 값" + email);
        //id 조회했을 때 없을 경우를 위한 if문 분기점
        Optional<Member> member = memberRepository.findByEmail(email);
       /* Optional.ofNullable(memberRepository.findByEmail(email).orElseThrow(()
                -> new UsernameNotFoundException("user name not founded")));
*/
        if (member != null) {

            System.out.println("로그인 시도 중");
            System.out.println("로그인 시도후 password" + member.get().getEmail() + member.get().getPassword());
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            //authorities.add(new SimpleGrantedAuthority("ROLE_USER")); //앞에 ROLE_을 붙여야 home.html에서 정상적으로 hasRole()구문을 인식
            //Security를 통해 인증을 받지 않은 회원은 로그인 페이지로 안내하며,
            // 로그인을 받은 회원은 인가된 객체를 반환하, 인증이 된 후에는 인가된 회원인 경우,
            // 로그인을 유지.

        }

        return new User(member.get().getEmail(), member.get().getPassword(), authorities);
    }

    public Member loginProcess(Member m) {

        Member member = memberRepository.findByEmailAndPassword(m.getEmail(), passwordEncoder.encode(m.getPassword()));
        System.out.println("로그인 프로세스 도착후");

        return member;
    }

    public Member signUp(Member m) {
        m.setWidthrawal("N");
        Date time = new Date();
        m.setReg_date(new java.sql.Date(time.getTime()));

        Member member = memberRepository.save(m);
        return member;
    }

}
