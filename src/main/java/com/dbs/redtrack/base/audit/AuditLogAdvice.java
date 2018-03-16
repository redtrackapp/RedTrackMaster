package com.dbs.redtrack.base.audit;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dbs.redtrack.business.anotation.AuditLog;
import com.dbs.redtrack.integration.dao.UserRegistrationDAO;
import com.dbs.redtrack.request.dto.CreateIncidentRequestDTO;

 
@Aspect
@Component
public class AuditLogAdvice {

	private static final Logger logger = Logger.getLogger(AuditLogAdvice.class);
	
	@Autowired 
	UserRegistrationDAO userRegistrationDAO;
	
//	 @Before("execution(* *.*(..)) && @annotation(testAnnotation) ")
//	  public void myBeforeLogger(JoinPoint joinPoint, TestAnnotation testAnnotation) {
//		 
//	 }
	
	@Before("execution(public * com.dbs.redtrack.controller.*Controller.*(..)) && @annotation(auditLog) ")
	public void auditLog(JoinPoint joinPoint, AuditLog auditLog) {
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>AuditLogAdvice :: auditLog :: start: service(" + auditLog.getService() +")");

		String userOTPID = null;
		boolean  isValidUser = false;
		final Signature signature = joinPoint.getStaticPart().getSignature();
		
	  if (signature instanceof MethodSignature) {
		  logger.info(">>>>>>>>>>>>>>>>Inside methodSignature<<<<<<<<<<<<<<<<<<<<<<<");
	        final MethodSignature ms = (MethodSignature) signature;
	        String[] params = ms.getParameterNames();
	        for (String param : params) {
	        	logger.info(">>>>>>>>>>>>>>>>param: "+ param);
	            // here how do i get parameter value using param ?
	        }
	    } else {		  
	    	logger.info(">>>>>>>>>>>>>>>>Inside object Request param<<<<<<<<<<<<<<<<<<<<<<<");
			Object[] signatureArgs = joinPoint.getArgs();
			for (Object signatureArg: signatureArgs) {
				logger.info("Arg: " + signatureArg);
				if(signatureArg instanceof CreateIncidentRequestDTO) {
					userOTPID = ((CreateIncidentRequestDTO) signatureArg).getUserotpid();
					break;
				}			      
			}
			logger.info(">>>>>>>>>>>>>>>>userOTPID: "+ userOTPID);			   
	    }	   
	}
	
	
	
}
