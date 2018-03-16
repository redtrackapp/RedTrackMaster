package com.dbs.redtrack.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dbs.redtrack.business.service.ChatMessageService;
import com.dbs.redtrack.constants.BusinessConstants;
import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.request.dto.SaveChatRequestDTO;
import com.dbs.redtrack.response.dto.ChatMessageResponseDTO;
import com.dbs.redtrack.response.dto.GetChatMessageResponseDTO;
import com.dbs.redtrack.util.property.AppConfig;
import com.dbs.redtrack.web.utilities.RequestValidator;


@Controller
public class ChatMessageController { 
	
	private static final Logger logger = Logger.getLogger(ChatMessageController.class);
	 
	@Autowired
	ChatMessageService chatMessageService;

	@Autowired 
	AppConfig appconfig;
	
	 
	@RequestMapping(value = BusinessConstants.SAVE_CHAT_MESSAGE, method = RequestMethod.POST)
 	public @ResponseBody ChatMessageResponseDTO saveChatMessage(@RequestParam("incidentid") String incidentid,
 					@RequestParam("userotpid") String userotpid, @RequestParam("message") String message) 
						throws RedTrackProcessingException {
 	    
		SaveChatRequestDTO saveChatRequestDTO = new SaveChatRequestDTO(incidentid, userotpid, message);
		logger.info("start ChatMessageController :: saveChat :: start"); 		
	 			 
		if(!RequestValidator.validateSaveChatMessage(saveChatRequestDTO)) {
			throw new RedTrackProcessingException(BusinessConstants.ERROR_00001, BusinessConstants.INVALID_REQUEST); 	
		}
 
		ChatMessageResponseDTO chatMessageResponseDTO = chatMessageService.saveChatMessage(saveChatRequestDTO);
 		 
		
		logger.info("start ChatMessageController :: saveChat :: end");
		return chatMessageResponseDTO;		 
	}
	
	
	@RequestMapping(value = BusinessConstants.GET_ALL_CHAT_MESSAGES, method = RequestMethod.POST)
 	public @ResponseBody GetChatMessageResponseDTO getChatMessages(@RequestParam("incidentid") String incidentid) 
						throws RedTrackProcessingException {
			
		logger.info("start ChatMessageController :: getChatMessages :: start"); 		
	 			 
		if(!RequestValidator.validateGetChatMessage(incidentid)) {
			throw new RedTrackProcessingException(BusinessConstants.ERROR_00001, BusinessConstants.INVALID_REQUEST); 	
		}
 
		GetChatMessageResponseDTO getChatMessageResponseDTO = chatMessageService.getMessages(incidentid);
 		 
		
		logger.info("start ChatMessageController :: getChatMessages :: end");
		return getChatMessageResponseDTO;		 
	} 
	
	
}
