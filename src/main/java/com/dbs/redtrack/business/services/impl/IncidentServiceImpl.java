/**
 * 
 */
package com.dbs.redtrack.business.services.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.redtrack.business.service.IApplicationStatusService;
import com.dbs.redtrack.business.service.IncidentService;
import com.dbs.redtrack.business.service.UserRegistrationService;
import com.dbs.redtrack.constants.BusinessConstants;
import com.dbs.redtrack.controller.test.NoteClass;
import com.dbs.redtrack.dto.helper.AppCountry;
import com.dbs.redtrack.dto.helper.ApplicationCountryHelper;
import com.dbs.redtrack.dto.helper.ApplicationHelper;
import com.dbs.redtrack.dto.helper.DeviceTokenHelper;
import com.dbs.redtrack.dto.helper.IncidentHelper;
import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.integration.dao.IncidentDAO;
import com.dbs.redtrack.integration.dao.IncidentHistoryDAO;
import com.dbs.redtrack.integration.dao.UserRegistrationDAO;
import com.dbs.redtrack.jpa.entity.Incident;
import com.dbs.redtrack.jpa.entity.IncidentMap;
import com.dbs.redtrack.jpa.entity.InicidentHistory;
import com.dbs.redtrack.jpa.entity.User;
import com.dbs.redtrack.request.dto.ArchiveIncidentRequestDTO;
import com.dbs.redtrack.request.dto.CreateIncidentRequestDTO;
import com.dbs.redtrack.request.dto.EditIncidentRequestDTO;
import com.dbs.redtrack.response.dto.GetAllIncidentResponseDTO;
import com.dbs.redtrack.response.dto.GetIncidentbyAppIDResponseDTO;
import com.dbs.redtrack.response.dto.GetUserRoleResponseDTO;
import com.dbs.redtrack.response.dto.IncidentResponseDTO;
import com.dbs.redtrack.web.utilities.DateTimeUtil;


/*
 * created by: Raymond 
 * Description: this service is for Incident management
 * 
 * 
 * */

@Service
public class IncidentServiceImpl implements IncidentService {
	private static final Logger logger = Logger.getLogger(IncidentServiceImpl.class);

	@Autowired
	IncidentDAO incidentDAO;
	
	@Autowired
	UserRegistrationDAO userRegistrationDAO;
	
	@Autowired
	IncidentHistoryDAO incidentHistoryDAO;
	
	@Autowired
	IApplicationStatusService iApplicationStatusService;
	
    @Autowired
    ServletContext context; 
    
	@Autowired
	UserRegistrationService userRegistrationService;
	
	/*
	 * comments/changes: This function is used to check for the role of the user and to check if User record is still active
	 * 					 This function is called before creating/updating Incident
	 * 
	 * */
	private User findUserByID(String userID) throws RedTrackProcessingException {
		
		logger.info("IncidentServiceImpl : findUserByID : start");
//		boolean isValid = false;
		User user  = userRegistrationDAO.findUserByID(userID);
		if (null != user) { 
			if (user.getStatus().equalsIgnoreCase(BusinessConstants.STATUS_ACTIVE)){
				logger.info("UserRegistrationServiceImpl :  user("+user+")" );
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
//		isValid = true;
		logger.info("IncidentServiceImpl : findUserByID : end" );
		return user;
	}
	
	
	
	/*
	 * comments/changes: 
	 * 					 
	 * */
	private List<DeviceTokenHelper> getUserTokenStringList(String userID) throws RedTrackProcessingException {
		
		logger.info("IncidentServiceImpl : getUserTokenStringList : start");
 
		List<DeviceTokenHelper> userTokenList  = userRegistrationDAO.getDeviceTokenStringList(userID);
		
		logger.info("IncidentServiceImpl : getUserTokenStringList : end" );
		return userTokenList;
	}	

	
	
	private Map<String, List<String>> getTokenStringList(String userID) {
		
		Map<String, List<String>> tokenMap = new HashMap<String, List<String>>();		
		List<String> iosList = new ArrayList<String>();
		List<String> androidList = new ArrayList<String>();
		
		List<DeviceTokenHelper> deviceTokenList = getUserTokenStringList(userID);		
				
		for (DeviceTokenHelper deviceToken : deviceTokenList) {
			if (null!=deviceToken && null!=deviceToken.getDeviceTokenString()) {
				if (deviceToken.getDevicePlatform() == BusinessConstants.IOS_DEVICE) {
					iosList.add(deviceToken.getDeviceTokenString());
				} else if (deviceToken.getDevicePlatform() == BusinessConstants.ANDROID_DEVICE) {
					androidList.add(deviceToken.getDeviceTokenString());
				}
			}
		}
		
		tokenMap.put("iosList", iosList);
		tokenMap.put("androidList", androidList);
		
		return tokenMap;
	}
	
	

	/*
	 * comments/changes: 
	 * 
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public IncidentResponseDTO createIncident(CreateIncidentRequestDTO createIncidentRequestDTO)throws RedTrackProcessingException {
		logger.info("IncidentServiceImpl :createIncident:Start " + createIncidentRequestDTO);

		IncidentResponseDTO incidentResponseDTO = new IncidentResponseDTO();		
		Incident incidentTemp = new Incident();
		
		//removed 19.07.17
		//changed 19.07.17 updated incidentid to be manually enteredd by user
		Long incidentId = DateTimeUtil.getLongDate();
//		String incidentId = null;
//		incidentId = createIncidentRequestDTO.getIncidentid();
		if (createIncidentRequestDTO!=null) {
			
			//changed 19.07.17 updated incidentid to be manually enteredd by user
//			Incident incident = incidentDAO.findIncidentById(createIncidentRequestDTO.getIncidentid());
//			if (incident!=null) {
//				 throw new RedTrackProcessingException(BusinessConstants.ERROR_00005, "Incident ID already Exist.");
//			}
			
			//boolean  isValidUser = findUserByID(createIncidentRequestDTO.getUserotpid());
			User user = findUserByID(createIncidentRequestDTO.getUserotpid());//to be used for push notification
			logger.info("IncidentServiceImpl :createIncident:Start User(" + user+")");	
			
			
			//get all open incidents
			List<String> appsWithOpenIncidentList = getApplicationsWithOpenIncident();
			

			incidentTemp.setIncidentId(incidentId);
			incidentTemp.setIncidenttitle(createIncidentRequestDTO.getIncidenttitle());
			incidentTemp.setIncidentdesc(createIncidentRequestDTO.getIncidentdesc());
			incidentTemp.setIncidentresolution(createIncidentRequestDTO.getIncidentresolution());
			incidentTemp.setIncidentthreatseverity(createIncidentRequestDTO.getIncidentthreatseverity());		
			incidentTemp.setIncidentcategory(createIncidentRequestDTO.getIncidentcategory());
			incidentTemp.setUserotpid(Long.parseLong(createIncidentRequestDTO.getUserotpid()));
			incidentTemp.setIncidentconfnum(createIncidentRequestDTO.getIncidentconfnum());			
			incidentTemp.setIncidentparticipantcode(createIncidentRequestDTO.getIncidentparticipantcode());
			incidentTemp.setDateCreated(DateTimeUtil.getCurrentDate());
			
			
			List<IncidentMap> incidentSet = new ArrayList<IncidentMap>();
			IncidentMap incidentMap = null;
			//List <String> applicationList = new ArrayList<String>(); //for updating applications master table 
			AppCountry appCountryMap = null;
			List <AppCountry> appCountryMapList = new ArrayList<AppCountry>();
			
			for(ApplicationCountryHelper appCountry : createIncidentRequestDTO.getIncidentApps()) {				
				for(ApplicationHelper application :appCountry.getApplicationList()) {
					incidentMap = new IncidentMap(); 
					//incidentMap.setIncidentId(incidentId);
					incidentMap.setIncident(incidentTemp);
					//incidentMap.setId(incidentId);
					incidentMap.setCountryCode(appCountry.getCountryCode());
					incidentMap.setApplicationCode(application.getApplicationID());
					
					//check for open Incident for Aapplication
					//to do if required to check appid and coutry pair
					//if(appsWithOpenIncidentList.contains(application.getApplicationID()+appCountry.getCountryCode())) {
					
					//changed 07.08.17 Sachin asked to remove check for now.
//					if(appsWithOpenIncidentList.contains(application.getApplicationID())) {
//						throw new RedTrackProcessingException(BusinessConstants.ERROR_00005, BusinessConstants.EXISTING_OPEN_INCIDENT);
//					}
					
					//applicationList.add(application.getApplicationID());//for updating applications master	// old logic to remove				
					incidentSet.add(incidentMap); 
					
					appCountryMap = new AppCountry(appCountry.getCountryCode(), application.getApplicationID());
					appCountryMapList.add(appCountryMap);
				}
			}			
			incidentTemp.setStatus(BusinessConstants.STATUS_ACTIVE);
			incidentTemp.setIncidentMap(incidentSet);
			
			//Set Status Open/Close : changed: 061317 : archived = closed
			//incidentTemp.setIncidentStatus(getIncidentStatus(incidentTemp.getIncidentthreatseverity()));//status open/closed
			incidentTemp.setIncidentStatus(BusinessConstants.STATUS_OPEN);
			
			//Create Incident
			incidentDAO.createIncident(incidentTemp);
			
			//Update Status based on Severity level SL1 = 1, SL2 =2 ....
			String appStatus = incidentTemp.getIncidentthreatseverity().substring(2);
			//iApplicationStatusService.updateApplicationsStatus(applicationList, appStatus);// old logic to remove
			iApplicationStatusService.updateApplicationsStatus(appCountryMapList, appStatus); 
			
			incidentResponseDTO.setMessage(BusinessConstants.INCIDENT_CREATED_SUUCCESSFULLY);
			incidentResponseDTO.setResponsecode(BusinessConstants.RESPONSE_CODE_SUCCESS);
			incidentResponseDTO.setIncidentId(String.valueOf(incidentId));
			
			//start push notification
			String userID = String.valueOf(user.getId());
			//List<String> userTokenList = getUserTokenStringList(userID); //returns ios/android token string
//			
			Map tokenMap = getTokenStringList(userID);
			
			List<String> iosList = (List<String>) tokenMap.get("iosList");
			List<String> androidList = (List<String>) tokenMap.get("androidList");
			incidentTemp.setUserDetail(user);
			
			//Start sending push notification to IOS
 			logger.info("TokenList:(" +iosList+")");
			try {
				pushMessage(iosList, "New Incident, " + incidentTemp.getIncidenttitle() + 
						"\nCreated by "+ user.getFullName() +
						"\nDescription: " + incidentTemp.getIncidentdesc());
			} catch (Exception ex) {
				logger.info("Unable to send push notification to IOS device(" + ex.getMessage() + ")");
			}
		
			//Start sending push notification to Andorid
 			logger.info("TokenList:(" +androidList+")");		
			try {
				NoteClass aa=new NoteClass();
				aa.sendAndroid(androidList, incidentTemp);
			} catch(Exception ex) {
				logger.info("Unable to send push notification to Adnroid device(" + ex.getMessage() + ")");
			}
		
			
			//end 
			
		} else {
			incidentResponseDTO.setMessage(BusinessConstants.ERROR_CREATING_INCIDENT);
			incidentResponseDTO.setResponsecode(BusinessConstants.ERROR_00001);
		}

		logger.info("IncidentServiceImpl : createIncident :end");
		return incidentResponseDTO;
	}


	
	@SuppressWarnings("unused")
	private String getIncidentStatus(String severityLevel) {

		String status = null;		
		List<String> list = new ArrayList<String>(Arrays.asList("SL1", "SL2", "SL3"));
		
		if(list.contains(severityLevel)) {
			status = "Open";			
		} else {			
			status = "Closed";
		}		
		return status;
	}
	
	
	
	private List<String> getApplicationsWithOpenIncident() {
		
		List<Incident> openList = incidentDAO.getallincidentsByStatus("Open");		
		List<String> applicationList = new ArrayList<String>();		 
		for (Incident incident : openList) {
			for(IncidentMap incidentApplications: incident.getIncidentMap()) {
				applicationList.add(incidentApplications.getApplicationCode());
				//todo: if required to check country appid pair ex: SGAPP1
				//applicationList.add(incidentApplications.getApplicationCode() + incidentApplications.getCountryCode());
			}
		}
		
		return applicationList;
	}
	
	 
	/*
	 * comments/changes: 
	 * 
	 * */
	@Override
	public IncidentResponseDTO editIncident(EditIncidentRequestDTO editIncidentRequestDTO)throws RedTrackProcessingException {
		logger.info("IncidentServiceImpl :createIncident:Start " + editIncidentRequestDTO);
		 
		IncidentResponseDTO incidentResponseDTO = new IncidentResponseDTO();
		
		//changed 19.07.17 change for incident id input by user
		long incidentID = Long.parseLong(editIncidentRequestDTO.getIncidentid());
//		String incidentID = editIncidentRequestDTO.getIncidentid();
		
//		Incident incident = incidentDAO.findIncidentById(editIncidentRequestDTO.getIncidentid());
//		if (incident!=null) {
//			 throw new RedTrackProcessingException(BusinessConstants.ERROR_00005, "Incident ID already Exist.");
//		}
		
//		boolean  isValidUser = findUserByID(editIncidentRequestDTO.getUserotpid());
//		logger.info("IncidentServiceImpl : editIncident : Start isValidUser(" + isValidUser+")");	
		User user = findUserByID(editIncidentRequestDTO.getUserotpid()); //to be used for push notification
		logger.info("IncidentServiceImpl :editIncident : Start User(" + user+")");	
		
		//changed 19.07.17 change for incident id input by user
		if(null != incidentDAO.findIncidentByID(incidentID) ) {
		//if(null != incidentDAO.findIncidentById(incidentID) ) {
			
			Incident incidentTemp = new Incident();		 
			incidentTemp.setIncidentId(incidentID);
			incidentTemp.setIncidenttitle(editIncidentRequestDTO.getIncidenttitle());
			incidentTemp.setIncidentdesc(editIncidentRequestDTO.getIncidentdesc());
			incidentTemp.setIncidentresolution(editIncidentRequestDTO.getIncidentresolution());
			incidentTemp.setIncidentthreatseverity(editIncidentRequestDTO.getIncidentthreatseverity());		
			incidentTemp.setIncidentcategory(editIncidentRequestDTO.getIncidentcategory());
			incidentTemp.setUserotpid(Long.parseLong(editIncidentRequestDTO.getUserotpid()));
			incidentTemp.setUpdatedBy(Long.parseLong(editIncidentRequestDTO.getUserotpid()));
			incidentTemp.setIncidentconfnum(editIncidentRequestDTO.getIncidentconfnum());			
			incidentTemp.setIncidentparticipantcode(editIncidentRequestDTO.getIncidentparticipantcode());
			incidentTemp.setDateCreated(DateTimeUtil.getCurrentDate());
			
//			Set<IncidentMap> incidentSet = new HashSet<IncidentMap>();
			List<IncidentMap> incidentSet = new ArrayList<IncidentMap>();
			IncidentMap incidentMap = null;
			//List <String> applicationList = new ArrayList<String>(); //for updating applications master table
			AppCountry appCountryMap = null;
			List <AppCountry> appCountryMapList = new ArrayList<AppCountry>();
			
			for(ApplicationCountryHelper appCountry : editIncidentRequestDTO.getIncidentApps()) {				
				for(ApplicationHelper application :appCountry.getApplicationList()) {
					incidentMap = new IncidentMap(); 
					//incidentMap.setIncidentId(incidentID);
					//incidentMap.setId(incidentID);
					incidentMap.setIncident(incidentTemp);
					incidentMap.setCountryCode(appCountry.getCountryCode());
					incidentMap.setApplicationCode(application.getApplicationID());
					
					//applicationList.add(application.getApplicationID());//for updating applications master					
					incidentSet.add(incidentMap);
					
					appCountryMap = new AppCountry(appCountry.getCountryCode(), application.getApplicationID());
					appCountryMapList.add(appCountryMap);					
				}
			}			
			incidentTemp.setStatus(BusinessConstants.STATUS_ACTIVE);
			incidentTemp.setIncidentMap(incidentSet);
			
			//Set Status Open/Close : changed: 061317 : archived = closed
			//incidentTemp.setIncidentStatus(getIncidentStatus(incidentTemp.getIncidentthreatseverity()));//status open/closed
			incidentTemp.setIncidentStatus(BusinessConstants.STATUS_OPEN);
					
			//update incident
			incidentDAO.saveUpdateIncident(incidentTemp);
			
			//Update Status based on Severity level SL1 = 1, SL2 =2 ....
			//: changed: 061317 : archived = closed = green
			String appStatus = incidentTemp.getIncidentthreatseverity().substring(2);
//			iApplicationStatusService.updateApplicationsStatus(applicationList, appStatus);
			iApplicationStatusService.updateApplicationsStatus(appCountryMapList, appStatus); 
			
			
			incidentResponseDTO.setMessage(BusinessConstants.INCIDENT_UPDATED_SUUCCESSFULLY);
			incidentResponseDTO.setResponsecode(BusinessConstants.RESPONSE_CODE_SUCCESS);
			incidentResponseDTO.setIncidentId(String.valueOf(incidentID));
		} else {
			incidentResponseDTO.setMessage(BusinessConstants.ERROR_UPDATED_INCIDENT);	
			incidentResponseDTO.setResponsecode(BusinessConstants.ERROR_00001);
		} 
		 
		return incidentResponseDTO;
		  
	}
	
	
	/*
	 * comments/changes: 
	 * 
	 * */
	@Override
	public GetAllIncidentResponseDTO getallincidents(String userotpid)throws RedTrackProcessingException {
		
		logger.info("IncidentServiceImpl : getallincidents :Start");		
		GetAllIncidentResponseDTO getAllIncidentResponseDTO = new GetAllIncidentResponseDTO();
		List<IncidentHelper> list = new ArrayList<IncidentHelper>();
		IncidentHelper incHelper = null;
		
		List<Incident> listOfIncident = incidentDAO.getallincidents();
		//if(listOfIncident.size() != 0) {
		if(listOfIncident != null) {
			for(Incident inc: listOfIncident) {
				incHelper = new IncidentHelper();
				incHelper.setIncidentid(String.valueOf(inc.getIncidentId()));
				incHelper.setIncidenttitle(inc.getIncidenttitle());
				incHelper.setIncidentdesc(inc.getIncidentdesc());
				incHelper.setIncidentresolution(inc.getIncidentresolution());
				incHelper.setIncidentthreatseverity(inc.getIncidentthreatseverity());
				incHelper.setIncidentcategory(inc.getIncidentcategory());
				incHelper.setUserotpid(String.valueOf(inc.getUserotpid()));
				incHelper.setUserFullName(inc.getUserDetail().getFullName());
				incHelper.setDateCreated(DateTimeUtil.convertTimestampToString( inc.getDateCreated()));				
				incHelper.setIncidentconfnum(inc.getIncidentconfnum()); 
				incHelper.setIncidentparticipantcode(inc.getIncidentparticipantcode());
				
				//convert List<IncidentMap> to List<ApplicationCountryHelper>
				HashMap<String, ApplicationCountryHelper> mapper = new HashMap<String, ApplicationCountryHelper>();				
				for(IncidentMap incidentMap : inc.getIncidentMap()) {					
					if (!mapper.containsKey(incidentMap.getCountryCode())) {
						mapper.put(incidentMap.getCountryCode(), new ApplicationCountryHelper(incidentMap.getCountryCode(), incidentMap.getApplicationCode()) );
					} else {
						ApplicationCountryHelper app = mapper.get(incidentMap.getCountryCode());
						app.getApplicationList().add(new ApplicationHelper(incidentMap.getApplicationCode()));						
					}						
				} 				
				incHelper.setIncidentApps(new ArrayList<ApplicationCountryHelper>(mapper.values()));
				
				
				list.add(incHelper);
			}			
			getAllIncidentResponseDTO.setResponsecode(BusinessConstants.RESPONSE_CODE_SUCCESS);
		} else {			
			getAllIncidentResponseDTO.setResponsecode(BusinessConstants.ERROR_00001);
		}
		
		getAllIncidentResponseDTO.setAllincidents(list);
		
//		GetUserRoleResponseDTO userRoleResponseDTO = userRegistrationService.getUserRole(userotpid);
//		getAllIncidentResponseDTO.setRoleis(userRoleResponseDTO.getRoleis());
		
		logger.info("IncidentServiceImpl : getallincidents :end");
		return getAllIncidentResponseDTO;
	} 
	
	
	/*
	 * comments/changes: 
	 * 
	 * */
	@Override
	public GetAllIncidentResponseDTO getAllArchivedincidents(String struserotpid)throws RedTrackProcessingException {
		
		logger.info("IncidentServiceImpl : getAllArchivedincidents :Start("+ struserotpid+")");
		GetAllIncidentResponseDTO getAllIncidentResponseDTO = new GetAllIncidentResponseDTO();
		List<IncidentHelper> list = new ArrayList<IncidentHelper>();
		IncidentHelper incHelper = null;
		
		List<Incident> listOfIncident = incidentDAO.getallArchivedincidents(struserotpid);
		//if(null!= listOfIncident && listOfIncident.size() != 0) {
		if(listOfIncident != null) {
			for(Incident inc: listOfIncident) {
				incHelper = new IncidentHelper();
				incHelper.setIncidentid(String.valueOf(inc.getIncidentId()));
				incHelper.setIncidenttitle(inc.getIncidenttitle());
				incHelper.setIncidentdesc(inc.getIncidentdesc());
				incHelper.setIncidentresolution(inc.getIncidentresolution());
				incHelper.setIncidentthreatseverity(inc.getIncidentthreatseverity());
				incHelper.setIncidentcategory(inc.getIncidentcategory());
				incHelper.setUserotpid(String.valueOf(inc.getUserotpid()));
				incHelper.setUserFullName(inc.getUserDetail().getFullName());
				incHelper.setDateCreated(DateTimeUtil.convertTimestampToString( inc.getDateCreated()));
				incHelper.setIncidentconfnum(inc.getIncidentconfnum()); 
				incHelper.setIncidentparticipantcode(inc.getIncidentparticipantcode());
				list.add(incHelper);
			}
			getAllIncidentResponseDTO.setAllincidents(list);			
			getAllIncidentResponseDTO.setResponsecode(BusinessConstants.RESPONSE_CODE_SUCCESS);	
		} else {
			getAllIncidentResponseDTO.setAllincidents(list);
			getAllIncidentResponseDTO.setResponsecode(BusinessConstants.ERROR_00001);	
		}
		GetUserRoleResponseDTO userRoleResponseDTO = userRegistrationService.getUserRole(struserotpid);
		getAllIncidentResponseDTO.setRoleis(userRoleResponseDTO.getRoleis());
		
		logger.info("IncidentServiceImpl : getAllArchivedincidents :end");
		return getAllIncidentResponseDTO;
	} 
	
	
	/*
	 * comments/changes: 
	 * 
	 * */                
	@Override
	public IncidentResponseDTO archiveIncident(ArchiveIncidentRequestDTO archiveIncidentRequestDTO)throws RedTrackProcessingException {
		logger.info("IncidentServiceImpl :archiveIncident : start");
		 
//		boolean  isValidUser = findUserByID(archiveIncidentRequestDTO.getUserotpid());
//		logger.info("IncidentServiceImpl : archiveIncident : Start isValidUser(" + isValidUser+")");
		User user = findUserByID(archiveIncidentRequestDTO.getUserotpid());//to be used for push notification
		logger.info("IncidentServiceImpl :editIncident : Start User(" + user+")");	
		
		IncidentResponseDTO incidentResponseDTO = new IncidentResponseDTO();		
		List <AppCountry> appCountryMapList = new ArrayList<AppCountry>();
		AppCountry appCountryMap = null; 
		//changing functionality all archived is = closed
		int x = incidentDAO.archiveIncident(archiveIncidentRequestDTO.getIncidentid(),archiveIncidentRequestDTO.getUserotpid());
		if (x >0) {
				incidentResponseDTO.setMessage(BusinessConstants.ARCHIVEINCIDENT_SAVED_SUUCCESSFULLY);
				incidentResponseDTO.setResponsecode(BusinessConstants.RESPONSE_CODE_SUCCESS);
				incidentResponseDTO.setIncidentId(archiveIncidentRequestDTO.getIncidentid());
				logger.info("IncidentServiceImpl : archiveIncident : Saved Successfully");				
 
				//start to update status to green
				//changed 19.07.17 updated incidentid to be manually enteredd by user
				Incident incident = incidentDAO.findIncidentByID(Long.parseLong(archiveIncidentRequestDTO.getIncidentid()));
				//Incident incident = incidentDAO.findIncidentById(archiveIncidentRequestDTO.getIncidentid());
				for (IncidentMap map :incident.getIncidentMap()) {
					appCountryMap = new AppCountry(map.getCountryCode(), map.getApplicationCode());
					appCountryMapList.add(appCountryMap);
				}
				iApplicationStatusService.updateApplicationsStatus(appCountryMapList, BusinessConstants.STATUS_GREEN); 
				//end update status to green
				
		} else {
				incidentResponseDTO.setMessage(BusinessConstants.ERROR_CREATING_ARCHIVEINCIDENT);
				incidentResponseDTO.setResponsecode(BusinessConstants.ERROR_00001);
				incidentResponseDTO.setIncidentId(archiveIncidentRequestDTO.getIncidentid());
				logger.info("IncidentServiceImpl : archiveIncident : Not Saved Successfully");
		}

		logger.info("IncidentServiceImpl : archiveIncident :end");
		return incidentResponseDTO;
	}
	
	
	/*
	 * comments/changes: 
	 * 
	 * */                
	@Override
	public GetIncidentbyAppIDResponseDTO getIncidentByAppID(String strAppID)throws RedTrackProcessingException {
		logger.info("IncidentServiceImpl :getIncidentByAppID : start");
		   
		GetIncidentbyAppIDResponseDTO getIncidentbyAppIDResponseDTO = new GetIncidentbyAppIDResponseDTO();		
	
		
		Incident incident = incidentDAO.getIIncidentByAppID(strAppID);
		if (null != incident) {
			getIncidentbyAppIDResponseDTO.setIncidentid(String.valueOf(incident.getIncidentId()));
			getIncidentbyAppIDResponseDTO.setIncidenttitle(incident.getIncidenttitle());
			getIncidentbyAppIDResponseDTO.setIncidentdesc(incident.getIncidentdesc());
			getIncidentbyAppIDResponseDTO.setIncidentresolution(incident.getIncidentresolution());
			getIncidentbyAppIDResponseDTO.setIncidentcategory(incident.getIncidentcategory());
			getIncidentbyAppIDResponseDTO.setIncidentthreatseverity(incident.getIncidentthreatseverity());
			getIncidentbyAppIDResponseDTO.setUserFullName(incident.getUserDetail().getFullName());
			getIncidentbyAppIDResponseDTO.setDateCreated((DateTimeUtil.convertTimestampToString( incident.getDateCreated())));
			
		} else {
			throw new RedTrackProcessingException("00005", BusinessConstants.NO_RECORD_FOUND);
		}

		logger.info("IncidentServiceImpl : getIncidentByAppID :end");
		return getIncidentbyAppIDResponseDTO;
	} 


//
//	@SuppressWarnings("unused")
//	private void saveIncidentHistory(Incident incident) {
//
//		InicidentHistory inicidentHistory = new InicidentHistory();
//		inicidentHistory.setIncidentID(incident.getIncidentId());
//		inicidentHistory.setSeverityLevel(incident.getIncidentthreatseverity());
//		
//		List<String> list = new ArrayList<String>(Arrays.asList("SL1", "SL2", "SL3"));
//		
//		if(list.contains(incident)) {
//			System.out.println("FOUND!!");
//			inicidentHistory.setStatus("OPEN");
//		} else {
//			
//			System.out.println("Not FOUND!!!");
//		}		
//		//check sl1-3 open 4 : close
//		//inicidentHistory.setStatus(status);
//		
//		//incidentHistoryDAO.saveIncidentHistory(inicidentHistory);
//		
//	}

	
	  public void pushMessage(List<String> tokenlist, String notificationMsg) {
//	    	notificationMsg="CBG Health Check - All applications are fully operational.";
			try	{
				if(tokenlist!=null && tokenlist.size()>0) {
						String certsrootpath = context.getRealPath("certspath");
						certsrootpath=certsrootpath+File.separator+"LifestyleUAT29052017(1).p12";
						logger.info("The new complete path is ==> "+certsrootpath);
						NoteClass aa=new NoteClass();
						aa.sendnotes(certsrootpath, tokenlist, notificationMsg);
				}
			}catch(Exception e) {
				//e.printStackTrace();
				throw new RedTrackProcessingException("0004", "Unable to send push notification. please check with the admin.");
			}    	
	    }
	  
//
//	@Override
//	public List<Incident> getallincidentMessages(GetAllIncidentMessagesRequestDTO getAllIncidentMessagesRequestDTO)throws RedTrackProcessingException {
//		logger.info("IncidentServiceImpl : getallincidentMessages :Start");
//		 
//		Incident incidentResponseDTO = new Incident();		
//		List<Incident> incidentList= new ArrayList<Incident>();
// 
//		List<Incident> inicidentList = incidentDAO.getallincidentMessages(getAllIncidentMessagesRequestDTO.getUserotpid());
//		for(Incident incident: inicidentList) {
//				
//			incidentResponseDTO.setIncidentId(String.valueOf(incident.getIncidentId()));
//			incidentResponseDTO.setIncidentthreatseverity(incident.getIncidentthreatseverity());
//			incidentList.add(incidentResponseDTO);
//
//		}
// 
//			 
//		logger.info("IncidentServiceImpl : getallincidentMessages :end");
//		return incidentList;
//	}
}
