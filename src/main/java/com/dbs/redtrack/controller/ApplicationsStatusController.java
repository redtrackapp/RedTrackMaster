/**
 * 
 */
package com.dbs.redtrack.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dbs.redtrack.business.service.IApplicationStatusService;
import com.dbs.redtrack.constants.BusinessConstants;
import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.response.dto.ApplicationStatusCountries;
import com.dbs.redtrack.response.dto.ApplicationStatusFinalResponseDTO;
import com.dbs.redtrack.response.dto.ApplicationStatusHelper;
import com.dbs.redtrack.util.property.AppConfig;

/**
 * @author ADMINIBM
 * 
 */
@Controller
public class ApplicationsStatusController {
	private static final Logger logger = Logger
			.getLogger(ApplicationsStatusController.class);

	@Autowired
	IApplicationStatusService applicationStatusService;

	@Autowired
	AppConfig appconfig;

//	@RequestMapping(value = BusinessConstants.APPLICATION_STATUS_UPDATE, method = RequestMethod.POST)
//	public @ResponseBody
//	String updateApplicationStatus() throws RedTrackProcessingException {
//		logger.info("start ApplicationsStatusController :: updateApplicationStatus :: start>> ");
//		List<String> appStrList = new ArrayList<String>();
//		appStrList.add("CN_IB_1001");
//		appStrList.add("CN_IB_1002");
//		String strStatus = "RED";
//		String msg = applicationStatusService.updateApplicationsStatus(
//				appStrList, strStatus);
//
//		logger.info("End ApplicationsStatusController :: Message :: end >>> "
//				+ msg);
//		return msg;
//
//	}

	@RequestMapping(value = BusinessConstants.GET_APPLICATION_STATUS, method = RequestMethod.GET)
	public @ResponseBody
	ApplicationStatusFinalResponseDTO getApplicationsStatus()
			throws RedTrackProcessingException {
		logger.info("start ApplicationsStatusController :: getApplicationsStatus :: start>> ");
		ApplicationStatusFinalResponseDTO applicationStatusFinalResponseDTO = new ApplicationStatusFinalResponseDTO();
		List<ApplicationStatusCountries> appStatusCountriesList = new ArrayList<>();
		appStatusCountriesList = applicationStatusService.getApplicationsStatus();
		applicationStatusFinalResponseDTO.setCountry_names(appStatusCountriesList);
		logger.info("start ApplicationsStatusController :: getApplicationsStatus :: start>> ");
		return applicationStatusFinalResponseDTO;

	}

}
