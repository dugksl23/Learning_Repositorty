package com.example.logaopdemo.domain;//package com.example.adviceaop.domain;

import com.example.logaopdemo.annotation.TestLog;

// Log 용 인터페이스
public interface TestLoggerBase {

    public void log(String msg);

    public void log(TestLogBody testLogBody);

}
