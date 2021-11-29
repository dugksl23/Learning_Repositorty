package com.example.logaopdemo.annotation;//package com.example.adviceaop.annotation;


import com.example.logaopdemo.domain.TestCode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD}) // 메소드 선언시 해당 위치에서 실행 포인트 지정
@Retention(RetentionPolicy.RUNTIME) // 어노테이션의 적용 범위 (runtime으로 적용, jvm 컴파일 이후에도 참조)
public @interface TestLog {
    // Log용 정보를 담고 있는 DTO.
    // 해당 어노테이션을 통해서 에러를 커스텀하기 위한 용도.

    public String name() default "";

    public TestCode success() default TestCode.SUCCESS;

    public TestCode failure() default TestCode.FAILURE;

    public TestCode exception() default TestCode.NONE;

    Class<? extends Exception> CustomExceptionClass();

}
