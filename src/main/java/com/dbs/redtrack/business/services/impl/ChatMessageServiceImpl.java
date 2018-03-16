/**
 * 
 */
package com.dbs.redtrack.business.services.impl;

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
import com.dbs.redtrack.web.utilities.DateTimeUtil;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {
	private static final Logger logger = Logger.getLogger(ChatMessageServiceImpl.class);

	@Autowired
	ChatMessageDAO chatMessageDAO;
	
	@Autowired
	UserRegistrationDAO userRegistrationDAO;
	
	@Override
	public ChatMessageResponseDTO saveChatMessage(SaveChatRequestDTO chatDTO) throws RedTrackProcessingException {
		logger.info("ChatMessageServiceImpl : saveChatMessage");
		
		boolean  isValidUser = findUserByID(chatDTO.getUserotpid());
		logger.info("IncidentServiceImpl :createIncident:Start isValidUser(" + isValidUser+")");	

		
		Date dte = new Date();
		long milliSeconds = dte.getTime();
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setID(milliSeconds);
		chatMessage.setDateCreated(new Date());
		chatMessage.setMessage(chatDTO.getMessage());
		chatMessage.setUserID(Long.parseLong(chatDTO.getUserotpid()));
		//changed 19.07.17 updated incidentid to be manually enteredd by user
		chatMessage.setIncidentID(Long.parseLong(chatDTO.getIncidentid()));
		//chatMessage.setIncidentID(chatDTO.getIncidentid());
		chatMessageDAO.saveChatMessage(chatMessage);
		ChatMessageResponseDTO chatResponse = new ChatMessageResponseDTO();
		chatResponse.setMessage("Successfully Saved chat");
		chatResponse.setResponsecode("0000");
		logger.info("ChatMessageServiceImpl : saveChatMessage");
		return chatResponse;
		 
	} 
	 
	@Override
	public GetChatMessageResponseDTO getMessages(String incidentID) throws RedTrackProcessingException {
		logger.info("ChatMessageServiceImpl : getMessages");

		String strDate = null;
		GetChatMessageResponseDTO chatResponse = new GetChatMessageResponseDTO();
		List<ChatMessageHelper> chatList = new ArrayList<ChatMessageHelper>();
		List<ChatMessage> list= chatMessageDAO.getChatMessages(incidentID);
		
		if (null!=list) {
			ChatMessageHelper chatMessage = null;
			for(ChatMessage chat :list){//String.valueOf(chat.getUserID())
				strDate = DateTimeUtil.convertTimestampToString(chat.getDateCreated());
				
				chatMessage = new ChatMessageHelper(String.valueOf(chat.getIncidentID()),chat.getUser().getFullName(), chat.getMessage(), strDate);
				chatList.add(chatMessage);
			}
		}
		chatResponse.setChatMessages(chatList);
		logger.info("ChatMessageServiceImpl : getMessages");
		return chatResponse;
		 
	} 
	
	/*
	 * comments/changes: This function is used to check for the role of the user and to check if User record is still active
	 * 					 This function is called before creating/updating Incident
	 * 
	 * */
	private boolean findUserByID(String userID) throws RedTrackProcessingException {
		
		logger.info("IncidentServiceImpl : findUserByID : start");
		boolean isValid = false;
		User user  = userRegistrationDAO.findUserByID(userID);
		if (null != user) { 
			if (user.getStatus().equalsIgnoreCase(BusinessConstants.STATUS_ACTIVE)){
				logger.info("ChatMessageServiceImpl :  user("+user+")" );
			}
		}
		 
		if(user != null) {
			if(user.getRole() != BusinessConstants.ROLE_1) {
				logger.info("ChatMessageServiceImpl : " + BusinessConstants.USER_ROLE_INVALID);
				throw new RedTrackProcessingException(BusinessConstants.ERROR_00005, BusinessConstants.USER_ROLE_INVALID);
			}
			if(!user.getStatus().equalsIgnoreCase(BusinessConstants.STATUS_ACTIVE)){
				logger.info("ChatMessageServiceImpl : " + BusinessConstants.USER_NOT_REGISTERED);
				throw new RedTrackProcessingException(BusinessConstants.ERROR_00005, BusinessConstants.USER_NOT_REGISTERED);
			} 
		} else {
			logger.info("ChatMessageServiceImpl : " + BusinessConstants.USER_ROLE_INVALID);
			throw new RedTrackProcessingException(BusinessConstants.ERROR_00005, BusinessConstants.USER_ROLE_INVALID);
		}
		isValid = true;
		logger.info("ChatMessageServiceImpl : findUserByID : end" );
		return isValid;
	}
}
