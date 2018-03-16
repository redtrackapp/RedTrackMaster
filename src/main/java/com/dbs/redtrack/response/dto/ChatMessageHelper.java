package com.dbs.redtrack.response.dto;

import com.dbs.redtrack.base.dto.AbstractBaseDTO;

public class ChatMessageHelper extends AbstractBaseDTO{
	
	private static final long serialVersionUID = 1L;
	
	String incidentid;
	String userotpid;
	String message;
	String datecreated;

	public ChatMessageHelper() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChatMessageHelper(String incidentid, String userotpid, String message, String datecreated) {
		super();
		this.incidentid = incidentid;
		this.userotpid = userotpid;
		this.message = message;
		this.datecreated = datecreated;
	}
	
	public ChatMessageHelper(String incidentid, String userotpid, String message) {
		super();
		this.incidentid = incidentid;
		this.userotpid = userotpid;
		this.message = message;
	} 
	 
	public String getDatecreated() {
		return datecreated;
	}
	public void setDatecreated(String datecreated) {
		this.datecreated = datecreated;
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
