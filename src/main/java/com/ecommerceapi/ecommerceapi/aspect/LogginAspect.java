package com.ecommerceapi.ecommerceapi.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LogginAspect {
    @Before("execution(* com.ecommerceapi.ecommerceapi..*.*(..))")
    public void loggin(JoinPoint joinpoint) {
    log.info(" o método" + joinpoint.getSignature().toShortString() + " está prestes a ser chamado");
}
}
