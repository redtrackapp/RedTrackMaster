/**
 * 
 */
package com.dbs.redtrack.web.utilities;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.dbs.redtrack.base.dto.RequestDTO;
import com.dbs.redtrack.dto.helper.ApplicationCountryHelper;
import com.dbs.redtrack.dto.helper.ApplicationHelper;
import com.dbs.redtrack.request.dto.ArchiveIncidentRequestDTO;
import com.dbs.redtrack.request.dto.CreateIncidentRequestDTO;
import com.dbs.redtrack.request.dto.EditIncidentRequestDTO;
import com.dbs.redtrack.request.dto.GenerateOTPRequestDTO;
import com.dbs.redtrack.request.dto.GetAllArchivedIncidentsRequestDTO;
import com.dbs.redtrack.request.dto.GetAllIncidentMessagesRequestDTO;
import com.dbs.redtrack.request.dto.GetAllIncidentsRequestDTO;
import com.dbs.redtrack.request.dto.RegisterUserRequestDTO;
import com.dbs.redtrack.request.dto.SaveChatRequestDTO;
import com.dbs.redtrack.request.dto.UpdateUserRequestDTO;
import com.dbs.redtrack.request.dto.ValidateOTPRequestDTO;
/****************************************************************************
*	Name    : RequestValidator
*	Author  : Raymond
*	Purpose : validations of request header and body
*   change Log:
*****************************************************************************/
@Component
public class RequestValidator {

	
	private static final Logger logger = Logger.getLogger(RequestValidator.class);


 
	@SuppressWarnings("rawtypes")
	public static boolean validateHeader(RequestDTO requestDTO){
		logger.info("Inside validateHeader");		
//        if(null == requestDTO.getHeader() || null == requestDTO.getHeader().getAppVersion() 
//        		||  PSConstants.EMPTY.equals(requestDTO.getHeader().getAppVersion().trim()) 
//        		|| !isDouble(requestDTO.getHeader().getAppVersion())
//        		|| null == requestDTO.getHeader().getAppName() ||  PSConstants.EMPTY.equals(requestDTO.getHeader().getAppName().trim())
//        				|| !requestDTO.getHeader().getAppName().matches(BusinessConstants.ALPHABET) 
//        		|| null == requestDTO.getHeader().getDeviceId() ||  PSConstants.EMPTY.equals(requestDTO.getHeader().getDeviceId().trim())
//        		||  null == requestDTO.getHeader().getLanguageId() ||  PSConstants.EMPTY.equals(requestDTO.getHeader().getLanguageId().trim())
//        		||  null == requestDTO.getHeader().getOsType() ||  PSConstants.EMPTY.equals(requestDTO.getHeader().getOsType().trim())
//        				|| !requestDTO.getHeader().getOsType().matches(BusinessConstants.ALPHABET) 
//        		||  null == requestDTO.getHeader().getScreenType() || PSConstants.EMPTY.equals(requestDTO.getHeader().getScreenType().trim())
//        		|| null == requestDTO.getHeader().getUiVersion() || PSConstants.EMPTY.equals(requestDTO.getHeader().getUiVersion().trim())
//        				|| !isDouble(requestDTO.getHeader().getUiVersion())
//        		|| null == requestDTO.getHeader().getDeviceModel() || PSConstants.EMPTY.equals(requestDTO.getHeader().getDeviceModel().trim())        		
//        		|| null == requestDTO.getHeader().getOsVersion() || PSConstants.EMPTY.equals(requestDTO.getHeader().getOsVersion().trim())){  
//        	logger.info("Header DTO" + requestDTO.getHeader()); 
//        	return false;
//		}
//        RequestHeaderPO requestHeaderPO =  (RequestHeaderPO)OmniRequestContext.get().get(Constants.OMNI_REQUEST_CONTEXT);
//        requestHeaderPO.setLanguageId(requestDTO.getHeader().getLanguageId()); 
		return true;	
	}
	
	
	/*
	 * validations for User Registration
	 * 
	 * 
	 * */ 
	public static boolean validateGenerateOTPRequest(GenerateOTPRequestDTO request){
 	logger.info("Inside validateGenerateOTPRequest");	
 	https://newredtrackapp.mybluemix.net/redtrack/generateOtp?usermobiledeviceid=1234567890&usermobilenumber=91082953&usercountrycode=65&usermobileplatform=1
     if(null == request
           		|| null == request.getUsercountrycode()
           		|| "".equals(request.getUsercountrycode().trim())
           		
           		|| null == request.getUsermobilenumber()
           		|| "".equals(request.getUsermobilenumber().trim())           		
           		|| (!isNumeric(request.getUsermobilenumber()))

           		|| null == request.getUsermobiledeviceid()
           		|| "".equals(request.getUsermobiledeviceid().trim())
           		
           		|| null == request.getUsermobileplatform()
           		|| "".equals(request.getUsermobileplatform().trim())
           		|| (!isStringInt(request.getUsermobileplatform()))
           		) {
     	return false;
		}
		return true;
	} 

	public static boolean isStringInt(String s) {
		boolean isValueInt = false;
	    try  {
	        if (Integer.parseInt(s) < 2)//0 or 1 only
	        	isValueInt = true;
	    } catch (NumberFormatException ex) {
	    	logger.info("isStringInt:"+ s);
	    	isValueInt = false;
	    }
	    logger.info("isStringInt:"+ s +":"+ isValueInt);
	    return isValueInt;
	}
	 
	public static boolean validateRegisterUser(RegisterUserRequestDTO request){
 	logger.info("Inside validateRegisterUser");	


     if(null == request
        		|| null == request.getUserotpid()
        		|| "".equals(request.getUserotpid().trim())
        		|| (!isNumeric(request.getUserotpid()))
        		
           		|| null == request.getUserdevictokenstr()
           		|| "".equals(request.getUserdevictokenstr().trim())

           		|| null == request.getUsernickname()
           		|| "".equals(request.getUsernickname().trim())
           		
           		|| null == request.getUserfullname()
           		|| "".equals(request.getUserfullname().trim())) {
     	return false;
		}
		return true;
	}
	 
	public static boolean validateOTP(ValidateOTPRequestDTO request){
 	logger.info("Inside validateOTPRequest");	 
     if(null == request
           		|| null == request.getUserotpid()
           		|| "".equals(request.getUserotpid().trim())

           		|| null == request.getUserenteredotp()
           		|| "".equals(request.getUserenteredotp().trim())
           		
           		|| null == request.getUsermobilenumber()
           		|| "".equals(request.getUsermobilenumber().trim()) ) {
     	return false;
		}
		return true;
	}	
	
	 
	public static boolean validateUpdateUser(UpdateUserRequestDTO request){
 	logger.info("Inside validateOTPRequest");	 
     if(null == request

           		|| null == request.getUserotpid()
           		|| "".equals(request.getUserotpid().trim())
           		|| (!isNumeric(request.getUserotpid()))
           		
           		|| null == request.getUsernickname()
           		|| "".equals(request.getUsernickname().trim()) ) {
     	return false;
		}
		return true;
	}	
	
	

	public static boolean validateGetAllIncidents(String userotpid){
	 	logger.info("Inside validateGetAllIncidents");	 
	     if(null == userotpid
	           		|| "".equals(userotpid.trim())
	           		|| (!isNumeric(userotpid)) ) {
	     	return false;
			}
			return true;
		}	
		

	public static boolean validateGetUserRole(String userotpid){
	 	logger.info("Inside validateGetAllIncidents");	 
	     if(null == userotpid
	           		|| "".equals(userotpid.trim())
	           		|| (!isNumeric(userotpid)) ) {
	     	return false;
			}
			return true;
		}	
		
	
	
	/*
	 * validations for Call History 
	 * 
	 * 
	 * */
	
	
	
	
	public boolean isInteger( String input )
	{
	   try
	   {
	      Integer.parseInt( input );
	      return true;
//	      if ("-2324.00".matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) {
//	    	    System.out.println("Is a number");
//	    	} else {
//	    	    System.out.println("Is not a number");
//	    	}
	   }
	   catch( Exception e)
	   {
	      return false;
	   }
	}
	
	
	
	private static boolean isNumeric(String input) {
	   try  {
	      Long.parseLong(input);
	      logger.info("isNumeric :true:"+ input );
	      return true;
	   }  catch( Exception e) {
		   logger.info("isNumeric :false:"+ input );
	      return false;
	   }
	}
	
	
	/*
	 * validations for Incidents 
	 * 
	 * 
	 * */
 
	public static boolean validateCreateIncidentRequest(CreateIncidentRequestDTO request){
 	logger.info("Inside validateCreateIncidentRequest");	
 
     if(null == request
    		 
//     		|| null == request.getIncidentid()
//     		|| "".equals(request.getIncidentid().trim())
     		
        		|| null == request.getIncidenttitle()
        		|| "".equals(request.getIncidenttitle().trim())
        		
           		|| null == request.getIncidentdesc()
           		|| "".equals(request.getIncidentdesc().trim())

//           		|| null == request.getIncidentresolution()
//           		|| "".equals(request.getIncidentresolution().trim())

           		|| null == request.getIncidentthreatseverity()
           		|| "".equals(request.getIncidentthreatseverity().trim())
           		
           		|| null == request.getIncidentcategory()
           		|| "".equals(request.getIncidentcategory().trim())
           		
           		|| null == request.getUserotpid()
           		|| "".equals(request.getUserotpid().trim())
           		|| (!isNumeric(request.getUserotpid()))
           		
//           		|| null == request.getIncidentconfnum()
//           		|| "".equals(request.getIncidentconfnum().trim())
//           		|| (!isNumeric(request.getIncidentconfnum()))
           		
//           		|| null == request.getIncidentparticipantcode()
//           		|| "".equals(request.getIncidentparticipantcode().trim())
           		
//           		|| null == request.getIncidentApps()
//           		|| 0 == request.getIncidentApps().size()
           		) {
    	 
    	 
    	 //todo: remove comments to swithc on
//    	 		 //to check if country and applications have a value
//		    	 for(ApplicationCountryHelper appCountryHelper: request.getIncidentApps()) {
//		    		 //check country code
//		    		 if ( (null == appCountryHelper.getCountryCode()) 
//		    				 || ("".equals(appCountryHelper.getCountryCode().trim())) ) {
//		    			 logger.info("validateCreateIncidentRequest getCountryCode null/empty");	
//		    			 return false;
//		    		 }
//		    		 //check application list should have atleast 1 record
//		    		 if ( (null==appCountryHelper.getApplicationList()) 
//		    				 || (0 == appCountryHelper.getApplicationList().size()) ) {
//		    			 logger.info("validateCreateIncidentRequest getApplicationList null/empty");
//		    			 return false;
//		    		 }
//		    		 
//		    		 for(ApplicationHelper appHelper: appCountryHelper.getApplicationList()) {
//		    			 if ( (null==appHelper.getApplicationID()) 
//		    					 || ("".equals(appHelper.getApplicationID().trim())) ) {
//		    				 logger.info("validateCreateIncidentRequest getApplicationID null/empty");
//		    				 return false;
//		    			 }
//		    			 
//		    		 }		    		 
//		    	 }
	    	 
	     		return false;
		}     
		return true; 
	}
	 
	public static boolean validateEditIncidentRequest(EditIncidentRequestDTO request){
 	logger.info("Inside validateEditIncidentRequest");	
 
     if(null == request
     		|| null == request.getIncidenttitle()
     		|| "".equals(request.getIncidenttitle().trim())
     		
        		|| null == request.getIncidentdesc()
        		|| "".equals(request.getIncidentdesc().trim())

//        		|| null == request.getIncidentresolution()
//        		|| "".equals(request.getIncidentresolution().trim())

        		|| null == request.getIncidentthreatseverity()
        		|| "".equals(request.getIncidentthreatseverity().trim())
        		
        		|| null == request.getIncidentcategory()
        		|| "".equals(request.getIncidentcategory().trim())
        		
        		|| null == request.getUserotpid()
        		|| "".equals(request.getUserotpid().trim())
        		|| "".equals(!isNumeric(request.getUserotpid()))
        		
        		|| null == request.getIncidentid()
        		|| "".equals(request.getIncidentid().trim())
        		//|| (!isNumeric(request.getIncidentid()))

//        		|| null == request.getIncidentconfnum()
//        		|| "".equals(request.getIncidentconfnum().trim())
//        		|| (!isNumeric(request.getIncidentconfnum()))
//        		
//        		|| null == request.getIncidentparticipantcode()
//        		|| "".equals(request.getIncidentparticipantcode().trim())
        		
           		|| null == request.getIncidentApps()
           		|| 0 == request.getIncidentApps().size()
        		
    		 ) {
    	 //todo: remove comments to swithc on    	 
//		    	 //to check if country and applications have a value
//		    	 for(ApplicationCountryHelper appCountryHelper: request.getIncidentApps()) {
//		    		 //check country code
//		    		 if ( (null == appCountryHelper.getCountryCode()) 
//		    				 || ("".equals(appCountryHelper.getCountryCode().trim())) ) {
//		    			 logger.info("validateEditIncidentRequest getCountryCode null/empty");	
//		    			 return false;
//		    		 }
//		    		 //check application list should have atleast 1 record
//		    		 if ( (null==appCountryHelper.getApplicationList()) 
//		    				 || (0 == appCountryHelper.getApplicationList().size()) ) {
//		    			 logger.info("validateEditIncidentRequest getApplicationList null/empty");
//		    			 return false;
//		    		 }
//		    		 
//		    		 for(ApplicationHelper appHelper: appCountryHelper.getApplicationList()) {
//		    			 if ( (null==appHelper.getApplicationID()) 
//		    					 || ("".equals(appHelper.getApplicationID().trim())) ) {
//		    				 logger.info("validateEditIncidentRequest getApplicationID null/empty");
//		    				 return false;
//		    			 }
//		    			 
//		    		 }		    		 
//		    	 } 
    	 
     			return false;
		}
		return true;
	}
	
	 
	public static boolean validateGetallincidentMessagesRequest(GetAllIncidentMessagesRequestDTO request){
 	logger.info("Inside validateGetallincidentMessagesRequest");	
 
     if(null == request
           		|| null == request.getUserotpid()
           		|| "".equals(request.getUserotpid().trim())
           		|| null == request.getIncidentid()
           		|| "".equals(request.getIncidentid().trim())) {
     	return false;
		}
		return true;
	}
	 
	public static boolean validateArchiveIncidentRequest(ArchiveIncidentRequestDTO request){
 	logger.info("Inside validateArchiveIncidentRequest");	
 
     if(null == request
           		|| null == request.getUserotpid()
           		|| "".equals(request.getUserotpid().trim())
           		|| "".equals(!isNumeric(request.getUserotpid()))
           		
           		|| null == request.getIncidentid()
           		|| "".equals(request.getIncidentid().trim()) 
           		//|| "".equals(!isNumeric(request.getIncidentid()))
           		) {
    	 
     	return false;
		}
		return true;
	}
	 
	public static boolean validateGetallarchivedIncidentsRequest(GetAllArchivedIncidentsRequestDTO request){
 	logger.info("Inside validateGetallarchivedIncidentsRequest");	
 
     if(null == request
           		|| null == request.getUserotpid()
           		|| "".equals(request.getUserotpid().trim()) 
           	) {
     	return false;
		}
		return true;
	}
	 
	public static boolean validateGetallIncidentsRequest(GetAllIncidentsRequestDTO request){
 	logger.info("Inside validateGetallIncidentsRequest");	
 
     if(null == request
           		|| null == request.getUserotpid()
           		|| "".equals(request.getUserotpid().trim())
           	) {
     	return false;
		}
		return true;
	}
	
	
	public static boolean validateSaveChatMessage(SaveChatRequestDTO request){
	 	logger.info("Inside validateSaveChatMessage");	
		
	     if(null == request
	           		|| null == request.getIncidentid()
	           		|| "".equals(request.getIncidentid().trim())
	           		//|| (!isNumeric(request.getIncidentid()))
	           		
	           		|| null == request.getUserotpid()
	           		|| "".equals(request.getUserotpid().trim())           		
	           		|| (!isNumeric(request.getUserotpid()))

	           		|| null == request.getMessage()
	           		|| "".equals(request.getMessage().trim())
	           		) {
	     	return false;
			}
			return true;
		} 	


	
	public static boolean validateGetChatMessage(String incidentid){
	 	logger.info("Inside validateSaveChatMessage");	
		
	     if(null == incidentid
	           		|| "".equals(incidentid.trim())
//	           		|| (!isNumeric(incidentid))	           		 
	           		) {
	     	return false;
			}
			return true;
		} 	
}
