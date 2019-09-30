package com.example.batchforscience.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeAspect {

	private static final Logger logger = LoggerFactory.getLogger(ExecutionTimeAspect.class);
	long startTime;
	
	@Before("com.example.batchforscience.aspect.PointCutDeclarations.beforeJob()")
	public void measureStartTime() {
		startTime = System.nanoTime();
	}
	
	@AfterReturning("com.example.batchforscience.aspect.PointCutDeclarations.afterJob()")
	public void loggTimeOfExecution(JoinPoint joinPoint) {
		MethodSignature sign = (MethodSignature) joinPoint.getSignature();
		long endTime = System.nanoTime();
		logger.info(">>>>> {} execution time: {} milliseconds", sign.toShortString(), (double) (endTime - startTime) / 1000000);
	}
	
}
