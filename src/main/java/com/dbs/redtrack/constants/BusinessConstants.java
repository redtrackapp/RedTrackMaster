package com.dbs.redtrack.constants;

import org.springframework.stereotype.Component;

@Component
public class BusinessConstants {

	//Application Config
	public static final String REDTRACK_CONFIG_PROPERTY = "classpath:/properties/RedTrackConfig.properties";
	
//	//TWILIO Settings
	public static final  String ACCOUNT_SID = "ACa9620ecd9511404353b4da68b6cbd651"; 
	public static final  String AUTH_TOKEN = "8e5eab3caa26edb2298bfeb470b5b571";
	public static final String TWILIO_FROM = "+12013456254";
	
	
 
	
	//User REgistration
	public static final String REGISTER_USER = "/registerUser";
	public static final String GENERATE_OTP = "/generateOtp";
	public static final String VALIDATE_OTP = "/validateOtp";	
	public static final String UPDATE_USER = "/updateUser";	
	public static final String GET_USER_ROLE = "/getUserRole";
	//Call History
	public static final String GET_CALL_HISTORY = "/getcallHistory";
	public static final String SAVE_CALL_HISTORY = "/savecallHistory";
	//Incidents API
	public static final String GET_ALL_INCIDENTS = "/getallincidents";
	public static final String GET_ALL_ARCHIVED_INCIDENTS = "/getallarchivedIncidents";
	public static final String CREATE_INCIDENT = "/createIncident";	
	public static final String EDIT_INCIDENT = "/editIncident";
	public static final String GET_ALL_INCIDENT_MESSAGES = "/getallincidentMessages";
	public static final String ARCHIVE_INCIDENT = "/archiveIncident";
	public static final String GET_INCIDENT_BY_APPID = "/getIncidentByAppID";	
	//Chat Messages
	public static final String GET_ALL_CHAT_MESSAGES = "/getChatMessage";
	public static final String SAVE_CHAT_MESSAGE = "/saveChatMessage";
	//Application Status
	public static final String GET_ALL_APPLICATIONS = "/getAllApplications";
	public static final String APPLICATION_DETAILS = "/applicationDetails";
	public static final String APPLICATION_STATUS_UPDATE = "/updateApplicationStatus";
	public static final String GET_APPLICATION_STATUS = "/getApplicationStatus";
	
	//Support Contact
	public static final String GET_SUPPORT_CONTACT_LIST = "/getSupportContactList";
	
	//error code
	public static final String ERROR_00001 = "00001";
	public static final String ERROR_00002 = "00002";
	public static final String ERROR_00003 = "00003";
	public static final String ERROR_00004 = "00004";
	public static final String ERROR_00005 = "00005";
	
	//error message
	public static final String INVALID_REQUEST = "Invalid Request Parameters Please Check.";
	
	
	
	//For Cron Jobs

	 public static final String TIME_ZONE ="Asia/Singapore";
	 public static final String CRON_ENTRY ="0 1 1 * * ?";//12:01am 

	 public static final String CRON_ENTRY_20 ="0 0 * * * *"; //= the top of every hour of every day.
			 
			 //"*/20 * * * * *" ; //10 seconds
// 
//	    "0 0 * * * *" = the top of every hour of every day.
//	    "*/10 * * * * *" = every ten seconds.
//	    "0 0 8-10 * * *" = 8, 9 and 10 o'clock of every day.
//	    "0 0 6,19 * * *" = 6:00 AM and 7:00 PM every day.
//	    "0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30, 10:00 and 10:30 every day.
//	    "0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
	 
 	 //STATUS
	 public static final int ROLE_1 = 1;
	 public static final String STATUS_ACTIVE = "A";
	 public static final String STATUS_ARCIVED = "D";
	 public static final String STATUS_OPEN = "Open";
	 public static final String STATUS_CLOSED = "Closed";
	 public static final String STATUS_PROCESSING = "P";
	 public static final String WHITE_LISTING_ACTIVE = "01";
	 public static final String IS_OTP_SENT = "00";
	 public static final String IS_OTP_CLAIMED = "00";
	 public static final String USER_EXIST= "01";
	 public static final String NEW_USER= "00";
	 public static final String STATUS_GREEN= "4";
	 
	 public static final String OTP_EXPIRY = "120";
	 public static final String TIME_ZONE_ASIA = "Asia/Singapore";
	 public static final int TWO_MINUTES = 120000; //2*60*1000;
	 public static final String RESPONSE_CODE_SUCCESS = "0000";
	 
	 public static final String EXPIRED_OTP_CODE = "00001";
	 public static final String INVALID_OTP_CODE = "00002";
	 public static final String INVALID_MOBILENUMBER_CODE = "00003";
	 
	 public static final int IOS_DEVICE = 1;
	 public static final int ANDROID_DEVICE = 0;

	 
	//Messages for User Registration
	//registerUser
	public static final String REGISTERED_SUUCCESSFULLY = "Successfully Registered number.";
	public static final String PHONE_NUMBER_NOT_IN_WHITELIST = "Phone Number is not allowed to register.";
	//generateOTP
	public static final String GENERATED_OTP_SUCCESSFULLY = "Successfully generated OTP.";
	public static final String UNABLE_TO_GENERATE_OTP = "Unable to update OTP.";		
	//validateOTP
	public static final String OTP_VERIFIED = "OTP Verification Successfull.";
	public static final String OTP_EXPIRED = "OTP has expired please re-generate OTP.";
	public static final String INVALID_OTP = "Invalid OTP please check and try again.";
	public static final String INVALID_PHONE_NUMBER = "Please check the phone number.";
	//updateUser
	public static final String USER_UPDATE_SUCCESSFULL = "Successfully updated User details";
	public static final String ERROR_UPDATING_USER = "Unable to update User. Please check with the Admin.";
	//Find user		
	public static final String ACTIVE_USER_FOUND = "Found active user account";
	public static final String USER_NOT_REGISTERED = "Unable to find active user account. Please check with the Admin.";
	public static final String USER_ROLE_INVALID = "User do not have the role to create/updater record. Please check with the Admin.";
	public static final String PHONE_NUMBER_NOT_ACTIVE = "User account inactive. Please check with the Administrator.";
	
	//Messages for Incident creation
	public static final String INCIDENT_CREATED_SUUCCESSFULLY = "Successfully Created Incident.";
	public static final String ERROR_CREATING_INCIDENT= "Unable to Create Incident.";	
	//Message for Edit Incident 
	public static final String INCIDENT_UPDATED_SUUCCESSFULLY = "Successfully Updated Incdent.";
	public static final String ERROR_UPDATED_INCIDENT = "Unable to Update Incident. Please check with the Admin.";	
	//Message for getallincidentMessages
	public static final String INCIDENT_MESSAGES_RECEIVED_SUUCCESSFULLY = "Successfully Received Incident Message.";
	public static final String ERROR_RECEIVING_INCIDENT_MESSAGES = "Unable to Receive Incident Messages. Please check with the Admin.";
	//Existing open incident
	public static final String EXISTING_OPEN_INCIDENT = "There is an Open Incident that has the same application affected.";
	public static final String NO_RECORD_FOUND = "No Record found";
	
	//Messages for  Archive Incident 
	public static final String ARCHIVEINCIDENT_SAVED_SUUCCESSFULLY = "Successfully Archived Incident.";
	public static final String ERROR_CREATING_ARCHIVEINCIDENT= "Unable to Archive Incident.";
	//Message for getallincidentMessages
	public static final String ARCHIVEINCIDENT_RECEIVED_SUUCCESSFULLY = "Successfully Received ArchiveIncident.";
	public static final String ERROR_RECEIVING_ARCHIVEINCIDENT = "Unable to Receive ArchiveIncident. Please check with the Admin.";
	
	public static final String ADMIN_PAGE_ONE = "/adminModule";
}
