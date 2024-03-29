package com.cos.aop.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

// Exception을 낚아채기
@RestController
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = IllegalArgumentException.class)
    public String handlerArgument(IllegalArgumentException e) {
        return e.getMessage();
    }
}
