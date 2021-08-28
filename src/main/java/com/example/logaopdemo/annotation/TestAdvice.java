package com.example.logaopdemo.annotation;//package com.example.adviceaop.annotation;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE}) // Anotation을 적용할 위치 (Type 선언 시)
@Retention(RetentionPolicy.RUNTIME) // (컴파일 이후에도 JVM에 의해서 참조 가능)
@ControllerAdvice
public @interface TestAdvice {
}
