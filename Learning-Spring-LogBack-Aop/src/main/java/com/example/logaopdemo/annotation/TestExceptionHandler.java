package com.example.logaopdemo.annotation;//package com.example.adviceaop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TestExceptionHandler {
    // throwable은 exception의 아버지.
    Class<? extends Throwable> value();

}
