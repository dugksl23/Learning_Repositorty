package com.example.logaopdemo.aop;//package com.example.adviceaop.aop;


import com.example.logaopdemo.annotation.TestLog;
import com.example.logaopdemo.domain.TestCode;
import com.example.logaopdemo.domain.TestLogBody;
import com.example.logaopdemo.model.TestLoggerImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.ExecutionException;


@Aspect // Aspect 어노테이션을 붙여서 이 클래스 aop class (구현체)임을 나타낸다
@Component // bean으로 등록하여 필요에 따라서 사용한다.
@Order
@RequiredArgsConstructor
@Slf4j
public class LogAop {

    private final TestLoggerImpl testLogger;



    //https://sup2is.tistory.com/59
    @Before("@annotation(com.example.logaopdemo.annotation.TestLog)")
    public void before() {
        log.info("before method execute");
    }

    //https://hamait.tistory.com/317

    @Around("@annotation(com.example.logaopdemo.annotation.TestLog)")
    // @Around() 는 메타 데이터로 넘어간 어노테이션(@interface)가 달린 타켓 메서드에서 Advice 를 실행하겠다는 의미이다.
    // @Around() 메타 데이터로는, 해당 패키지의 경로에서 있는 어노테이션(@interface).
    public Object pointCut(ProceedingJoinPoint joinPoint) throws Throwable {


        log.info(" ======== start joinPoint ==========");
        log.info(" ======== start method args 가져오기 ==========");
        // @Around 가 실행된 메서드의 args 를 가져오기.
        Object[] args = joinPoint.getArgs();
        log.info("args, {}", args[0]);

        log.info(" ======== end method args 가져오기  ==========");

        log.info(" ======== start method Signature 가져오기 ==========");
        // 메소드의 구성요소 중, 메소드의 이름과, 메소드의 파라미터를 가리켜서 메소드의 시그니쳐라고 한다.
        MethodSignature methodSignature = (MethodSignature) joinPoint.getStaticPart().getSignature();
        Method method = methodSignature.getMethod();
        Object[] parameters = method.getParameters(); // 메서드의 파라미터의 변수명을 가져온다.
        log.info(" ======== end method Signature 가져오기 ==========");

        log.info(" ======== start method Annotation 가져오기 ==========");
        // advise가 실행된 메소드의 어노테이션의 접근하는 함수.
        TestLog testLog = method.getAnnotation(TestLog.class);
        // 함수의 어노테이션들을 배열로 가져오기.
        Annotation[] declaredAnnotations = method.getDeclaredAnnotations();


        // log를 찍기 위해서 기본 default 값들을 가져온 것이다.
        TestCode exception = testLog.exception();
        TestCode success = testLog.success();
        TestCode failure = testLog.failure();
        String name = testLog.name();

        log.info("parameters, {}", parameters[0].toString());
        log.info(" ======== end method Annotation 가져오기 ==========");
        try {
            log.info(" ======== start methodExecute ==========");
            Object result = joinPoint.proceed(args);
            log.info(" ======== end methodExecute ==========");

            if (args != null) {
                testLogger.log(TestLogBody.builder()
                        .resultCode(success)
                        .logTime(new Date())
                        .build());

            }

        } catch (Throwable throwable) {
            log.info("============= excpetion catch =====================r");
            throwable.printStackTrace();
            Throwable error = getException(throwable);
            log.info("============= testLogger  찍기 전 =====================r");

            testLogger.log(TestLogBody.builder()
                    .resultCode(failure)
                    .logTime(new Date())
                    .build());
            testLogger.log(exception.toString());
            // customException 클래스를 주소값으로 보내고, callback으로 정의한뒤 사용.
            Throwable throwable1 = customExceptionClass(testLog.CustomExceptionClass(), throwable);
            log.info("throwable 1 날리기 전, {}", throwable1.toString());
            throw throwable1;

        }
        log.info("또 왔어요~");
        return null;
    }


    @After("@annotation(com.example.logaopdemo.annotation.TestLog)")
    public void after(JoinPoint joinPoint) {
        log.info("after method execute");
    }


    private Throwable getException(Throwable e) {
        log.info("getException, {}", e.toString());
        if (ExecutionException.class.isAssignableFrom(e.getClass())) {
            return e.getCause();
        }

        return e;
    }

    private Throwable customExceptionClass(Class<? extends Throwable> CustomExceptionClass, Throwable e) {

        try {

            log.info("Exception, {}", e.toString());
            Constructor<? extends Throwable> constructor = CustomExceptionClass.getConstructor(Throwable.class);
            Throwable throwable = constructor.newInstance(e);
            log.info("여긴 오나요?");
            return throwable;

        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException noSuchMethodException) {
            noSuchMethodException.printStackTrace();
            return null;
        }

    }

}
