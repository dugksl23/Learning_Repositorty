package ai.plats.domain.account.service;


import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFailureHandler implements AuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

            System.out.println("로그인 실패 핸들러");

            String errorMsg=null;
            if(e instanceof UsernameNotFoundException){

                errorMsg="잘못된 로그인 정보입니다. 다시 입력해주세요. ";
            }

        //forward post
        httpServletRequest.getRequestDispatcher("/goLoginError").forward(httpServletRequest,httpServletResponse);
    }
}
