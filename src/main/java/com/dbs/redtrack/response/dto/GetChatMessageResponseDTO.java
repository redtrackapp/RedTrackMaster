package com.dbs.redtrack.response.dto;

import java.util.List;

import com.dbs.redtrack.base.dto.AbstractBaseDTO;

public class GetChatMessageResponseDTO extends AbstractBaseDTO{
	
	private static final long serialVersionUID = 1L;
	 
	 List<ChatMessageHelper> chatMessages;

	public List<ChatMessageHelper> getChatMessages() {
		return chatMessages;
	}

	public void setChatMessages(List<ChatMessageHelper> chatMessages) {
		this.chatMessages = chatMessages;
	}
	

}
