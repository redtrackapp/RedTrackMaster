package com.dbs.redtrack.integration.dao.impl;
/**
 * 
 * @author Imran
 *
 */
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.integration.dao.AdminViewDAO;
import com.dbs.redtrack.jpa.entity.ApplicationStatus;
import com.dbs.redtrack.jpa.entity.Applications;
import com.dbs.redtrack.jpa.entity.BusinessUnit;
import com.dbs.redtrack.jpa.entity.Country;
import com.dbs.redtrack.request.dto.ApplicationStatusDTO;
import com.dbs.redtrack.request.dto.ApplicationsDTO;
import com.dbs.redtrack.request.dto.BusinessUnitsDTO;
import com.dbs.redtrack.request.dto.CountryMasterDTO;

@Repository
public class AdminViewDAOImpl extends BaseDAOImpl implements AdminViewDAO {

	private static final Logger logger = Logger.getLogger(AdminViewDAOImpl.class);
	
	
	 
	@SuppressWarnings("unchecked")
	@Transactional
	public String findandRemoveApplicationStatusById(String appid) {
		logger.info("Inside AdminViewDAOImpl :: findApplicationStatusById() :: start");
		 
	//	Query query = getEm().createQuery(DAOConstants.QUERY_INCIDENT_BY_INCIDENT_ID);
		Query query = getEm().createQuery("SELECT a FROM ApplicationStatus a WHERE a.appId = :appid");
		query.setParameter("appid", appid);
		
		
		List<ApplicationStatus> results = query.getResultList();		
		System.out.println("List size of findandRemoveApplicationStatusById after query"+results.size());
		System.out.println("List size of findandRemoveApplicationStatusById after query"+results);
if(null != results && results.size() != 0){
			
			for(ApplicationStatus applicationStatus2:results){
			
				remove(applicationStatus2);
				logger.info("Inside AdminViewDAOImpl :: findApplicationStatusById() :: Record removed");
			}	
		}else{
			logger.info("Inside AdminViewDAOImpl :: findApplicationStatusById() :: No Record Found");	
		}

		
		/*
		if(null != results && results.size() != 0){
			logger.info("Inside AdminViewDAOImpl :: findApplicationStatusById() :: results found");	
			return  results;
		}else{
			logger.info("Inside AdminViewDAOImpl :: findApplicationStatusById() ::  results not  found ");	
		}*/
		
		return  null;
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public ApplicationStatus findApplicationStatusById(String appid) {
		logger.info("Inside AdminViewDAOImpl :: findApplicationStatusById() :: start");
		ApplicationStatus applicationStatus = null;
	//	Query query = getEm().createQuery(DAOConstants.QUERY_INCIDENT_BY_INCIDENT_ID);
		Query query = getEm().createQuery("SELECT a FROM ApplicationStatus a WHERE a.appId = :appid");
		query.setParameter("appid", appid);
		
		
		List<ApplicationStatus> results = query.getResultList();		
		System.out.println("List size of findApplicationStatusById after query"+results.size());
		System.out.println("List size of findApplicationStatusById after query"+results);

		if (null != results && results.size() != 0) {
			applicationStatus =  results.get(0);
			logger.info("Existing Entity from DB for ApplicationStatus: " +  results);	
		}
		
		logger.info("Inside AdminViewDAOImpl :: findApplicationStatusById() :: end");

		return  applicationStatus;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Applications findApplicationById(String appid) {
		logger.info("Inside AdminViewDAOImpl :: findApplicationById() :: start");
		Applications applications = null;
	//	Query query = getEm().createQuery(DAOConstants.QUERY_INCIDENT_BY_INCIDENT_ID);
		Query query = getEm().createQuery("SELECT a FROM Applications a WHERE a.appId = :appid");
		query.setParameter("appid", appid);
		
		
		List<Applications> results = query.getResultList();		
		System.out.println("List size of findApplicationById after query"+results.size());
		System.out.println("List size of findApplicationById after query"+results);

		if (null != results && results.size() != 0) {
			applications =  results.get(0);
			logger.info("Existing Entity from DB for ApplicationStatus: " +  results);	
		}
		
		logger.info("Inside AdminViewDAOImpl :: findApplicationById() :: end");

		return  applications;
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public ApplicationStatus findApplicationStatusByParam(String appid, String country, String businessUnit) throws RedTrackProcessingException {
		logger.info("Inside AdminViewDAOImpl :: findApplicationStatusByParam() :: start");
		ApplicationStatus applicationStatus = null;
	//	Query query = getEm().createQuery(DAOConstants.QUERY_INCIDENT_BY_INCIDENT_ID);
		Query query = getEm().createQuery("SELECT a FROM ApplicationStatus a WHERE a.appId = :appid and a.countryCode=:country and a.unitId=:businessUnit");
		query.setParameter("appid", appid);
		query.setParameter("country", country);
		query.setParameter("businessUnit", businessUnit);
		
		List<ApplicationStatus> results = query.getResultList();		
			
		if (null != results && results.size() != 0) {
			applicationStatus =  results.get(0);
			logger.info("Existing Entity from DB for ApplicationStatus: " +  results);	
		}
		
		logger.info("Inside AdminViewDAOImpl :: findApplicationStatusByParam() :: end");

		return  applicationStatus;
	}
	
	
	
		
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ApplicationsDTO> getApplicationList() throws RedTrackProcessingException {
		
		Query query = this.getEm().createQuery("select  a  from Applications a"); 
		List<Applications> results = query.getResultList();
		List<ApplicationsDTO> applicationList = new ArrayList<ApplicationsDTO>();
		
		for (Applications result : results) {
			if(result != null)
			{
				ApplicationsDTO applicationsDto=new ApplicationsDTO();
				applicationsDto.setAppId(result.getAppId());
				applicationsDto.setCountryCode(result.getCountryCode());
				applicationsDto.setAppActive(result.getAppActive());
				applicationsDto.setAppCode(result.getAppCode());
				applicationsDto.setApplicationName(result.getAppName());
					applicationList.add(applicationsDto);
			}
			}
				
		return applicationList;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ApplicationStatusDTO> getApplicationStatusList() {
		
		Query query = this.getEm().createQuery("select  a  from ApplicationStatus a where a.appId=:"); 
		List<ApplicationStatus> results = query.getResultList();
		List<ApplicationStatusDTO> applicationStatusList = new ArrayList<ApplicationStatusDTO>();
		
		for (ApplicationStatus result : results) {
			if(result != null)
			{
				ApplicationStatusDTO applicationStatusDTO=new ApplicationStatusDTO();
				applicationStatusDTO.setAppId(result.getAppId());
				applicationStatusDTO.setCountryCode(result.getCountryCode());
				applicationStatusDTO.setUnitId(result.getUnitId());
				applicationStatusDTO.setStatus(result.getStatus());
				applicationStatusList.add(applicationStatusDTO);
	
			}
			}
				
		return applicationStatusList;
	}
		
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ApplicationStatusDTO> getApplicationStatusListById(String appid123) {
		System.out.println("**************** In AdminviewDaoimp runs");
		
		System.out.println("selected getAppid"+appid123);
		Query query22 = getEm().createQuery("select b.appId, b.countryCode, b.unitId, b.status from ApplicationStatus b where b.appId=:applicaitonId");
		query22.setParameter("applicaitonId", appid123);
		
		List<Object[]> results22 = query22.getResultList();
		
		System.out.println("List size of country after query"+results22.size());
		System.out.println("List size of country after query"+results22);
		List<ApplicationStatusDTO> applicationStatusList = new ArrayList<ApplicationStatusDTO>();
		
		for (Object[] elements : results22) {
			if(elements != null)
			{
			
				ApplicationStatusDTO applicationStatusDTO=new ApplicationStatusDTO();
				applicationStatusDTO.setAppId(String.valueOf(elements[0]));
				applicationStatusDTO.setCountryCode(String.valueOf(elements[1]));
				System.out.println("country code ******************* "+String.valueOf(elements[1]));
				applicationStatusDTO.setUnitId(String.valueOf(elements[2]));
				applicationStatusDTO.setStatus(String.valueOf(elements[3]));
				applicationStatusList.add(applicationStatusDTO);
			}
			}
				
		return applicationStatusList;
	}
		
	
	 
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<BusinessUnitsDTO> getBusinessUnitList() throws RedTrackProcessingException {
		
		Query query = this.getEm().createQuery("select  a  from BusinessUnit a"); 
		List<BusinessUnit> results = query.getResultList();
		List<BusinessUnitsDTO> businessUnitList = new ArrayList<BusinessUnitsDTO>();
		
		for (BusinessUnit result : results) {
			if(result != null){
				BusinessUnitsDTO businessUnitsDTO=new BusinessUnitsDTO();
				businessUnitsDTO.setUnitId(result.getBusinessUnitID());
			businessUnitsDTO.setDescription(result.getBusinessUnitDesc());
			businessUnitList.add(businessUnitsDTO);
			}
		}
		return businessUnitList;
	}
		
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<CountryMasterDTO> getCountryList() throws RedTrackProcessingException {
		
		Query query = this.getEm().createQuery("select  a  from Country a"); 
		List<Country> results = query.getResultList();
		System.out.println("************in Countrylist  DAO"+results.size());
		List<CountryMasterDTO> countryList = new ArrayList<CountryMasterDTO>();
		
		for (Country result : results) {
			System.out.println("************in Countrylist  DAO"+result.getCountryDescription());
			if(result != null){
				System.out.println("************in Countrylist  DAO inside if");
				CountryMasterDTO countryMasterDTO=new CountryMasterDTO();
				countryMasterDTO.setCountryCode(result.getCountryCode());
			countryMasterDTO.setCountryDescription(result.getCountryDescription());
			//countryMasterDTO.setDateCreated(result.get());
			countryList.add(countryMasterDTO);
			
			}
		}
		return countryList;
	}

 
	@Override
	@Transactional
	public String submitapplicationFormBean(String appid, String country, String businessUnit) {
		
		System.out.println("DAO runs");
		System.out.println("getBusinessUnits list"+businessUnit);
		System.out.println("getCountries list"+country);
		System.out.println("selected getAppid "+appid);
		logger.info("Inside AdminViewDAOImpl :: submitapplicationFormBean() :: start");
		
	//	ApplicationStatus applicationStatusRemoveList =findApplicationStatusById(appid); 
		
		ApplicationStatus applicationStatus = findApplicationStatusByParam( appid,  country,  businessUnit);
		ApplicationStatus applicationStatus1=new ApplicationStatus();
		if (applicationStatus==null) {
			applicationStatus1.setAppId(appid);
			applicationStatus1.setCountryCode(country);
			applicationStatus1.setUnitId(businessUnit);
				save(applicationStatus1);
				logger.info("Inside AdminViewDAOImpl :: submitapplicationFormBean() :: submitapplicationFormBean saved");
				return "record Saved";
		} else {
			
			logger.info("Inside AdminViewDAOImpl :: submitapplicationFormBean() :: end");	
			return "record not Saved";
		}
			
	/*if(result!=null){
	logger.info("Inside IncidentDAOImpl :: submitForm() :: submitForm saved");	
	}else{
		logger.info("Inside IncidentDAOImpl :: submitForm() :: submitForm  not saved");	
	}*/	
	}
 
	@Override
	@Transactional
	public String addNewapplicationForm(String appId, String applicationName, String appCode, String appActive, String countryCode) {
		
		System.out.println("DAO runs");
		System.out.println("appId "+appId);
		System.out.println("applicationName "+applicationName);
		System.out.println("appActive "+appActive);
		System.out.println("countryCode "+countryCode);
		System.out.println("appCode "+appCode);
		logger.info("Inside AdminViewDAOImpl :: addNewapplicationForm() :: start");
		
		Applications applications =findApplicationById(appId); 
		
		
		Applications applicationStatus1=new Applications();
		if (applications==null) {
			applicationStatus1.setAppId(appId);
			applicationStatus1.setCountryCode(countryCode);
			applicationStatus1.setAppName(applicationName);
			applicationStatus1.setAppActive(appActive);
			applicationStatus1.setAppCode(appCode);
				save(applicationStatus1);
				logger.info("Inside AdminViewDAOImpl :: addNewapplicationForm() :: addNewapplicationForm saved");
				return "record Saved";
		} else {
			
			logger.info("Inside AdminViewDAOImpl :: addNewapplicationForm() :: end");	
			return "record not Saved";
		}
			
	/*if(result!=null){
	logger.info("Inside IncidentDAOImpl :: submitForm() :: submitForm saved");	
	}else{
		logger.info("Inside IncidentDAOImpl :: submitForm() :: submitForm  not saved");	
	}*/	
	}


	@Override
	@Transactional
	public String saveApplicaitonStatusForm(String appid, String country, String businessUnit, String status) {
		System.out.println("DAO saveApplicaitonStatusForm runs");
		/*System.out.println("getBusinessUnits list"+applicationStatusDTO.getUnitId());
		System.out.println("getCountries list"+applicationStatusDTO.getCountryCode());
		System.out.println("selected getAppid "+applicationStatusDTO.getAppId());
		System.out.println("selected status "+applicationStatusDTO.getStatus());*/
		logger.info("Inside AdminViewDAOImpl :: saveApplicaitonStatusForm() :: start");
		
	
		
		/*ApplicationStatus applicationStatus = findApplicationStatusByParam( appid,  country,  businessUnit);
		ApplicationStatus applicationStatus1=new ApplicationStatus();*/
		/*if (applicationStatus!=null) {
			applicationStatus1.setAppId(appid);
			applicationStatus1.setCountryCode(country);
			applicationStatus1.setUnitId(businessUnit);
			applicationStatus1.setStatus(status);
				save(applicationStatus1);
				logger.info("Inside AdminViewDAOImpl :: saveApplicaitonStatusForm() :: saveApplicaitonStatusForm saved");
				return "record Saved";
		} else {
			
			logger.info("Inside AdminViewDAOImpl :: saveApplicaitonStatusForm() :: end");	
			return "record not Saved";
		}
	}*/
		
		/* String appId=applicationStatusDTO.getAppId();
		  String unitId=applicationStatusDTO.getUnitId();
		  String status=applicationStatusDTO.getStatus();
		  String countryCode=applicationStatusDTO.getCountryCode();*/
		Query query = getEm().createQuery("update ApplicationStatus a set a.status=:status WHERE a.appId = :appid and a.countryCode=:country and a.unitId=:unitId");
		System.out.println("**************************** appid*****************"+appid);
		query.setParameter("appid", appid);
		
		query.setParameter("country", country);
		query.setParameter("unitId", businessUnit);
		query.setParameter("status", status);
		
		int results = query.executeUpdate();		
			
		if (results!=0) {
				logger.info("Inside AdminViewDAOImpl :: saveApplicaitonStatusForm() :: saveApplicaitonStatusForm saved");
				return "record Saved";
		} else {
			
			logger.info("Inside AdminViewDAOImpl :: saveApplicaitonStatusForm() :: end");	
			return "record not Saved";
		}
	}
	
	

	 

	
	
	


	
}
