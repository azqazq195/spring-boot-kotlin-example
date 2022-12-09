package com.example.springbootkotlinexample.common.aop

import com.example.springbootkotlinexample.config.logger
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component

@Aspect
@Component
class ServiceAspect {
    private val log = logger()

//    @Pointcut("execution(* com.example.springbootkotlinexample.domain..*.*(..))")
//    fun request() {
//    }
//
//    @Before("request()")
//    fun before(joinPoint: JoinPoint) {
//        log.info("before '{}' service", getMethodName(joinPoint))
//    }
//
//    @After("request()")
//    fun after(joinPoint: JoinPoint) {
//        log.info("after '{}' service", getMethodName(joinPoint))
//    }
//
//    @AfterReturning("request()")
//    fun afterReturning(joinPoint: JoinPoint) {
//        log.info("after returning '{}' service", getMethodName(joinPoint))
//    }
//
//    @AfterThrowing("request()")
//    fun afterThrowing(joinPoint: JoinPoint) {
//        log.info("after throwing '{}' service", getMethodName(joinPoint))
//    }
//
//    @Around("request()")
//    @Throws(Throwable::class)
//    fun around(pjp: ProceedingJoinPoint): Any {
//        log.info("around before '{}' service", getMethodName(pjp))
//        val result: Any = pjp.proceed()
//        log.info("around after '{}' service", getMethodName(pjp))
//        return result
//    }
//
//    private fun getMethodName(joinPoint: JoinPoint): String {
//        return joinPoint.signature.name
//    }
}