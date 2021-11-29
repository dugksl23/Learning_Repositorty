package com.example.logaopdemo.domain;//package com.example.adviceaop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Component
@Slf4j
@ToString
public class TestLogBody {

    private String uuid;
    private Date logTime;
    private TestCode resultCode;

    public TestLogBody() {
    }

    @Builder
    public TestLogBody(Date logTime, TestCode resultCode) {
        this.uuid = makeUuid();
        this.logTime = logTime;
        this.resultCode = resultCode;
    }

    private String makeUuid() {
        // 현재 날짜 + uuid* 8자리)
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String now = format.format(new Date());
        String uuid = String.format("%040d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16));
        uuid = uuid.substring(uuid.length() - 8, uuid.length());
        log.info("uuid, {}", uuid);

        return now + uuid;
    }
}
