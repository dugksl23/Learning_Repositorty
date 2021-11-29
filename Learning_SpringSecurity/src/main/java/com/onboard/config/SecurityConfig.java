
package com.onboard.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity//Security가 낚아채는 것을 방지하는 annotaion
//configuration을 선언하여 시큐리티에서 제공하는 기본적인 기능을 재정
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/", "/member/**", "/h2_db/**").permitAll()//디스패처를 통한 annotation 매핑을 가능하게 하려는 주소를 기입.
                .anyRequest().authenticated()//상위의 조건에 해당하는 자만을 인가하겠다는 메소드
                .and()
                .formLogin()// 로그인 진행 시작 메소드
                .loginPage("/member/login")//로그인이 진행될 페이지로 redirect이며, 기본 요청 url은 localhost/login
                .loginProcessingUrl("/member/loginProcess")
                .defaultSuccessUrl("/")//로그인 후의 redirect url
                .usernameParameter("email")
                .passwordParameter("password")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/member/logout")
                .logoutSuccessUrl("/")//로그아웃 후, home으로 redirect
                .invalidateHttpSession(true)//로그아웃 후에, session 값 비우기// 창을 끄기만 해도 로그아웃
                .permitAll();

        http.csrf().disable();
        http.headers().frameOptions().disable();

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


}

