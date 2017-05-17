package com.boot.spring.service;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AopAspect {

	@SuppressWarnings("unchecked")
	@Around("execution (java.util.Map com.boot.spring.controller.JPAController.*( .. ) ) ")
	public Object invoke(ProceedingJoinPoint point){
		Map<String, Object> map = null;
        try {
            Object[] args = point.getArgs();
            Object val = point.proceed(args);
            map = (Map<String, Object>) val;
        } catch (Throwable e) {
            e.printStackTrace();
            map = new HashMap<>();
            map.put("msg", e.toString());
        }
        map.put("aop", "拦截了一个请求");
        return map;
    }

	@Around("execution (java.lang.Object com.boot.spring.controller.JPAController.*( .. ) ) ")
	public Object invoke2(ProceedingJoinPoint point){
		Object str = null;
        try {
            Object[] args = point.getArgs();
            Object val = point.proceed(args);
            str = val;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return str;
    }

//	@Around("execution (com.boot.spring.entity.OrderJPA com.boot.spring.controller.JPAController.*( .. ) ) ")
	@Pointcut(value="execution(* cut(..))")
	public void pointcut(){
		System.out.println("pointcut()");
	}
	@Around(value="pointcut()")
	public Object invoke3(ProceedingJoinPoint point){
		String str = null;
		try {
			Object[] args = point.getArgs();
			Object val = point.proceed(args);
			str = (String) val;
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return str;
	}
}
