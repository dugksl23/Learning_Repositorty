package com.example.logaopdemo.Service;


import com.example.logaopdemo.annotation.TestLog;
import com.example.logaopdemo.domain.TestCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.logaopdemo.domain.TestCode.SERVICE_ERROR;

@Service
@Slf4j
public class TestService {

    @TestLog(exception = SERVICE_ERROR, CustomExceptionClass = Exception.class)
    public void testException(String args) {

        String str = "";
        log.info("method block 입니다~");
        if (str == "") {
            throw new NullPointerException("null 입니다.");
        }

    }

}
