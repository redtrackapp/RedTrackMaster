/**
 * 
 */
package com.dbs.redtrack.business.service;

import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.request.dto.SaveChatRequestDTO;
import com.dbs.redtrack.response.dto.ChatMessageResponseDTO;
import com.dbs.redtrack.response.dto.GetChatMessageResponseDTO;

public interface ChatMessageService {
	
	 public	ChatMessageResponseDTO saveChatMessage(SaveChatRequestDTO messageDTO) throws RedTrackProcessingException;

	public GetChatMessageResponseDTO getMessages(String incidentID) throws RedTrackProcessingException;
}
