package com.cos.aop.config;

import com.cos.aop.dto.CommonDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

// @Configuration은 설정만, 나머지는 @Component
@Component
@Aspect
public class BindingAdvice {

    @Before("execution(* com.cos.aop.controller..*Controller.*(..))")
    public void BeforeTestCheck() {

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
                    }

                    return new CommonDto<>(HttpStatus.BAD_REQUEST.value(), errorMap);
                }
            }
        }
        return proceedingJoinPoint.proceed(); // 함수 스택 실행
    }
}
