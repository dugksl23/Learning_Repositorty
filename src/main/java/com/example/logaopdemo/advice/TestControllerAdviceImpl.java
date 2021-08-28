package com.example.logaopdemo.advice;


import com.example.logaopdemo.annotation.TestAdvice;
import com.example.logaopdemo.annotation.TestLog;
import com.example.logaopdemo.domain.TestCode;
import com.example.logaopdemo.domain.TestLogBody;
import com.example.logaopdemo.model.TestLoggerImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@Slf4j
//@TestAdvice
// TestAdvice (interfave)의 구현체
@RestControllerAdvice
public class TestControllerAdviceImpl {

    public TestControllerAdviceImpl(TestLoggerImpl testLogger) {
        log.info("와요?");
        this.testLogger = testLogger;
    }

    private final TestLoggerImpl testLogger;


    @ExceptionHandler(NullPointerException.class)
    public void nullPointException(Exception e, TestLogBody testLogBody) {
        log.info("handler 오나요?");
//        testLogger.log(String.valueOf(TestLogBody.builder()
//                .logTime(new Date())
//                .resultCode(TestCode.FAILURE))
//        );


    }

}
