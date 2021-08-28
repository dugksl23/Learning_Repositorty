package com.example.logaopdemo.model;//package com.example.adviceaop.model;

import com.example.logaopdemo.annotation.TestLog;
import com.example.logaopdemo.domain.TestLogBody;
import com.example.logaopdemo.domain.TestLoggerBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestLoggerImpl implements TestLoggerBase {


    @Override
    public void log(String msg) {

        log.info("Test log msg :, {}", msg);

    }

    @Override
    public void log(TestLogBody testLog) {

        String obj = testLog.toString();
        log.info("log : {}", obj);

    }
}
