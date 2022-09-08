package com.yangteng.workbackstage.annotation.impl;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 此类是对AuthorHimself注解的实现
 */
@Aspect
@Component
@Slf4j
public class AuthorHimselfImpl {

    @Around("@annotation(com.yangteng.workbackstage.annotation.AuthorHimself)")
    public Object methodExporter(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获得传入书籍的id
        Object[] args = joinPoint.getArgs();
        // 规定最后一个为书籍id，如果没有则抛方法格式错误异常

        return null;
    }
}
