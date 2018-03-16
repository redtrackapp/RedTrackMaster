package com.dbs.redtrack.request.dto;

import com.dbs.redtrack.base.dto.AbstractBaseDTO;

public class GetAllIncidentMessagesRequestDTO extends AbstractBaseDTO{
	
	private static final long serialVersionUID = 1L;

	
	String userotpid;
	String incidentid;
	String chatmessageid;
	
	public GetAllIncidentMessagesRequestDTO(String incidentid2,
			String userotpid2, String chatmessageid2) {
		
		this.userotpid=userotpid2;
		this.incidentid=incidentid2;
		this.chatmessageid=chatmessageid2;
		
		
		
	}
	
	
	public GetAllIncidentMessagesRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getUserotpid() {
		return userotpid;
	}
	public void setUserotpid(String userotpid) {
		this.userotpid = userotpid;
	}
	public String getIncidentid() {
		return incidentid;
	}
	public void setIncidentid(String incidentid) {
		this.incidentid = incidentid;
	}
	public String getChatmessageid() {
		return chatmessageid;
	}
	public void setChatmessageid(String chatmessageid) {
		this.chatmessageid = chatmessageid;
	}
	
	
	
	
		
 

}
