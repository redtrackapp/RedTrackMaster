package com.dbs.redtrack.request.dto;

import com.dbs.redtrack.base.dto.AbstractBaseDTO;

public class SaveChatRequestDTO extends AbstractBaseDTO{
	
	private static final long serialVersionUID = 1L;
	
	String incidentid;
	String userotpid;
	String message;

	public SaveChatRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SaveChatRequestDTO(String incidentid, String userotpid, String message) {
		super();
		this.incidentid = incidentid;
		this.userotpid = userotpid;
		this.message = message;
	} 
	 
	public String getIncidentid() {
		return incidentid;
	}
	public void setIncidentid(String incidentid) {
		this.incidentid = incidentid;
	}
	public String getUserotpid() {
		return userotpid;
	}
	public void setUserotpid(String userotpid) {
		this.userotpid = userotpid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
