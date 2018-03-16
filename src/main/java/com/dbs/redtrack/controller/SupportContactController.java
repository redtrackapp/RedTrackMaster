package com.dbs.redtrack.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dbs.redtrack.business.service.SupportContactService;
import com.dbs.redtrack.constants.BusinessConstants;
import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.response.dto.SupportContactResponseDTO;
import com.dbs.redtrack.util.property.AppConfig;


@Controller
public class SupportContactController { 
	
	private static final Logger logger = Logger.getLogger(SupportContactController.class);
	 
	@Autowired
	SupportContactService supportContactService;

	@Autowired 
	AppConfig appconfig;
	  
	@RequestMapping(value = BusinessConstants.GET_SUPPORT_CONTACT_LIST, method = RequestMethod.GET)
 	public @ResponseBody SupportContactResponseDTO getSupportContactList(@RequestParam("userotpid") String userotpid) 
						throws RedTrackProcessingException {
			
		logger.info("start SupportContactController :: getSupportContactList :: start"); 		
	 			 
//		if(!RequestValidator.validateGetChatMessage(incidentid)) {
//			throw new RedTrackProcessingException(BusinessConstants.ERROR_00001, BusinessConstants.INVALID_REQUEST); 	
//		}
 
		SupportContactResponseDTO supportContactResponseDTO = supportContactService.getSupportContactList();
 		 
		
		logger.info("start SupportContactController :: getSupportContactList :: end");
		return supportContactResponseDTO;		 
	} 
	
	
}
