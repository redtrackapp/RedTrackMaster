/**
 * 
 */
package com.dbs.redtrack.business.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.redtrack.business.service.IApplicationDetailsService;
import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.integration.dao.IApplicationStatusDAO;
import com.dbs.redtrack.integration.dao.IApplicationsDAO;
import com.dbs.redtrack.jpa.entity.ApplicationStatus;
import com.dbs.redtrack.jpa.entity.Applications;
import com.dbs.redtrack.response.dto.ApplicationDetailsHelper;
import com.dbs.redtrack.response.dto.ApplicationsDetailsResponseDTO;
import com.dbs.redtrack.util.property.AppConfig;

/**
 * @author IBM
 * 
 *         Service Implementation to get details of application like
 *         appId,appCode and appStatus etc.
 * 
 */
@Service
public class ApplicationDetailsServiceImpl implements
		IApplicationDetailsService {

	private static final Logger logger = Logger
			.getLogger(ApplicationDetailsServiceImpl.class);

	@Autowired
	IApplicationsDAO applicationsDAO;

	@Autowired
	AppConfig appconfig;

	@Autowired
	IApplicationStatusDAO applicationStatusDAO;
	
	@Override
	public List<ApplicationsDetailsResponseDTO> getApplicationDetails()
			throws RedTrackProcessingException {
		logger.info("ApplicationDetailsServiceImpl:getApplicationDetails:start");

//		List<Applications> appDbList = new ArrayList<Applications>();
		List<ApplicationDetailsHelper> appResponseList = new ArrayList<ApplicationDetailsHelper>();
		ApplicationsDetailsResponseDTO detailsResponseDTO = new ApplicationsDetailsResponseDTO();
		//appDbList = applicationsDAO.getApplicationDetails();
//		if (appDbList != null && appDbList.size() != 0) {
//			for (Applications applications : appDbList) {
//				ApplicationDetailsHelper detailsHelper = new ApplicationDetailsHelper();
//				detailsHelper.setAppId(applications.getAppId());
//				detailsHelper.setAppName(applications.getAppName());
//				detailsHelper.setAppCode(applications.getAppCode());
//				detailsHelper.setAppActive(applications.getAppActive());
//				detailsHelper.setCountryCode(applications.getCountryCode());
//				appResponseList.add(detailsHelper);
//
//			}
//			detailsResponseDTO.setAppList(appResponseList);
//
//		}
		
//		List<ApplicationStatus> appDbList  = applicationStatusDAO.getApplicationsStatus();
//		
//		if (appDbList != null && appDbList.size() != 0) {
//			for (ApplicationStatus appstat : appDbList) {
//				ApplicationDetailsHelper detailsHelper = new ApplicationDetailsHelper();
//				detailsHelper.setAppId(appstat.getAppId());
//				detailsHelper.setAppName(appstat.getApplications().getAppName());
//				detailsHelper.setAppCode(appstat.getApplications().getAppCode());
//				detailsHelper.setAppActive(appstat.getApplications().getAppActive());
//				detailsHelper.setCountryCode(appstat.getCountryCode());
//				appResponseList.add(detailsHelper);
//
//			}
//			detailsResponseDTO.setAppList(appResponseList);
//		}		
		appResponseList = applicationStatusDAO.getApplicationDetails();
		detailsResponseDTO.setAppList(appResponseList);
		
		
		List<ApplicationsDetailsResponseDTO> applicationsDetailsResponseDTOList = makeCountryWiseList(detailsResponseDTO.getAppList());
		logger.info("ApplicationDetailsServiceImpl:getApplicationDetails:appResponseList");
		return applicationsDetailsResponseDTOList;
	}

	public List<ApplicationsDetailsResponseDTO> makeCountryWiseList(
			List<ApplicationDetailsHelper> appResponseList) {
		logger.info("ApplicationDetailsServiceImpl:makeCountryWiseList:end>>>>>>>>>>>>>>>>> >> ");
		List<ApplicationsDetailsResponseDTO> applicationsDetailsResponseDTOList = new ArrayList<ApplicationsDetailsResponseDTO>();
		ApplicationsDetailsResponseDTO applicationsDetailsResponseDTO = new ApplicationsDetailsResponseDTO();

		HashMap<String, ApplicationsDetailsResponseDTO> mapper = new HashMap<String, ApplicationsDetailsResponseDTO>();
		for (ApplicationDetailsHelper applicationDetailsHelper : appResponseList) {

			if (!mapper.containsKey(applicationDetailsHelper.getCountryCode())) {
				logger.info("countryCode: >>>>>>>>>>>>>>>>>>>>>>> "	+ applicationDetailsHelper.getCountryCode());
				mapper.put(applicationDetailsHelper.getCountryCode(), new ApplicationsDetailsResponseDTO(applicationDetailsHelper.getCountryCode(),	applicationDetailsHelper));
			} else {
				applicationsDetailsResponseDTO = mapper.get(applicationDetailsHelper.getCountryCode());
				applicationsDetailsResponseDTO.getAppList().add(applicationDetailsHelper);
			}
			applicationsDetailsResponseDTOList = new ArrayList<ApplicationsDetailsResponseDTO>(
					mapper.values());
		}

		logger.info("ApplicationDetailsServiceImpl:makeCountryWiseList:end>>>>>>>>>>>> >> ");
		return applicationsDetailsResponseDTOList;
	}

}
