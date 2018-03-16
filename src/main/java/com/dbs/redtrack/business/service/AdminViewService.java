/**
 * 
 */
package com.dbs.redtrack.business.service;

import java.util.List;

import com.dbs.redtrack.request.dto.ApplicationStatusDTO;
import com.dbs.redtrack.request.dto.ApplicationsDTO;
import com.dbs.redtrack.request.dto.BusinessUnitsDTO;
import com.dbs.redtrack.request.dto.CountryMasterDTO;

public interface AdminViewService {
	
	List<ApplicationsDTO> getApplicationList() ;


	public List<BusinessUnitsDTO> getBusinessUnitList();


	public List<CountryMasterDTO> getCountryList();


	public String submitapplicationFormBean(String appid, String country, String businessUnit);
	
	List<ApplicationStatusDTO> getApplicationStatusList();
	
	List<ApplicationStatusDTO> getApplicationStatusListById(String appid);
	
	public String findandRemoveApplicationStatusById(String appid);


	String addNewapplicationForm(String appId, String applicationName, String appCode, String appActive, String countryCode);


	String saveApplicaitonStatusForm(String appid, String country, String businessUnit, String status);
}
