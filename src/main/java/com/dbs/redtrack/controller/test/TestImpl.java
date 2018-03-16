/**
 * 
 */
package com.dbs.redtrack.controller.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.redtrack.business.service.ChatMessageService;
import com.dbs.redtrack.constants.BusinessConstants;
import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.integration.dao.ChatMessageDAO;
import com.dbs.redtrack.integration.dao.UserRegistrationDAO;
import com.dbs.redtrack.jpa.entity.ChatMessage;
import com.dbs.redtrack.jpa.entity.User;
import com.dbs.redtrack.request.dto.SaveChatRequestDTO;
import com.dbs.redtrack.response.dto.ChatMessageHelper;
import com.dbs.redtrack.response.dto.ChatMessageResponseDTO;
import com.dbs.redtrack.response.dto.GetChatMessageResponseDTO;
import com.dbs.redtrack.util.property.AppConfig;
import com.dbs.redtrack.web.utilities.DateTimeUtil;

@Service
public class TestImpl  {
	private static final Logger logger = Logger.getLogger(TestImpl.class);

	@Autowired
	ChatMessageDAO chatMessageDAO;
	@Autowired
	AppConfig appconfig;
	@Autowired
	UserRegistrationDAO userRegistrationDAO;
	 
	public void saveChatMessage( ) throws RedTrackProcessingException {
		logger.info("  : saveChatMessage>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>raymond:"+ appconfig.getProperty("WHITE_LISTING_SWITCH"));
			logger.info(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>after");
 
	} 
	  
}
