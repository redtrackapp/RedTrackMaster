package com.dbs.redtrack.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dbs.redtrack.business.service.IApplicationDetailsService;
import com.dbs.redtrack.constants.BusinessConstants;
import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.response.dto.ApplicationsDetailsResponseDTO;
import com.dbs.redtrack.util.property.AppConfig;

/**
 * @author IBM Controller for Application Status
 * 
 */
@Controller
public class ApplicationDetailsController {
	private static final Logger logger = Logger
			.getLogger(ApplicationDetailsController.class);

	@Autowired
	IApplicationDetailsService iApplicationDetailsService;

	@Autowired
	AppConfig appconfig;

	@RequestMapping(value = BusinessConstants.APPLICATION_DETAILS, method = RequestMethod.GET)
	public @ResponseBody
	List<ApplicationsDetailsResponseDTO> getApplicationDetails()
			throws RedTrackProcessingException {
		logger.info("start ApplicationDetailsController :: getApplicationDetails :: start");

		// ApplicationsDetailsResponseDTO responsedto = new
		// ApplicationsDetailsResponseDTO();
		List<ApplicationsDetailsResponseDTO> applicationsDetailsResponseDTOList = iApplicationDetailsService
				.getApplicationDetails();

		logger.info("End ApplicationDetailsController :: responsedto :: end >>> "
				+ applicationsDetailsResponseDTOList);
		return applicationsDetailsResponseDTOList;

	}
}
