package com.dbs.redtrack.integration.dao;
/**
 * 
 * @author Imran
 *
 */
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dbs.redtrack.request.dto.ApplicationStatusDTO;
import com.dbs.redtrack.request.dto.ApplicationsDTO;
import com.dbs.redtrack.request.dto.BusinessUnitsDTO;
import com.dbs.redtrack.request.dto.CountryMasterDTO;
 

@Repository
public interface AdminViewDAO {
	
	
	List<ApplicationsDTO> getApplicationList() ;


	public List<BusinessUnitsDTO> getBusinessUnitList();


	public List<CountryMasterDTO> getCountryList();


	public String submitapplicationFormBean(String appid, String country, String businessUnit);


	List<ApplicationStatusDTO> getApplicationStatusList();
	
	public String findandRemoveApplicationStatusById(String appid);


	List<ApplicationStatusDTO> getApplicationStatusListById(String appid);


	String addNewapplicationForm(String appId, String applicationName, String appCode, String appActive, String countryCode);

	String saveApplicaitonStatusForm(String appid, String country, String businessUnit, String status);
}
