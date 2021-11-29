package com.example.logaopdemo.domain;//package com.example.adviceaop.domain;


import org.springframework.stereotype.Component;

public enum TestCode {

    SUCCESS("200"),
    FAILURE("405"),

    //===== service error
    SERVICE_ERROR("404"),

    NONE("NONE");

    TestCode(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

}
