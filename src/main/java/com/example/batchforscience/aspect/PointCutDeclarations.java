package com.example.batchforscience.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointCutDeclarations {

	@Pointcut("execution(* com.example.batchforscience.listener.JobCompletionListener.beforeJob(*))")
	public void beforeJob() {
		
	}
	
	@Pointcut("execution(* com.example.batchforscience.listener.JobCompletionListener.afterJob(*))")
	public void afterJob() {
		
	}
}
