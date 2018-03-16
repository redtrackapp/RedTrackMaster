package com.dbs.redtrack.response.dto;

import com.dbs.redtrack.base.dto.AbstractBaseDTO;

public class ChatMessageResponseDTO extends AbstractBaseDTO{
	
	private static final long serialVersionUID = 1L;
	 
	String message;
	String responsecode;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResponsecode() {
		return responsecode;
	}

	public void setResponsecode(String responsecode) {
		this.responsecode = responsecode;
	}
  
	

}
