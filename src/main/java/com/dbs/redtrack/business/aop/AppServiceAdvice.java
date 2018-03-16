package com.dbs.redtrack.business.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dbs.redtrack.constants.BusinessConstants;
import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.integration.dao.UserRegistrationDAO;
import com.dbs.redtrack.jpa.entity.User;
import com.dbs.redtrack.request.dto.CreateIncidentRequestDTO;

 
@Aspect
@Component
public class AppServiceAdvice {

	private static final Logger logger = Logger.getLogger(AppServiceAdvice.class);
	
	@Autowired 
	UserRegistrationDAO userRegistrationDAO;
 
	@Before("execution(public * com.dbs.redtrack.controller.*Controller.*(..)) && @annotation(userCheck)")
	public void userCheck(JoinPoint joinPoint, UserCheck userCheck) {
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		logger.info("AppServiceAdvice :: userCheck :: start: service(" + userCheck.getService() +")");

		String userOTPID = null;
		boolean  isValidUser = false;
		
		Object[] signatureArgs = joinPoint.getArgs();
		   for (Object signatureArg: signatureArgs) {
		      logger.info("Arg: " + signatureArg);
		      if(signatureArg instanceof CreateIncidentRequestDTO) {
		    	  //logger.info(((CreateIncidentRequestDTO) signatureArg).getIncidenttitle());
		    	  userOTPID = ((CreateIncidentRequestDTO) signatureArg).getUserotpid();
		    	  break;
		      }
		   } 
		   
		   
//			logger.info("isActiveUser: "+ userCheck.isActiveUser());
//			logger.info("isManageRole: "+ userCheck.isManageRole());
		if (null != userOTPID)
			isValidUser = findUserByID(userOTPID);
		logger.info("AppServiceAdvice : userCheck : isValidUser(" + isValidUser+")");	
	        
		logger.info("AppServiceAdvice :: userCheck :: start");
	}
	
	
	private boolean findUserByID(String userID) throws RedTrackProcessingException {
		
		logger.info("AppServiceAdvice : findUserByID : start");
		boolean isValid = false;
		User user  = userRegistrationDAO.findUserByID(userID);
		if (null != user) { 
			if (user.getStatus().equalsIgnoreCase(BusinessConstants.STATUS_ACTIVE)){
				logger.info("AppServiceAdvice :  user("+user+")" );
			}
		}
		 
		if(user != null) {
			if(user.getRole() != 1) {				
				throw new RedTrackProcessingException(BusinessConstants.ERROR_00004, BusinessConstants.USER_ROLE_INVALID);
			}
			if(!user.getStatus().equalsIgnoreCase(BusinessConstants.STATUS_ACTIVE)){				
				throw new RedTrackProcessingException(BusinessConstants.ERROR_00004, BusinessConstants.USER_NOT_REGISTERED);
			} 
		} else {			
			throw new RedTrackProcessingException(BusinessConstants.ERROR_00004, BusinessConstants.USER_ROLE_INVALID);
		}
		isValid = true;
		logger.info("AppServiceAdvice : findUserByID : end" );
		return isValid;
	}




//	@Before("execution(public * com.dbs.omni.web.controller.*Controller.*(..)) && @annotation(versionCheck) && args(bean)")
//	public void checkAppVersion(JoinPoint joinPoint, Object bean, VersionCheck versionCheck) {
//	@SuppressWarnings("unchecked")
//	private void checkUserServiceStatus(String roleId, String serviceName, boolean isUserSessionExist,String userId ,HeaderDTO headerDTO) {
 //	private void auditService(String eventType,HeaderDTO headerDTO,String failureReason){

	 
//	@Before("execution(public * com.dbs.redtrack.controller.*Controller.*(..)) && @annotation(userCheck)")
//	public void userCheck(JoinPoint joinPoint, UserCheck userCheck) {
//		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>userCheck<<<<<<<<<<<<<<<<<<<<");
//		String serviceName = userCheck.getService();
//		logger.info("serviceName: "+ serviceName);
//		logger.info("isActiveUser: "+ userCheck.isActiveUser());
//		logger.info("isManageRole: "+ userCheck.isManageRole());
//		logger.info("joinPoint.getSignature: "+ joinPoint.getSignature().getName());
//		   Object[] signatureArgs = joinPoint.getArgs();
//		   for (Object signatureArg: signatureArgs) {
//
//		      logger.info("Arg: " + signatureArg);
//		      if(signatureArg instanceof CreateIncidentRequestDTO) {
//		    	  logger.info(((CreateIncidentRequestDTO) signatureArg).getIncidenttitle());
//		      }
//		   }
//		   
//		   logger.info("********************************************");
//		   final Signature signature = joinPoint.getStaticPart().getSignature();
//		    if (signature instanceof MethodSignature) {
//		        final MethodSignature ms = (MethodSignature) signature;
//		        String[] params = ms.getParameterNames();
//		        for (String param : params) {
//		            System.out.println("Param>>"+ param);
//		            // here how do i get parameter value using param ?
//		        }
//		    }
//		    logger.info("********************************************");
//		    
//		    System.out.println(joinPoint);
//	        Object[] args = joinPoint.getArgs();
//	        MethodSignature methodSignature = (MethodSignature) joinPoint.getStaticPart().getSignature();
//	        Method method = methodSignature.getMethod();
//	        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
//	        assert args.length == parameterAnnotations.length;
//	        for (int argIndex = 0; argIndex < args.length; argIndex++) {
//	            for (Annotation annotation : parameterAnnotations[argIndex]) {
//	                if (!(annotation instanceof RequestParam))
//	                    continue;
//	                RequestParam requestParam = (RequestParam) annotation;
//	                if (! "accessToken".equals(requestParam.value()))
//	                    continue;
//	                logger.info(" requestParam.value() " + requestParam.value() + " = " + args[argIndex]);
//	            }
//	        }
//	        
//		   logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>userCheck<<<<<<<<<<<<<<<");
//	}

 
		
}
