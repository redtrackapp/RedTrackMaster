package com.dbs.redtrack.integration.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.jpa.entity.ChatMessage;
 

@Repository
public interface ChatMessageDAO {
	
	 public List<ChatMessage> getChatMessages(String incidentID) throws RedTrackProcessingException;

	 public ChatMessage saveChatMessage(ChatMessage chatMessage) throws RedTrackProcessingException;
	
	
	


	
	
}
