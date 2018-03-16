/**
 * 
 */
package com.dbs.redtrack.business.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbs.redtrack.business.service.AdminViewService;
import com.dbs.redtrack.integration.dao.AdminViewDAO;
import com.dbs.redtrack.request.dto.ApplicationStatusDTO;
import com.dbs.redtrack.request.dto.ApplicationsDTO;
import com.dbs.redtrack.request.dto.BusinessUnitsDTO;
import com.dbs.redtrack.request.dto.CountryMasterDTO;

@Service
public class AdminViewServiceImpl implements AdminViewService {
	private static final Logger logger = Logger.getLogger(AdminViewServiceImpl.class);

	@Autowired
	AdminViewDAO adminViewDAO;

	@Override
	@Transactional
	public List<ApplicationsDTO> getApplicationList() {
		return this.adminViewDAO.getApplicationList();
	}
	
	@Override
	@Transactional
	public List<BusinessUnitsDTO> getBusinessUnitList() {
		
		return this.adminViewDAO.getBusinessUnitList();
	}

	@Override
	@Transactional
	public List<CountryMasterDTO> getCountryList() {
		
		return this.adminViewDAO.getCountryList();
	}

	@Override
	@Transactional
	public String submitapplicationFormBean(String appid, String country, String businessUnit) {
		
		  String form=adminViewDAO.submitapplicationFormBean(appid,country,businessUnit);
		return form;
	}

	@Override
	public List<ApplicationStatusDTO> getApplicationStatusList() {
		return this.adminViewDAO.getApplicationStatusList();
	}

	@Override
	public String findandRemoveApplicationStatusById(String appid) {
		return this.adminViewDAO.findandRemoveApplicationStatusById(appid);
	}

	@Override
	public List<ApplicationStatusDTO> getApplicationStatusListById(String appid) {
		return this.adminViewDAO.getApplicationStatusListById(appid);
	}

	@Override
	public String addNewapplicationForm(String appId, String applicationName, String appCode, String appActive, String countryCode) {
		return this.adminViewDAO.addNewapplicationForm(appId,applicationName,appCode,appActive,countryCode);
	}

	@Override
	public String saveApplicaitonStatusForm(String appid, String country, String businessUnit,
			String status) {
		return this.adminViewDAO.saveApplicaitonStatusForm(appid,country,businessUnit,status);
	}



	
	
	 
	
}
