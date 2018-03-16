package com.dbs.redtrack.constants;

public class DAOConstants {

	/********** UserSession ***********/
	public static final String USERID = "userId";
	public static final String SERVERNAME = "serverName";
	public static final String SESSIONID = "sessionId";
	public static final String RANDOM_NUMBER = "randomNumber";
	public static final String CHANNEL_ID = "channelID";
	public static final String CHANNEL_ID_VALUE = "OMNI";
	public static final String ACTORID = "actorId";	
	public static final String SERVER_NAME = "serverName";
	public static final String CURRENT_MINS = "currentMins";
	public static final String OS_TYPE= "osType";	
	public static final String UPGRADE_TYPE = "upgradeType";
	public static final String VERSION_ID = "versionId";
	public static final int ONE = 1;
	public static final String TWO = "2";
	public static final String THREE = "3";
	public static final String ZERO = "0";
	public static final String EMPTY = "";
	public static final String DEC = "D";
 
	/********** User   ***********/
	public static final String QUERY_USER__SELECT_ALL = "SELECT a FROM User a WHERE a.country = :country";
	public static final String QUERY_USER_BY_PHONENUMBER_COUNTRY = "SELECT a FROM User a WHERE a.phoneNumber = :strPhoneNumber and a.countryCode =:strCountryCode";
	//user Token
//	public static final String QUERY_DEVICE_TOKEN_LIST= "SELECT a.deviceTokenString FROM User a WHERE a.id != :struserotpid and a.deviceID= :deviceID and a.status =:status";
	
//	public static final String QUERY_DEVICE_TOKEN_LIST= "SELECT a.deviceTokenString FROM User a WHERE a.id != :struserotpid "
//			+ "and a.status =:status and "
//			+ "a.devicePlatform=(Select b.devicePlatform from User b WHERE b.id= :struserotpid)";

	public static final String QUERY_DEVICE_TOKEN_LIST= "SELECT a.deviceTokenString, a.devicePlatform FROM User a WHERE a.id != :struserotpid "
			+ "and a.status =:status";
	

	
	/********** UserTemp   ***********/
	public static final String QUERY_USER_TEMP__SELECT_ALL = "SELECT a FROM UserTemp a WHERE a.countryCode = :strCountry and a.status =:strStatus";	
	public static final String QUERY_USER_TEMP_BY_PHONENUMBER = "SELECT a FROM UserTemp a WHERE a.phoneNumber = :strPhoneNumber";
	public static final String QUERY_USER_TEMP_BY_PHONENUMBER_COUNTRY_CODE = "SELECT a FROM UserTemp a WHERE a.phoneNumber = :strPhoneNumber and a.countryCode =:strCountryCode";
	public static final String DELETE_USER_TEMP = "DELETE FROM UserTemp a where a.status =:strStatus";
	
	/********** WhiteList   ***********/
	public static final String QUERY_WHITE_LIST_BY_PHONENUMBER = "SELECT a FROM WhiteList a WHERE a.phoneNumber = :strPhoneNumber";	
	
	/********** *********** Call History *********** ***********/
	public static final String QUERY_CALL_HISTORY_BY_USER = "SELECT a FROM CallHistory a WHERE a.userOTPID = :userOTPID";	
	
	/********** *********** Call History *********** ***********/
	public static final String QUERY_SUPPORT_CONTACT_LIST = "SELECT a FROM SupportContact a order by a.status desc";	
	
	
	/****************** Incident ************/
	public static final String QUERY_INCIDENT_BY_INCIDENT_ID = "SELECT a FROM Incident a WHERE a.incidentId = :strincidentId";
	public static final String QUERY_INCIDENT_BY_USER = "SELECT a FROM Incident a WHERE a.userotpid = :strUserotpid";
	public static final String DELETE_ICIDENT_BY_ID = "DELETE FROM IncidentMap a where a.incident =:incidentID";
	public static final String UPDATE_ICIDENT_BY_ID = "Update Incident a set a.status= :strStatus, a.incidentStatus= :incidentStatus, a.updatedBy= :updatedBy where a.incidentId= :incidentID";
	
	public static final String QUERY_INCIDENT_BY_APPLICATION_ID = "SELECT a FROM Incident a Left Join a.incidentMap b  where "
			+ "a.incidentStatus = :incidentStatus and b.applicationCode = :applicationCode";
	/******************getallincidents by userdevictokenstr,userotpid,accessprofileid ************/	
	public static final String QUERY_ALL_INCIDENT_BY_USERDEVICTOKENSTR_USEROTPID = "SELECT a FROM Incident a WHERE a.userdevictokenstr=:struserdevictokenstr and a.userotpid = :struserotpid";
	public static final String QUERY_ALL_INCIDENT = "SELECT a FROM Incident a where a.incidentStatus = :incidentStatus order by a.dateCreated desc";
	public static final String QUERY_ALL_INCIDENT_BY_STATUS = "SELECT a FROM Incident a where a.incidentStatus = :incidentStatus";	
	/****************** Incident History ************/
	public static final String QUERY_INCIDENT_HISTORY_BY_INCIDENT_ID = "SELECT a FROM InicidentHistory a WHERE a.incidentID = :strincidentId";	
	/******************Archive Incident ************/
	public static final String QUERY_ARCHIVE_INCIDENT_BY_USEROTP_ID = "SELECT a FROM Incident a WHERE a.userotpid = :struserotpid and a.status = :strStatus";	
	public static final String QUERY_ARCHIVE_INCIDENT = "SELECT a FROM Incident a WHERE a.status = :strStatus order by a.dateCreated desc";
	public static final String QUERY_GET_ALL_APPLICATIONS= "SELECT a FROM ApplicationStatus a";
	
	/********** Applications ***********/
	public static final String QUERY_APPLICATIONS_DETAILS = "select a from Applications a";
	/********** Applications_Status ***********/
	public static final String QUERY_APPLICATIONS_STATUS_UPDATE = "UPDATE ApplicationStatus a SET a.status = :strStatus WHERE a.appId = :strAppId and a.countryCode= :countryCode";
	public static final String QUERY_APPLICATIONS_STATUS_UPDATE2 = "UPDATE ApplicationStatus a SET a.status = :strStatus, a.appComments =:comment  WHERE a.appId = :strAppId and a.countryCode= :countryCode";

	public static final String QUERY_GET_APPLICATIONS_STATUS = "select a from ApplicationStatus a";
	
	//raymond
//	select b.COUNTRY_CODE, c.COUNTRY_DESCRIPTION, b.APP_ID, a.APPLICATION_NAME,, a.APP_CODE b.UNIT_ID, d.DESCRIPTION, b.status
//	from applications a, application_status b, country_master c, business_units d
//	where a.APP_ID = b.APP_ID and a.APP_ACTIVE='Y' and b.COUNTRY_CODE = c.COUNTRY_CODE
//	and b.unit_id = d.UNIT_ID
//	group by  b.app_id, b.country_code order by a.app_ID;
	public static final String QUERY_GET_APPLICATIONS_DETAILS = 
	"select b.countryCode, c.countryDescription, b.appId, a.appName, a.appCode, b.unitId, d.businessUnitDesc, b.status, a.appActive " +
	"from Applications a, ApplicationStatus b, Country c, BusinessUnit d " +
	"where a.appId = b.appId and a.appActive= :appActive and b.countryCode = c.countryCode " +
	"and b.unitId = d.businessUnitID " +
	"group by b.appId, b.countryCode order by a.appId";
	
	//Raymond
	public static final String UPDATE_APPLICATIONS_STATUS = "update a.ApplicationStatus a set a.status= :status "
			+ "where a.appId in (select b.applicationCode from Incident c left Join a.incidentMap b where c.incidentId= :incidentId)";
	
	/*************CHAT MESSAGE***************************/
	public static final String QUERY_CHAT_MESSAGE_BY_INCIDENT = "SELECT a FROM ChatMessage a WHERE a.incidentID = :incidentID order by a.dateCreated ASC";
	
	/************************* Audit Log *************************************/
	public static final String QUERY_AUDIT_LOG_BY_USERID = "SELECT a FROM AuditLog a WHERE a.userID = :userID";
//	/  select * from incident where  DATE_FORMAT(date_created, '%Y-%m-%d') = '2017-06-09' order by date_created asc
	public static final String QUERY_AUDIT_LOG_BY_DATE = "SELECT a FROM AuditLog a WHERE DATE_FORMAT(a.dateCreated, '%Y-%m-%d') = :dateCreated order by dateCreated asc";
	public static final String QUERY_AUDIT_LOG_BY_USER_AND_DATE = "SELECT a FROM AuditLog a WHERE DATE_FORMAT(a.dateCreated, '%Y-%m-%d') = :dateCreated "
			+ " and a.userID = :userID order by dateCreated asc";
//	
//	 select country_code, unit_id, status from application_status  group by country_code, unit_id
//	 
//	//APPLICATION STATUS
//	public static final String QUERY_GET_APPLICATIONS_GROUP_BY_APP = "select a from ApplicationStatus a ";
//	public static final String QUERY_GET_APPLICATIONS__BY_BUSINESS_UNIT = "select a from ApplicationStatus a";
}
