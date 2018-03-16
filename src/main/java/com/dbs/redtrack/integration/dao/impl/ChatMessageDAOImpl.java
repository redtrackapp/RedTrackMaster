package com.dbs.redtrack.integration.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dbs.redtrack.constants.DAOConstants;
import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.integration.dao.ChatMessageDAO;
import com.dbs.redtrack.jpa.entity.ChatMessage;

@Repository
public class ChatMessageDAOImpl extends BaseDAOImpl implements ChatMessageDAO {

	private static final Logger logger = Logger.getLogger(ChatMessageDAOImpl.class);
	
	
	  
	@Override
	@Transactional
	public ChatMessage saveChatMessage(ChatMessage chatMessage) throws RedTrackProcessingException {
		logger.info("Inside ChatMessageDAOImpl :: saveChatMessage() :: start");
 
		if (null != chatMessage) {
			save(chatMessage);
		}
		return  chatMessage;
	}
	

	/**
	 * comments here
	 */ 
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ChatMessage> getChatMessages(String incidentID) throws RedTrackProcessingException {
		logger.info("Inside ChatMessageDAOImpl :getChatMessages : incidentID()(" + incidentID + ")");
		
		Query query = getEm().createQuery(DAOConstants.QUERY_CHAT_MESSAGE_BY_INCIDENT);	
		//changed 19.07.17 change for incident id input by user
		//query.setParameter("incidentID", incidentID);
		query.setParameter("incidentID", Long.parseLong(incidentID));
 
		List<ChatMessage> results = query.getResultList();		
 	
		logger.info("getChatMessages() :: Results: " + results);
		
		return results;
	}
	 
 
}
