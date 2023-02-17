package com.cos.aop.config;

import com.cos.aop.dto.CommonDto;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;
import java.util.Map;

// @Configuration은 설정만, 나머지는 @Component
@Component
@Aspect
public class BindingAdvice {

    private static final Logger log = LoggerFactory.getLogger(BindingAdvice.class);

    @Before("execution(* com.cos.aop.controller..*Controller.*(..))")
    public void BeforeTestCheck() {

        // request 값 처리
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        System.out.println("주소: " + request.getRequestURI());

        System.out.println("전처리 로그");
    }

    @After("execution(* com.cos.aop.controller..*Controller.*(..))")
    public void AfterTestCheck() {

        System.out.println("후처리 로그");
    }

    @Around("execution(* com.cos.aop.controller..*Controller.*(..))")
    public Object validCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();
        String method = proceedingJoinPoint.getSignature().getName();

        System.out.println("type: " + type);
        System.out.println("method: " + method);

        Object[] args = proceedingJoinPoint.getArgs();

        for(Object arg: args) {
            if(arg instanceof BindingResult) {
                BindingResult bindingResult = (BindingResult) arg;

                if(bindingResult.hasErrors()) {
                    Map<String, String> errorMap = new HashMap<>();

                    for(FieldError error: bindingResult.getFieldErrors()) {
                        errorMap.put(error.getField(), error.getDefaultMessage());
                        // 로그 레벨: error < warn < info < debug (레벨이 클수록 하위 로그를 출력, 예: info 레벨이면 error, warn, info만 출력)
                        log.warn(type + "." + method + "() -> 필드: " + error.getField() + ", 메세지: " + error.getDefaultMessage());
                        // 로그를 파일로 생성하면 관리가 힘들어 진다.
                        log.debug(type + "." + method + "() -> 필드: " + error.getField() + ", 메세지: " + error.getDefaultMessage());
                    }

                    return new CommonDto<>(HttpStatus.BAD_REQUEST.value(), errorMap);
                }
            }
        }
        return proceedingJoinPoint.proceed(); // 함수 스택 실행
    }
}
