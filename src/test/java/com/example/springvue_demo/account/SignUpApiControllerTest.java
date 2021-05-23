package com.example.springvue_demo.account;


import com.example.springvue_demo.account.dto.MemberDto;
import com.example.springvue_demo.account.entity.MemberEntity;
import com.example.springvue_demo.account.web.SignUpApiController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SignUpApiController.class) //@Controller등을 사용할 수 있다
@RunWith(SpringJUnit4ClassRunner.class)
//@WithUserDetails// 권한이 인증된 회원임을 나타내는 Annotaion
public class SignUpApiControllerTest {

    //@Required~~
    // 이것은 FINAL을 가진 녀석을 생성자로 만든다. 즉 customized 생성자로 만들어진다는 의미이기에, 기본생성자를 삭제한다.
    // 생성자 주입<< 요거는 Spring 코드 내에서만 유효해요
    // 그리고 여기는 자바테스트지 spring이 아니다.
    // 생성자 주입은 spring이며, 현재는 자바단에서의 테스트는 될리 없다...


    @Autowired
    public MockMvc mvc; // web api 테스트 시 사용(http get, post), 스프링 mvc의 시작.
    //일단 mock이라는 게, 컨트롤러의 서비스를 mock으로 정의하고, return 값을 맘대로 바꾸는 것
    //가짜객체 만들어서 리턴값을 지정해줘서 그 객체의존성을 제거하고 테스트하는


    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("회원가입 test")
    public void signUpTest() {

        //given...
        MemberDto dto = new MemberDto();
        dto.setUserName("회원1");
        dto.setPassword("회원1");
        dto.setEmail("회원1");

        //when...
        Optional<MemberEntity> entity = Optional.ofNullable(dto.toEntity());
        MemberEntity signUpEntity = entity.orElseGet(MemberEntity::new);

        //then
        Assert.assertThat(signUpEntity.getEmail(), CoreMatchers.notNullValue());
        Assert.assertThat(signUpEntity.getUserName(), CoreMatchers.notNullValue());
        Assert.assertThat(signUpEntity.getPassword(), CoreMatchers.notNullValue());
        System.out.println(signUpEntity.getPassword());
    }


    @Test
    @DisplayName("회원가입 valid Test for http Request")//postman과 비슷한 역할을 하는 mockmvc
    @WithMockUser(roles = "ADMIN")
    public void signUpTestForHttpRequest() throws Exception {

        //given...
        MemberDto dto = new MemberDto();
        dto.setUserName("회원1");
        dto.setPassword("회원1");
        dto.setEmail("test@test.com");

        //when...
        mvc.perform(
                MockMvcRequestBuilders.post("/api/signUps")
                        .contentType(MediaType.APPLICATION_JSON)//json 형식으로 데이터를 보냄.
                        .content(objectMapper.writeValueAsString(dto))
                        .with(csrf()) // 이 부분
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

    }

    //403 에러 -> .with(csrf()) // 이 부분
    //400 에러 ->
    //API에서 @RequestBody로 전달받을 객체로 사용한 Hello의 접근제한자가 문제였다. 간단히 Hello를 private로 API를 작성한 Controller 내부에 선언해놨었는데, 이 때문에 HttpServlet이 JSON 메세지를 Hello 객체라고 인식하지 못했다.
    //--> postMapping에 (consumes = MediaType.APPLICATION_JSON_VALUE
    //                , produces = MediaType.APPLICATION_JSON_VALUE) 추가

}
