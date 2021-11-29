package com.example.logaopdemo.Service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestServiceTest {

    @Autowired
    private TestService testService;

    @Test
    @DisplayName("Exception Test")
    public void exceptionTest(){

        testService.testException("와우");
    }


}