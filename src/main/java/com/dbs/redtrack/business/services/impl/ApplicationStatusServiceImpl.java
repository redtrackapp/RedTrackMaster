/**
 * 
 */
package com.dbs.redtrack.business.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.redtrack.business.service.IApplicationStatusService;
import com.dbs.redtrack.dto.helper.AppCountry;
import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.integration.dao.IApplicationStatusDAO;
import com.dbs.redtrack.response.dto.ApplicationStatusApps;
import com.dbs.redtrack.response.dto.ApplicationStatusBusinessUnits;
import com.dbs.redtrack.response.dto.ApplicationStatusCountries;
import com.dbs.redtrack.response.dto.ApplicationStatusHelper;
import com.dbs.redtrack.util.property.AppConfig;

/**
 * @author IBM
 * 
 */
@Service
public class ApplicationStatusServiceImpl implements IApplicationStatusService {
	private static final Logger logger = Logger
			.getLogger(ApplicationStatusServiceImpl.class);

	@Autowired
	IApplicationStatusDAO applicationStatusDAO;

	@Autowired
	AppConfig appconfig;

	
	//changed by raymond
	@Override
	public String updateApplicationsStatus(List <AppCountry> appCountryList, String strStatus) throws RedTrackProcessingException {
		logger.info("ApplicationStatusServiceImpl : updateApplicationsStatus : start >> ");
		String msg = null;
		msg = applicationStatusDAO.updateApplicationsStatus(appCountryList,	strStatus);
		logger.info("ApplicationStatusServiceImpl : updateApplicationsStatus : End >> ");
		return msg;
	}
	
	/*
	 * @Override public List<ApplicationStatusHelper> getApplicationsStatus()
	 * throws RedTrackProcessingException {
	 * logger.info("ApplicationDetailsServiceImpl:getApplicationsStatus:start");
	 * 
	 * List<ApplicationStatus> appDbList = new ArrayList<ApplicationStatus>();
	 * List<ApplicationStatusHelper> appStatusHelperlist = new
	 * ArrayList<ApplicationStatusHelper>();
	 * 
	 * appDbList = applicationStatusDAO.getApplicationsStatus(); if (appDbList
	 * != null && appDbList.size() != 0) { for (ApplicationStatus
	 * applicationStatus : appDbList) { ApplicationStatusHelper
	 * applicationStatusHelper = new ApplicationStatusHelper();
	 * applicationStatusHelper.setAppId(applicationStatus.getAppId());
	 * applicationStatusHelper
	 * .setCountryCode(applicationStatus.getCountryCode());
	 * applicationStatusHelper.setBizunit_name(applicationStatus.getUnitId());
	 * applicationStatusHelper.setApptype_status(applicationStatus.getStatus());
	 * if(null!=applicationStatus.getApplications()){
	 * applicationStatusHelper.setApptype_name
	 * (applicationStatus.getApplications().getAppName()); }
	 * 
	 * appStatusHelperlist.add(applicationStatusHelper); } }
	 * 
	 * //List<ApplicationStatusBusinessUnits>
	 * appStatusBusResponseList=makeBusinessWiseList(appStatusHelperlist);
	 * //List<ApplicationStatusCountries> appStatusCountriesList
	 * =makeCountryWiseList(appStatusBusResponseList);
	 * 
	 * logger.info(
	 * "ApplicationDetailsServiceImpl:getApplicationsStatus:appStatusCountriesList "
	 * ); return appStatusHelperlist; }
	 */

	@Override
	public List<ApplicationStatusCountries> getApplicationsStatus()
			throws RedTrackProcessingException {
		logger.info("ApplicationDetailsServiceImpl:getApplicationsStatus:start");
 
		List<ApplicationStatusHelper> appStatusHelperlist = new ArrayList<ApplicationStatusHelper>();
		
		appStatusHelperlist=applicationStatusDAO.getApplicationsStatus();

		List<ApplicationStatusCountries> appStatusCountriesList	 = getApplicationsStatusNew(appStatusHelperlist);

		logger.info("ApplicationDetailsServiceImpl:getApplicationsStatus:appStatusCountriesList "
				+ appStatusHelperlist.size());
		return appStatusCountriesList;
	}
	
	
	/*@Override
	public List<ApplicationStatusCountries> getApplicationsStatus()
			throws RedTrackProcessingException {
		logger.info("ApplicationDetailsServiceImpl:getApplicationsStatus:start");

		List<ApplicationStatus> appDbList = new ArrayList<ApplicationStatus>();
		List<ApplicationStatusHelper> appStatusHelperlist = new ArrayList<ApplicationStatusHelper>();

		appDbList = applicationStatusDAO.getApplicationsStatus();
		logger.info("ApplicationStatusServiceImpl.getApplicationsStatus()::DAO LIST SIZE::" + appDbList.size());
		if (appDbList != null && appDbList.size() != 0) {
			for (ApplicationStatus applicationStatus : appDbList) {
				ApplicationStatusHelper applicationStatusHelper = new ApplicationStatusHelper();
				applicationStatusHelper.setAppId(applicationStatus.getAppId());
				applicationStatusHelper.setCountryCode(applicationStatus
						.getCountryCode());
				applicationStatusHelper.setBizunit_name(applicationStatus
						.getUnitId());
				applicationStatusHelper.setApptype_status(applicationStatus
						.getStatus());
				if (null != applicationStatus.getApplications()) {
					applicationStatusHelper.setApptype_name(applicationStatus
							.getApplications().getAppName());
				}
				logger.info("CountryCode:: " + applicationStatusHelper
						.getCountryCode() + " BusniessUnit:: " + applicationStatusHelper.getBizunit_name());

				appStatusHelperlist.add(applicationStatusHelper);
			}
		}

		
		List<ApplicationStatusCountries> appStatusCountriesList	 = getApplicationsStatusNew(appStatusHelperlist);

		logger.info("ApplicationDetailsServiceImpl:getApplicationsStatus:appStatusCountriesList "
				+ appStatusHelperlist.size());
		return appStatusCountriesList;
	}
	*/

	// Make JSON Opbject
	public List<ApplicationStatusCountries> getApplicationsStatusNew(
			List<ApplicationStatusHelper> appStatusHelperlist)
			throws RedTrackProcessingException {
		
		logger.info("ApplicationDetailsServiceImpl:getApplicationsStatusNew:start >>>>>>>>>>>>>>> ");
		
		List<ApplicationStatusCountries> applicationStatusCountriesList = new ArrayList<ApplicationStatusCountries>();
		List<ApplicationStatusBusinessUnits> businessUnitsList = null;
		List<ApplicationStatusApps> applicationStatusAppsList = null; 
		
		ApplicationStatusCountries applicationStatusCountriesObj = null;
		ApplicationStatusBusinessUnits applicationStatusBusinessUnits = null;
		ApplicationStatusApps applicationStatusApps = null;

		HashMap<String, ApplicationStatusHelper> mapperCountry = makeCountryMap(appStatusHelperlist);
		HashMap<String, ApplicationStatusHelper> mapperBusinessUnit = makeBusinessUnitMap(appStatusHelperlist);
		HashMap<String, ApplicationStatusHelper> mapperApplications = makeApplicationsMap(appStatusHelperlist);
		//Loop for Country
		for (Entry<String, ApplicationStatusHelper> entryCountry : mapperCountry
				.entrySet()) {
			
			applicationStatusCountriesObj = new ApplicationStatusCountries();

			applicationStatusCountriesObj.setCountry_name(entryCountry
					.getValue().getCountryCode());
			applicationStatusCountriesObj.setCountry_status(entryCountry
					.getValue().getApptype_status());
			
			//logger.info("ApplicationStatusServiceImpl.getApplicationsStatusNew()::setCountry_name " + entryCountry
				//			.getValue().getCountryCode() + "::status::" + entryCountry
					//		.getValue().getApptype_status());
			String businessKey = "";
			businessUnitsList = new ArrayList<ApplicationStatusBusinessUnits>();
			for (Entry<String, ApplicationStatusHelper> entryBusiness : mapperBusinessUnit
					.entrySet()) {
					businessKey = entryBusiness.getKey();
				if (businessKey.startsWith(entryCountry
					.getValue().getCountryCode())) {
					
					applicationStatusBusinessUnits = new ApplicationStatusBusinessUnits();
					applicationStatusBusinessUnits
							.setBizunit_name(entryBusiness.getValue()
									.getBizunit_name());
					//Assign country status to business unit
					applicationStatusBusinessUnits
							.setBizunit_status(entryBusiness
									.getValue().getApptype_status());
					applicationStatusBusinessUnits.setCountry_code(entryBusiness
									.getValue().getCountryCode());
					//logger.info("ApplicationStatusServiceImpl.getApplicationsStatusNew()::businessKey:: " + businessKey + "countryCode:: " + entryCountry
						//	.getValue().getCountryCode() + "::AppStatus::" + entryCountry
							//.getValue().getApptype_status());
					
					applicationStatusAppsList = new ArrayList<ApplicationStatusApps>();
					for (Entry<String, ApplicationStatusHelper> entryApps : mapperApplications
							.entrySet()) {
						String entryApp = entryApps.getKey();
						//logger.info("entryApp:: " + entryApp + " businessKey1:: " + businessKey);
						if(entryApp.startsWith(businessKey)){
							applicationStatusApps = new ApplicationStatusApps();
							applicationStatusApps.setAppId(entryApps.getValue().getAppId());
							applicationStatusApps.setApptype_name(entryApps.getValue().getApptype_name());
							applicationStatusApps.setApptype_status(entryApps.getValue().getApptype_status());
							//added appComments : 17.07.17
							applicationStatusApps.setAppComments(entryApps.getValue().getAppComments());
							
							applicationStatusAppsList.add(applicationStatusApps);
						}
						
					}//App For
					businessUnitsList.add(applicationStatusBusinessUnits);
					applicationStatusBusinessUnits.setApptype_names(applicationStatusAppsList);
					
				}//Businee For
//				logger.info("businessKey:: " + businessKey + "::applicationStatusAppsList:: " + applicationStatusAppsList);

			}
			logger.info("businessUnitsList:: " + businessUnitsList.size());// + ":: businessUnitsList Object " + businessUnitsList);
			applicationStatusCountriesObj.setBizunit_names(businessUnitsList);
			//businessUnitsList.clear();
		    applicationStatusCountriesList.add(applicationStatusCountriesObj);	
		}//Country For
		
		
		logger.info("ApplicationDetailsServiceImpl:getApplicationsStatusNew:END >>>>>>>>>>>>>>> FINAL LIST SIZE>>>  " + applicationStatusCountriesList.size());
		return applicationStatusCountriesList;
	}

	// Country Map
	public HashMap<String, ApplicationStatusHelper> makeCountryMap(
			List<ApplicationStatusHelper> appStatusHelperlist) {

		HashMap<String, ApplicationStatusHelper> mapperCountry = new HashMap<String, ApplicationStatusHelper>();
		for (ApplicationStatusHelper applicationStatusHelper : appStatusHelperlist) {
			// JSONObject jsonObj = new JSONObject();
			mapperCountry.put(applicationStatusHelper.getCountryCode(),
					applicationStatusHelper);
		}

		return mapperCountry;

	}

	// Business Unit Map
	public HashMap<String, ApplicationStatusHelper> makeBusinessUnitMap(
			List<ApplicationStatusHelper> appStatusHelperlist) {

		HashMap<String, ApplicationStatusHelper> mapperBusinessUnit = new HashMap<String, ApplicationStatusHelper>();

		for (ApplicationStatusHelper applicationStatusHelper : appStatusHelperlist) {
			
			// JSONObject jsonObj = new JSONObject();
			
		
			mapperBusinessUnit.put(applicationStatusHelper.getCountryCode()
					+ "_" + applicationStatusHelper.getBizunit_id(),
					applicationStatusHelper);
			
		}

	
		
		return mapperBusinessUnit;

	}

	// Business Unit Map
	public HashMap<String, ApplicationStatusHelper> makeApplicationsMap(
			List<ApplicationStatusHelper> appStatusHelperlist) {

		HashMap<String, ApplicationStatusHelper> mapperApplications = new HashMap<String, ApplicationStatusHelper>();

		for (ApplicationStatusHelper applicationStatusHelper : appStatusHelperlist) {
			// JSONObject jsonObj = new JSONObject();
			mapperApplications.put(applicationStatusHelper.getCountryCode()
					+ "_" + applicationStatusHelper.getBizunit_id() + "_"
					+ applicationStatusHelper.getAppId(),
					applicationStatusHelper);
		}

		return mapperApplications;

	}
 
	@Override
	public String getApplicationRemarks(String applicationID) throws RedTrackProcessingException {
		
		return null;
	}

	/*
	 * public List<ApplicationStatusBusinessUnits> makeBusinessWiseList(
	 * List<ApplicationStatusHelper> appResponseList) { logger.info(
	 * "ApplicationDetailsServiceImpl:makeBusinessWiseList:end>>>>>>>>>>>>>>>>> >> "
	 * );
	 * 
	 * List<ApplicationStatusBusinessUnits> applicationStatusBusinessUnitsList =
	 * new ArrayList<ApplicationStatusBusinessUnits>();
	 * ApplicationStatusBusinessUnits businessUnits = new
	 * ApplicationStatusBusinessUnits();
	 * 
	 * HashMap<String, ApplicationStatusBusinessUnits> mapper = new
	 * HashMap<String, ApplicationStatusBusinessUnits>();
	 * 
	 * for (ApplicationStatusHelper applicationStatusHelper : appResponseList) {
	 * 
	 * if (!mapper.containsKey(applicationStatusHelper.getBizunit_name() + "_"+
	 * applicationStatusHelper.getCountryCode())) {
	 * logger.info("mapper Unit ID : >>>>>>>>>>>>>>>>>>>>>>> " +
	 * applicationStatusHelper.getBizunit_name() + "_"+
	 * applicationStatusHelper.getCountryCode());
	 * mapper.put(applicationStatusHelper.getBizunit_name(), new
	 * ApplicationStatusBusinessUnits(applicationStatusHelper.getBizunit_name(),
	 * applicationStatusHelper
	 * .getApptype_status(),applicationStatusHelper.getCountryCode
	 * (),applicationStatusHelper)); } else {
	 * 
	 * businessUnits = mapper.get(applicationStatusHelper.getBizunit_name());
	 * businessUnits.getApptype_names().add(applicationStatusHelper); }
	 * applicationStatusBusinessUnitsList = new
	 * ArrayList<ApplicationStatusBusinessUnits>(mapper.values()); }
	 * 
	 * logger.info(
	 * "ApplicationDetailsServiceImpl:makeBusinessWiseList:end >>>>>>>>>>>> >> "
	 * +applicationStatusBusinessUnitsList.size()); return
	 * applicationStatusBusinessUnitsList; }
	 * 
	 * public List<ApplicationStatusCountries>
	 * makeCountryWiseList(List<ApplicationStatusBusinessUnits>
	 * applicationStatusHelperList) {
	 * 
	 * logger.info(
	 * "ApplicationDetailsServiceImpl: makeCountryWiseList: start >>>>>>>>>>>>>>>>> >> "
	 * );
	 * 
	 * List<ApplicationStatusCountries> applicationStatusCountriesList = new
	 * ArrayList<ApplicationStatusCountries>(); ApplicationStatusCountries
	 * applicationStatusCountries = new ApplicationStatusCountries();
	 * 
	 * HashMap<String, ApplicationStatusCountries> mapperCountry = new
	 * HashMap<String, ApplicationStatusCountries>();
	 * 
	 * for (ApplicationStatusBusinessUnits applicationStatusBusinessUnits :
	 * applicationStatusHelperList) {
	 * 
	 * if
	 * (!mapperCountry.containsKey(applicationStatusBusinessUnits.getCountry_code
	 * ())) {
	 * 
	 * logger.info("Country_code : >>>>>>>>>>>>>>>>>>>>>>> " +
	 * applicationStatusBusinessUnits.getCountry_code());
	 * mapperCountry.put(applicationStatusBusinessUnits.getCountry_code(), new
	 * ApplicationStatusCountries
	 * (applicationStatusBusinessUnits.getCountry_code(),
	 * applicationStatusBusinessUnits
	 * .getBizunit_status(),applicationStatusBusinessUnits));
	 * 
	 * logger.info("mapperCountry.values() IF 222222222 : >>>>>>>>>>>>>>>>>>>>>>> "
	 * + mapperCountry.values());
	 * 
	 * } else {
	 * 
	 * applicationStatusCountries =
	 * mapperCountry.get(applicationStatusBusinessUnits.getCountry_code());
	 * 
	 * logger.info("Business UNit Object ELSE : >>>>>>>>>>>>>>>>>>>>>>> " +
	 * applicationStatusBusinessUnits);
	 * 
	 * applicationStatusCountries.getBizunit_names().add(
	 * applicationStatusBusinessUnits); }
	 * 
	 * applicationStatusCountriesList = new
	 * ArrayList<ApplicationStatusCountries>(mapperCountry.values()); }
	 * 
	 * logger.info(
	 * "ApplicationDetailsServiceImpl : makeCountryWiseList: end >>>>>>>>>>>> >> "
	 * + applicationStatusCountriesList.size() );
	 * 
	 * return applicationStatusCountriesList; }
	 */
}
