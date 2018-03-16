package com.dbs.redtrack.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dbs.redtrack.base.dto.ResponseDTO;
import com.dbs.redtrack.business.anotation.AuditLog;
import com.dbs.redtrack.business.service.UserRegistrationService;
import com.dbs.redtrack.constants.BusinessConstants;
import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.request.dto.GenerateOTPRequestDTO;
import com.dbs.redtrack.request.dto.RegisterUserRequestDTO;
import com.dbs.redtrack.request.dto.UpdateUserRequestDTO;
import com.dbs.redtrack.request.dto.ValidateOTPRequestDTO;
import com.dbs.redtrack.response.dto.GenerateOTPResponseDTO;
import com.dbs.redtrack.response.dto.GetUserRoleResponseDTO;
import com.dbs.redtrack.response.dto.RegisterUserResponseDTO;
import com.dbs.redtrack.response.dto.UpdateUserResponseDTO;
import com.dbs.redtrack.response.dto.ValidateOTPResponseDTO;
import com.dbs.redtrack.util.property.AppConfig;
import com.dbs.redtrack.web.utilities.RequestValidator;
import com.twilio.sdk.TwilioRestException;


@Controller
public class RegistrationController { 
	
	private static final Logger logger = Logger.getLogger(RegistrationController.class);
	 
	@Autowired
	UserRegistrationService userRegistrationService;

	@Autowired 
	AppConfig appconfig;
	
	  @Autowired
	  ApplicationContext applicationContext;

	
	/**
	 * Created by: Raymond
	 * Method name: registerUser()
	 * @throws TwilioRestException 
	 * 
	 */
	@RequestMapping(value = BusinessConstants.GENERATE_OTP, method = RequestMethod.POST)
	public @ResponseBody GenerateOTPResponseDTO generateOTP(@RequestParam("usermobiledeviceid") String usermobiledeviceid,
															@RequestParam("usermobilenumber") String usermobilenumber,
															@RequestParam("usercountrycode") String usercountrycode,
															@RequestParam("usermobileplatform") String usermobileplatform ) 
						throws RedTrackProcessingException, TwilioRestException {
			
		logger.info("start RegistrationController :: generateOTP :: start"); 		
		GenerateOTPRequestDTO request = new GenerateOTPRequestDTO(usermobiledeviceid, usermobilenumber, usercountrycode, usermobileplatform);
			 
		if(!RequestValidator.validateGenerateOTPRequest(request)) {
  
			throw new RedTrackProcessingException(BusinessConstants.ERROR_00001, BusinessConstants.INVALID_REQUEST); 	
		}
 
		GenerateOTPResponseDTO generateOTPResponseDTO = userRegistrationService.generateOTP(request); 		 
		
		logger.info("start RegistrationController :: generateOTP :: end");
		return generateOTPResponseDTO;		 
	}
	

	/**
	 * Created by: Raymond
	 * Method name: validateOtp()
	 * 
	 */
	@RequestMapping(value = BusinessConstants.VALIDATE_OTP, method = RequestMethod.POST)
//	public @ResponseBody ValidateOTPResponseDTO validateOtp(@RequestBody ValidateOTPRequestDTO request) throws RedTrackProcessingException{
	public @ResponseBody ValidateOTPResponseDTO validateOtp(@RequestParam("userenteredotp") String userenteredotp,
															@RequestParam("userotpid") String userotpid,
															@RequestParam("usermobilenumber") String usermobilenumber,
															@RequestParam("userdevictokenstr") String userdevictokenstr)
			throws RedTrackProcessingException {
		logger.info("start RegistrationController :: validateOtp :: start"); 
		//ValidateOTPRequestDTO request = new ValidateOTPRequestDTO(userenteredotp, userotpid, usermobilenumber);
		ValidateOTPRequestDTO request = new ValidateOTPRequestDTO(userenteredotp, userotpid, usermobilenumber, userdevictokenstr);
		ResponseDTO<ValidateOTPResponseDTO> responsedto = new ResponseDTO<ValidateOTPResponseDTO>();
		
		if(!RequestValidator.validateOTP(request)) {
			throw new RedTrackProcessingException(BusinessConstants.ERROR_00001, BusinessConstants.INVALID_REQUEST); 	
		}
 
		ValidateOTPResponseDTO validateOTPResponseDTO = userRegistrationService.validateOTP(request);
 		responsedto.setBody(validateOTPResponseDTO);		
		logger.info("start RegistrationController :: validateOtp :: end");
		
		return validateOTPResponseDTO;	 
	}
	
	

	/**
	 * Created by: Raymond
	 * Method name: registerUser()
	 * @throws TwilioRestException 
	 * 
	 */

	
	@RequestMapping(value = BusinessConstants.REGISTER_USER, method = RequestMethod.POST)
	@AuditLog(getService = BusinessConstants.REGISTER_USER)
	public @ResponseBody RegisterUserResponseDTO registerUser(@RequestParam("userotpid") String userotpid,
																  @RequestParam("usernickname") String usernickname,
																  @RequestParam("userfullname") String userfullname,
																  @RequestParam("userdevictokenstr") String userdevictokenstr
			) throws RedTrackProcessingException, TwilioRestException{
		logger.info("start RegistrationController :: registerUser :: start"); 
		RegisterUserRequestDTO registerUserRequestDTO = new RegisterUserRequestDTO(userotpid, usernickname, userfullname, userdevictokenstr);

		if( !RequestValidator.validateRegisterUser(registerUserRequestDTO)) {
			throw new RedTrackProcessingException(BusinessConstants.ERROR_00001, BusinessConstants.INVALID_REQUEST); 	
		}
 
		RegisterUserResponseDTO registerUserResponseDTO = userRegistrationService.registerTempUser(registerUserRequestDTO);
		
		logger.info("start RegistrationController :: registerUser :: end");
		return registerUserResponseDTO;		 
	}	
	


	/**
	 * Created by: Raymond
	 * Method name: updateUser()
	 * 
	 */
	@RequestMapping(value = BusinessConstants.UPDATE_USER, method = RequestMethod.POST)
	public @ResponseBody UpdateUserResponseDTO updateUser(@RequestParam("userotpid") String userotpid,
			  											@RequestParam("usernickname") String usernickname
						) throws RedTrackProcessingException{
		logger.info("start RegistrationController :: validateOtp :: start"); 
		UpdateUserRequestDTO updateUserRequestDTO = new UpdateUserRequestDTO(userotpid, usernickname);
		
		if(!RequestValidator.validateUpdateUser(updateUserRequestDTO)) {
			throw new RedTrackProcessingException(BusinessConstants.ERROR_00001, BusinessConstants.INVALID_REQUEST); 	
		}
 
		UpdateUserResponseDTO updateUserResponseDTO = userRegistrationService.updateUser(updateUserRequestDTO);
 
		
		logger.info("start RegistrationController :: validateOtp :: end");
		return updateUserResponseDTO; 	 
	}
	
	
	/**
	 * Created by: Raymond
	 * Method name: getUserRole()
	 * 
	 */
	@RequestMapping(value = BusinessConstants.GET_USER_ROLE, method = RequestMethod.GET)
	public @ResponseBody GetUserRoleResponseDTO getUserRole(@RequestParam("userotpid") String userotpid
						) throws RedTrackProcessingException{
		logger.info("start RegistrationController :: getUserRole :: start"); 
		
		if(!RequestValidator.validateGetUserRole(userotpid)) {
			throw new RedTrackProcessingException(BusinessConstants.ERROR_00005, BusinessConstants.INVALID_REQUEST); 	
		}
 
		GetUserRoleResponseDTO userRoleResponseDTO = userRegistrationService.getUserRole(userotpid);
		
		logger.info("start RegistrationController :: getUserRole :: end");
		return userRoleResponseDTO; 	 
	}
	
	 
	
	//not used
	@RequestMapping(value = "/findUser", method = RequestMethod.POST)
	public @ResponseBody UpdateUserResponseDTO findUser(@RequestBody UpdateUserRequestDTO request) throws RedTrackProcessingException{
		logger.info("start RegistrationController :: findUser :: start"); 
 		
		if(!RequestValidator.validateUpdateUser(request)) {
			throw new RedTrackProcessingException(BusinessConstants.ERROR_00001, BusinessConstants.INVALID_REQUEST); 	
		}
 
		UpdateUserResponseDTO updateUserResponseDTO = userRegistrationService.findTempUserByID(request);
 
		
		logger.info("start RegistrationController :: findUser :: end");
		return updateUserResponseDTO; 	 
	}
	 
	
	
//	/**
//	 * Created by: Raymond
//	 * Method name: registerUser()
//	 * @throws TwilioRestException 
//	 * 
//	 */
//	@RequestMapping(value = BusinessConstants.GENERATE_OTP, method = RequestMethod.POST)
//	//public @ResponseBody GenerateOTPResponseDTO generateOTP(@RequestBody GenerateOTPRequestDTO request) throws RedTrackProcessingException, TwilioRestException{
//	public @ResponseBody GenerateOTPResponseDTO generateOTP(@RequestParam("usermobiledeviceid") String usermobiledeviceid,
//															@RequestParam("usermobilenumber") String usermobilenumber,
//															@RequestParam("usercountrycode") String usercountrycode,
//															@RequestParam("usermobileplatform") String usermobileplatform ) 
//						throws RedTrackProcessingException, TwilioRestException {
//			
//		logger.info("start RegistrationController :: generateOTP :: start"); 		
//		GenerateOTPRequestDTO request = new GenerateOTPRequestDTO(usermobiledeviceid, usermobilenumber, usercountrycode, usermobileplatform);
//			 
//		if(!RequestValidator.validateGenerateOTPRequest(request)) {
////			logger.info(">>>>>>>>>>>>>>>>>>>>>>before call RedTrackCache");
////			RedTrackCache redtrackCache = applicationContext.getBean(RedTrackCache.class);
////			logger.info(">>>>>>>>>>>>>>>>>>>>>>after call RedTrackCache");
////			if (null != redtrackCache) {
////				logger.info(">>>>>>>>>>>>>>>>>>>>>>not null call RedTrackCache");
////				String errorMessage = redtrackCache.getErrorMessageDescription("WHITE_LISTING_SWITCH");
////				logger.info(">>>>>>>>>>>>>>>>>>>>>>errorMessage>>>>>>>>>>>>>"+ errorMessage+"<<<<<<<<<<<<<<<<<<<<<<<<<<");
////			}
//				
//			throw new RedTrackProcessingException(BusinessConstants.ERROR_00001, BusinessConstants.INVALID_REQUEST); 	
//		}
// 
//		GenerateOTPResponseDTO generateOTPResponseDTO = userRegistrationService.generateOTP(request); 		 
//		
//		logger.info("start RegistrationController :: generateOTP :: end");
//		return generateOTPResponseDTO;		 
//	}
}
