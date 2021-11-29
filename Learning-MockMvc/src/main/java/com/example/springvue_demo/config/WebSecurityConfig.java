package com.example.springvue_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 filter chain을 자동으로 등록시켜주는 annotaion이며, cosutomSpringSecurity를 활성화하겠다는 annotation
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring()
                .antMatchers("/resources/**", "/resources/static/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/", "/index", "/member/signUp")
                .permitAll() //모두 접근 가능 페이지

                .antMatchers("/member/**").access("hasRole('ROLE_USER')") //로그인 및 회원가입을 위한 접근 페이지
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest()
                .permitAll()
                //.authenticated() //어떠한 접근이든 인증을 해야 한다는 것이다.
                .and()
                .csrf()
                .ignoringAntMatchers("/h2_db/**")
                .and()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .formLogin()
                .loginPage("/member/login").defaultSuccessUrl("/index")
                .failureUrl("/goTest")
                //.failureHandler(authFailureHandler())

                .permitAll()

                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/goHome")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .permitAll();

        //authorizeRequest 는 인증을 어떻게 할건지 (보안을 어떻게 할것인지에 대한 것)
        //antMatchers 특정 요청에 대한 리턴 설정  저기에는  루트랑 home은 모두 허가해준다
        //anyRequest() 나머지는 authenticated 인증을 거쳐야한다
        // and는 그냥 연결하는 말
        //formloing은 로그인 요청이 무엇이냐를 말함
        //loginPage는 로그인 페이지가 여기라는 의미
        //permitAll  모두 허가
        //logout  로그아웃 설정

        // .antMatchers("/admin/**").hasRole("ADMIN")   라고적으  //어드민 밑에 접근하려면 ADMIN롤을 가지고있어야한다. (예를들어서 작성한것)

        //hello를 누르는순간 anyRequest().authenticated에 걸린다 .
        //인증이 필요한 페이지에 들어왔기 때문에  fillter bean이 그걸 가로채서
        //formLogin을 처리하는 쪽으로 보내서 거기서 Login("/login")으로 보낸다.
        //이 요청 곧 MvcConfig에서 뷰리졸버를 통해 login.html로 보낸다.

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


}
