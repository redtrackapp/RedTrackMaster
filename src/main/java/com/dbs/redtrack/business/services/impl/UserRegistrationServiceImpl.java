/**
 * 
 */
package com.dbs.redtrack.business.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.redtrack.business.service.UserRegistrationService;
import com.dbs.redtrack.constants.BusinessConstants;
import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.integration.dao.UserRegistrationDAO;
import com.dbs.redtrack.jpa.entity.User;
import com.dbs.redtrack.jpa.entity.UserTemp;
import com.dbs.redtrack.jpa.entity.WhiteList;
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
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {
 
	private static final Logger logger = Logger.getLogger(UserRegistrationServiceImpl.class);

	@Autowired
	UserRegistrationDAO userRegistrationDAO;
	
	@Autowired
	AppConfig appconfig;
	
	
	
	/**
	 * Created by: Raymond
	 * Method name: registerTempUser()
	 * 
	 */
	@Override
	public GenerateOTPResponseDTO generateOTP(GenerateOTPRequestDTO generateOTPRequestDTO) throws RedTrackProcessingException, TwilioRestException {
		logger.info("UserRegistrationServiceImpl: generateOTP :start "+ generateOTPRequestDTO.toString());
		
		GenerateOTPResponseDTO generateOTPResponseDTO = new GenerateOTPResponseDTO();
		UserTemp userTemp = new UserTemp();
		Date dte = new Date();
		String iWhitListingSwitch = appconfig.getProperty("WHITE_LISTING_SWITCH");
		boolean isCheckWhitListingPassed = true;
		String strOTP = "000000";
		String strMobileNumber = "+" +generateOTPRequestDTO.getUsercountrycode() + generateOTPRequestDTO.getUsermobilenumber(); 
		String userRole = "1";
		
		if (iWhitListingSwitch.equals(BusinessConstants.WHITE_LISTING_ACTIVE)) {
			//isCheckWhitListing = userRegistrationDAO.checkUserFromWhiteList(strMobileNumber);
			WhiteList whiteList = userRegistrationDAO.getUserFromWhiteList(strMobileNumber);
			
			if(null == whiteList)  {
				isCheckWhitListingPassed = false;				
			} else {
				userRole = String.valueOf(whiteList.getRole());
			}
			
			logger.info("generateOTP : WHITE_LISTING_ACTIVE("+iWhitListingSwitch+") :: "
					+ "strMobileNumber("+ strMobileNumber+ ") isCheckWhitListingPassed(" +isCheckWhitListingPassed +")" );
		}
		
		if (isCheckWhitListingPassed) {
			long otpID = 0L; //milliSeconds
			
			//check for previous record //
			//todo: changed here and DAOService markUserAsActive() line 202 and 203
			//UserTemp oldUserTemp = userRegistrationDAO.findTempUserByPhoneNumber(generateOTPRequestDTO.getUsermobilenumber(), generateOTPRequestDTO.getUsercountrycode()); 
			User oldUser = userRegistrationDAO.findUserByPhoneNumber(generateOTPRequestDTO.getUsermobilenumber(), generateOTPRequestDTO.getUsercountrycode());
			
			if (null != oldUser) {
				otpID = oldUser.getId();
				if (null != oldUser.getStatus() && !oldUser.getStatus().equalsIgnoreCase(BusinessConstants.STATUS_ACTIVE)) {
					throw new RedTrackProcessingException(BusinessConstants.ERROR_00004, BusinessConstants.PHONE_NUMBER_NOT_ACTIVE); 	
				}
			} else {
				otpID = dte.getTime();
			}
			
			userTemp.setId(otpID);
			userTemp.setStatus(BusinessConstants.STATUS_PROCESSING);
			userTemp.setCountryCode(generateOTPRequestDTO.getUsercountrycode());
			userTemp.setPhoneNumber(generateOTPRequestDTO.getUsermobilenumber());			
			userTemp.setDevicePlatform(Integer.parseInt(generateOTPRequestDTO.getUsermobileplatform()));
			userTemp.setDeviceID(generateOTPRequestDTO.getUsermobiledeviceid());			
			userTemp.setDateCreated(dte);			
			TimeZone.setDefault(TimeZone.getTimeZone(BusinessConstants.TIME_ZONE_ASIA));
			java.util.Date date = new Date(); 
			userTemp.setValidityStartDate(date);
			date = new Date(System.currentTimeMillis() + BusinessConstants.TWO_MINUTES);
			userTemp.setValidtityEndDate((new java.sql.Timestamp(date.getTime())));
			userTemp.setRole(Integer.parseInt(userRole));
			
			//strOTP = "123456";//generateOTP();
			strOTP = generateOTP();
			userTemp.setOtp(strOTP);		
			String userMobile = "+" + generateOTPRequestDTO.getUsercountrycode() + generateOTPRequestDTO.getUsermobilenumber();
			logger.info("generateOTP : sendOTP : userMobile, OTP("+userMobile+", "+strOTP+ ")");
			//comment following line to switch off
			sendOTP(userMobile, strOTP, "");
			
			
			userRegistrationDAO.registerTempUser(userTemp); 
			generateOTPResponseDTO.setInsertedotpid(String.valueOf(otpID));
			generateOTPResponseDTO.setIsOtpSent(BusinessConstants.IS_OTP_SENT);
			generateOTPResponseDTO.setIsoldotpclaimed(BusinessConstants.IS_OTP_CLAIMED);
			generateOTPResponseDTO.setResponsecode(BusinessConstants.RESPONSE_CODE_SUCCESS);
			generateOTPResponseDTO.setRoleis(userRole);
			
			if (null != oldUser) {
				generateOTPResponseDTO.setUseralreadyexists(BusinessConstants.USER_EXIST);
				generateOTPResponseDTO.setUserfullname(oldUser.getFullName());
			} else {
				generateOTPResponseDTO.setUseralreadyexists(BusinessConstants.NEW_USER);
				generateOTPResponseDTO.setUserfullname("");
			}	
			
			generateOTPResponseDTO.setUseravatarlocation("");
			generateOTPResponseDTO.setValidityduration(BusinessConstants.OTP_EXPIRY);			
			//registerUserResponseDTO.setMessage(BusinessConstants.REGISTERED_SUUCCESSFULLY);
		} else {
			//generateOTPResponseDTO.setResponsecode("111111");
			throw new RedTrackProcessingException(BusinessConstants.ERROR_00001, BusinessConstants.PHONE_NUMBER_NOT_IN_WHITELIST); 	
			//registerUserResponseDTO.setMessage(BusinessConstants.PHONE_NUMBER_NOT_IN_WHITELIST);
		}

		logger.info("UserRegistrationServiceImpl: generateOTP : end");
		return generateOTPResponseDTO;
	}
	
	
	
	/**
	 * Created by: Raymond
	 * Method name: validateOTP()
	 * 
	 */
	@Override
	public ValidateOTPResponseDTO validateOTP(ValidateOTPRequestDTO validateOTPRequestDTO) throws RedTrackProcessingException  {
		logger.info("UserRegistrationServiceImpl : validateOTP : start");
		ValidateOTPResponseDTO validateOTPResponseDTO = new ValidateOTPResponseDTO(); 		
		//UserTemp userTemp = userRegistrationDAO.findTempUserByPhoneNumber(validateOTPRequestDTO.getUsermobilenumber());
		UserTemp userTemp = userRegistrationDAO.findUserTempByID(validateOTPRequestDTO.getUserotpid());
		
		if (null != userTemp){
			logger.info("validateOTP:: validateOTP Sent("+userTemp.getOtp()+") Received ("+validateOTPRequestDTO.getUserenteredotp()+")");
			if (userTemp.getOtp().equals(validateOTPRequestDTO.getUserenteredotp())) {
				
				TimeZone.setDefault(TimeZone.getTimeZone("Asia/Singapore"));
				java.util.Date date = new Date(); 
				logger.info("validateOTP:: validateOTP ("+date+") before ("+userTemp.getValidtityEndDate()+")");
				
				if (date.before(userTemp.getValidtityEndDate())) {
					//new requirement from android team to update devicetoken string from here
					//if (null != validateOTPRequestDTO.getUserdevictokenstr() && !("").equals(validateOTPRequestDTO.getUserdevictokenstr())){
					if (StringUtils.isNotEmpty(validateOTPRequestDTO.getUserdevictokenstr())) {
						logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>update toekn string"+ validateOTPRequestDTO.getUserdevictokenstr());
						userRegistrationDAO.markUserAsActive(validateOTPRequestDTO.getUserotpid(), validateOTPRequestDTO.getUserdevictokenstr());
					} else {
						logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>token string not updated");
						userRegistrationDAO.markUserAsActive(validateOTPRequestDTO.getUserotpid());
					}
					//registerUserResponseDTO.setMessage(BusinessConstants.OTP_VERIFIED);
					logger.info("validateOTP:: validateOTP:: Valid OTP");
					validateOTPResponseDTO.setResponsecode(BusinessConstants.RESPONSE_CODE_SUCCESS);
					validateOTPResponseDTO.setRoleis(String.valueOf(userTemp.getRole()));
				} else {
					logger.info("validateOTP:: validateOTP:: OTP Expired");
					validateOTPResponseDTO.setResponsecode(BusinessConstants.EXPIRED_OTP_CODE);
					validateOTPResponseDTO.setResponsecode(BusinessConstants.ERROR_00001);
					//registerUserResponseDTO.setMessage(BusinessConstants.OTP_EXPIRED);
				}
			} else {
				logger.info("validateOTP:: validateOTP:: Invalid OTP");
				validateOTPResponseDTO.setResponsecode(BusinessConstants.INVALID_OTP_CODE);
				validateOTPResponseDTO.setResponsecode(BusinessConstants.ERROR_00002);
				//registerUserResponseDTO.setMessage(BusinessConstants.INVALID_OTP);
			}
		} else {
			logger.info("validateOTP:: validateOTP:: Invalid Mobile number");
			validateOTPResponseDTO.setResponsecode(BusinessConstants.INVALID_MOBILENUMBER_CODE);
			validateOTPResponseDTO.setResponsecode("00003");
			//registerUserResponseDTO.setMessage(BusinessConstants.INVALID_PHONE_NUMBER);
		}
		logger.info("UserRegistrationServiceImpl : validateOTP : end");
		return validateOTPResponseDTO;
	}
	
	
	/**
	 * Created by: Raymond
	 * Method name: updateUser()
	 * 
	 */
	@Override
	public UpdateUserResponseDTO updateUser(UpdateUserRequestDTO updateUserRequestDTO) throws RedTrackProcessingException {
		logger.info("UserRegistrationServiceImpl : updateUser : start");
		UpdateUserResponseDTO updateUserResponseDTO = new UpdateUserResponseDTO();
		
		if(!isUserActive(updateUserRequestDTO.getUserotpid())) {
			throw new RedTrackProcessingException(BusinessConstants.ERROR_00004, BusinessConstants.PHONE_NUMBER_NOT_ACTIVE);
		}
			
		User user = userRegistrationDAO.updateUserbyID(updateUserRequestDTO.getUserotpid(), updateUserRequestDTO.getUsernickname());
		
		if (null != user){ 
			updateUserResponseDTO.setResponsecode(BusinessConstants.RESPONSE_CODE_SUCCESS);
			updateUserResponseDTO.setMessage(BusinessConstants.USER_UPDATE_SUCCESSFULL);
			logger.info("UserRegistrationServiceImpl:: updateUser:: "+ BusinessConstants.USER_UPDATE_SUCCESSFULL);
		} else {			
			updateUserResponseDTO.setResponsecode(BusinessConstants.ERROR_00001);
			updateUserResponseDTO.setMessage(BusinessConstants.ERROR_UPDATING_USER);
			logger.info("UserRegistrationServiceImpl:: updateUser:: "+ BusinessConstants.ERROR_UPDATING_USER);
		}
		 
		logger.info("UserRegistrationServiceImpl : updateUser : end");
		return updateUserResponseDTO;
	}
	

	/**
	 * Created by: Raymond
	 * Method name: registerTempUser()
	 * 
	 */
	@Override
	public RegisterUserResponseDTO registerTempUser(RegisterUserRequestDTO registerUserRequestDTO)
			throws RedTrackProcessingException {
		RegisterUserResponseDTO registerUserResponseDTO = new RegisterUserResponseDTO();
		
		if(!isUserActive(registerUserRequestDTO.getUserotpid())) {
			throw new RedTrackProcessingException(BusinessConstants.ERROR_00004, BusinessConstants.PHONE_NUMBER_NOT_ACTIVE);
		}
		
		User user  = userRegistrationDAO.updateUser(registerUserRequestDTO.getUserotpid(), registerUserRequestDTO.getUsernickname(),
															registerUserRequestDTO.getUserfullname(), registerUserRequestDTO.getUserdevictokenstr()); 
		if (null != user) {
			registerUserResponseDTO.setResponsecode(BusinessConstants.RESPONSE_CODE_SUCCESS); 
			registerUserResponseDTO.setMessage(BusinessConstants.USER_UPDATE_SUCCESSFULL);
			logger.info("UserRegistrationServiceImpl:: registerTempUser:: "+ BusinessConstants.USER_UPDATE_SUCCESSFULL);
		} else {
			registerUserResponseDTO.setResponsecode(BusinessConstants.ERROR_00001); 
			registerUserResponseDTO.setMessage(BusinessConstants.ERROR_UPDATING_USER);
			logger.info("UserRegistrationServiceImpl:: registerTempUser:: "+ BusinessConstants.ERROR_UPDATING_USER);
		}
		return registerUserResponseDTO;
	}	 
	
	
	/**
	 * Created by: Raymond
	 * Method name: findActiveUserByID()
	 * 
	 */
	@Override
	public UpdateUserResponseDTO findActiveUserByID(UpdateUserRequestDTO updateUserRequestDTO) throws RedTrackProcessingException {
		logger.info("UserRegistrationServiceImpl : findActiveUserByID : start");
		UpdateUserResponseDTO updateUserResponseDTO = new UpdateUserResponseDTO();		

		User user  = userRegistrationDAO.findUserByID(updateUserRequestDTO.getUserotpid());
		if (null != user){ 
			updateUserResponseDTO.setResponsecode(BusinessConstants.RESPONSE_CODE_SUCCESS);
			updateUserResponseDTO.setMessage(BusinessConstants.ACTIVE_USER_FOUND);
			logger.info("UserRegistrationServiceImpl:: findActiveUserByID:: "+ BusinessConstants.ACTIVE_USER_FOUND);			
		} else {
			updateUserResponseDTO.setResponsecode(BusinessConstants.ERROR_00001);
			updateUserResponseDTO.setMessage(BusinessConstants.USER_NOT_REGISTERED);
			logger.info("UserRegistrationServiceImpl:: findActiveUserByID:: "+ BusinessConstants.USER_NOT_REGISTERED);					
		}
		logger.info("UserRegistrationServiceImpl : findActiveUserByID : end");
		return updateUserResponseDTO;
	}
	
	
	
	
	/**
	 * Created by: Raymond
	 * Method name: findActiveUserByID()
	 * 
	 */
	@Override
	public GetUserRoleResponseDTO getUserRole(String strUserOTPID) throws RedTrackProcessingException {
		logger.info("UserRegistrationServiceImpl : getUserRole : start :: strUserOTPID("+ strUserOTPID +")");
		String strUserRole = "";		
		GetUserRoleResponseDTO userRoleResponseDTO = null;

		User user  = userRegistrationDAO.findUserByID(strUserOTPID);
		if (null != user) {		
			userRoleResponseDTO = new GetUserRoleResponseDTO(String.valueOf(user.getRole()));
			logger.info("UserRegistrationServiceImpl:: getUserRole:: "+ userRoleResponseDTO.toString());			
		} else {
			logger.info("UserRegistrationServiceImpl:: getUserRole:: "+ BusinessConstants.USER_NOT_REGISTERED);
			throw new RedTrackProcessingException(BusinessConstants.ERROR_00005, BusinessConstants.USER_NOT_REGISTERED);
		}
		
		logger.info("UserRegistrationServiceImpl : getUserRole : end");
		return userRoleResponseDTO;
	}
	

	/**
	 * Created by: Raymond
	 * Method name: isUserActive()
	 * 
	 */
	
	private boolean isUserActive(String userID) throws RedTrackProcessingException {
		
		logger.info("UserRegistrationServiceImpl : isUserActive : start :: userID("+ userID +")");
		boolean isUSerActive = false;     
		User user  = userRegistrationDAO.findUserByID(userID);
		if (null != user) { 
			if (user.getStatus().equalsIgnoreCase(BusinessConstants.STATUS_ACTIVE)){
				isUSerActive = true;
			}
		}
		logger.info("UserRegistrationServiceImpl : isUserActive : end isUSerActive("+isUSerActive+")" );
		return isUSerActive;
	}
	
	/**
	 * Created by: Raymond
	 * Method name: deleteUserTemp()
	 * 
	 */
	@Override
	public int deleteUserTemp() throws RedTrackProcessingException {
		logger.info("UserRegistrationServiceImpl : deleteUserTemp : start");
		int iResult  = userRegistrationDAO.deleteUserTemp();
		logger.info("UserRegistrationServiceImpl : deleteUserTemp : end");
		return iResult;
	}
	 
 
	/**
	 * Created by: Raymond
	 * Method name: generateOTP()
	 * 
	 */ 
	@SuppressWarnings("unused")
	private String generateOTP() {

		logger.info("UserRegistrationServiceImpl : generateOTP : start");
		Random randomNumObj=null;
		String strOtp = "000000";

		randomNumObj=new Random();
		strOtp = (randomNumObj.nextInt(999999-100000) + 100000) + "";
		logger.info("Generated OTP value: ("+ strOtp+")" );
		logger.info("UserRegistrationServiceImpl : generateOTP : end");
		return strOtp;
	}
	
	
	/**
	 * Created by: Raymond
	 * Method name: sendMessageSMS()
	 * 
	 */ 
	@SuppressWarnings("unused")
	private void sendMessageSMS(String strTo, String strData)  throws TwilioRestException {
//		public static final  String ACCOUNT_SID = "ACa9620ecd9511404353b4da68b6cbd651"; 
//		public static final  String AUTH_TOKEN = "8e5eab3caa26edb2298bfeb470b5b571";
//		public static final String TWILIO_FROM = "+12013456254";
		logger.info("UserRegistrationServiceImpl : sendMessageSMS : end");
		TwilioRestClient client = new TwilioRestClient(BusinessConstants.ACCOUNT_SID, BusinessConstants.AUTH_TOKEN); 
		// Build the parameters 
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("To", strTo)); 
		params.add(new BasicNameValuePair("From", BusinessConstants.TWILIO_FROM)); 
		params.add(new BasicNameValuePair("Body", strData)); 
		MessageFactory messageFactory = client.getAccount().getMessageFactory(); 
		//Message message=null;
 
		Message message = messageFactory.create(params);

		logger.info("UserRegistrationServiceImpl : sendMessageSMS : end");
		  
	}		
	
	
	/**
	 * Created by: Raymond
	 * Method name: sendOTP()
	 * 
	 */ 
	@SuppressWarnings("unused")
	private void sendOTP(String strTo, String strOTP, String strFirstname ) throws TwilioRestException {

		logger.info("UserRegistrationServiceImpl : sendOTP : end");
		TwilioRestClient client = new TwilioRestClient(BusinessConstants.ACCOUNT_SID, BusinessConstants.AUTH_TOKEN); 
		// Build the parameters 
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("To", strTo)); 
		params.add(new BasicNameValuePair("From", BusinessConstants.TWILIO_FROM)); 
		params.add(new BasicNameValuePair("Body", "Hi " + strFirstname + ","
				+ "\nYour one-time passcode is "+ strOTP +". The passcode expires in 2 minutes.")); 
		MessageFactory messageFactory = client.getAccount().getMessageFactory(); 
		//Message message=null;
		Message message = messageFactory.create(params);

		logger.info("UserRegistrationServiceImpl : sendOTP : end");
	}



	/**
	 * Created by: Raymond
	 * Method name: findActiveUserByID()
	 * 
	 */
	@Override
	public UpdateUserResponseDTO findTempUserByID(UpdateUserRequestDTO updateUserRequestDTO) throws RedTrackProcessingException {
		logger.info("UserRegistrationServiceImpl : findTempUserByID : start");
		UpdateUserResponseDTO updateUserResponseDTO = new UpdateUserResponseDTO();		

		UserTemp oldUserTemp = userRegistrationDAO.findTempUserByPhoneNumber(updateUserRequestDTO.getUsermobilenumber(), updateUserRequestDTO.getCountryCode());
		logger.info("findTempUserByID:: oldUserTemp:: ("+ oldUserTemp+ ")");
		if (null != oldUserTemp){ 
			updateUserResponseDTO.setResponsecode("0000");
			updateUserResponseDTO.setMessage(BusinessConstants.ACTIVE_USER_FOUND);
			logger.info("validateOTP:: findTempUserByID:: "+ BusinessConstants.ACTIVE_USER_FOUND);			
		} else {
			updateUserResponseDTO.setResponsecode("0001");
			updateUserResponseDTO.setMessage(BusinessConstants.USER_NOT_REGISTERED);
			logger.info("validateOTP:: findTempUserByID:: "+ BusinessConstants.USER_NOT_REGISTERED);					
		}
		logger.info("UserRegistrationServiceImpl : findTempUserByID : end");
		return updateUserResponseDTO;
	}
//	
//	/**
//	 * Created by: Raymond
//	 * Method name: registerTempUser()
//	 * 
//	 */
//	
//	private RegisterUserResponseDTO registerTempUser1(GenerateOTPRequestDTO registerUserTempRequestDTO) throws RedTrackProcessingException {
//		logger.info("UserRegistrationServiceImpl:registerUser:start");
//		
//		RegisterUserResponseDTO registerUserResponseDTO = new RegisterUserResponseDTO();
//		UserTemp userTemp = new UserTemp();
//		Date dte = new Date();
//		String iWhitListingSwitch = appconfig.getProperty("WHITE_LISTING_SWITCH");
//		boolean isCheckWhitListing = false;
//
//		if (iWhitListingSwitch.equals(BusinessConstants.WHITE_LISTING_ACTIVE)) {
//			isCheckWhitListing= userRegistrationDAO.checkUserFromWhiteList(registerUserTempRequestDTO.getPhoneNumber());
//		}
//		
//		if (isCheckWhitListing) {
//			long milliSeconds = dte.getTime();
//			userTemp.setId(milliSeconds);
//			userTemp.setCountryCode(registerUserTempRequestDTO.getCountryCode());
////			userTemp.setFirstName(registerUserTempRequestDTO.getFirstName());
////			userTemp.setLastName(registerUserTempRequestDTO.getLastName());
//			userTemp.setFullName(registerUserTempRequestDTO.getUserfullname());
//			userTemp.setNickName(registerUserTempRequestDTO.getUsernickname());
//			userTemp.setPhoneNumber(registerUserTempRequestDTO.getPhoneNumber());
//			userTemp.setStatus(BusinessConstants.STATUS_PROCESSING);
//			//userTemp.setDeviceTokenString(registerUserTempRequestDTO.getDeviceTokenString());
//			userTemp.setDeviceID(registerUserTempRequestDTO.getDeviceID());
//			userTemp.setDevicePlatform(Integer.parseInt(registerUserTempRequestDTO.getDevicePlatform()));
//			userTemp.setDateCreated(dte);		 
// 
//			userRegistrationDAO.registerTempUser(userTemp);
//			registerUserResponseDTO.setMessage(BusinessConstants.REGISTERED_SUUCCESSFULLY);
//		} else {
//			registerUserResponseDTO.setMessage(BusinessConstants.PHONE_NUMBER_NOT_IN_WHITELIST);
//		}
//
//		logger.info("UserRegistrationServiceImpl:registerUser:end");
//		return registerUserResponseDTO;
//	}
//	
//	
//	
//	/**
//	 * Created by: Raymond
//	 * Method name: generateOTP()
//	 * 
//	 */
//	@Override
//	public RegisterUserResponseDTO generateOTP(GenerateOTPRequest generateOTPRequestDTO) throws RedTrackProcessingException, TwilioRestException {
//		
//		logger.info("UserRegistrationServiceImpl : generateOTP : start");
//		String strOTP = generateOTP();//"1234";
//		
////		UserTemp uTemp = userRegistrationDAO.findTempUserByPhoneNumber(generateOTPRequestDTO.getUsermobilenumber());		
////		sendOTP(uTemp.getPhoneNumber(), strOTP, uTemp.getFirstName());
//		
//		
//		RegisterUserResponseDTO registerUserResponseDTO = new RegisterUserResponseDTO();		
//		UserTemp userTemp = userRegistrationDAO.updateTempUser(generateOTPRequestDTO.getUsermobilenumber(), strOTP, generateOTPRequestDTO.getUsercountrycode());
//		
//		if (null != userTemp) {
//			registerUserResponseDTO.setMessage(BusinessConstants.GENERATED_OTP_SUCCESSFULLY);
//		} else {
//			registerUserResponseDTO.setMessage(BusinessConstants.UNABLE_TO_GENERATE_OTP);
//		}
//		
//		logger.info("UserRegistrationServiceImpl : generateOTP : end");
//		return registerUserResponseDTO;
//	}
}
