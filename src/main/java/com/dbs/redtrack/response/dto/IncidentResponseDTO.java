package com.dbs.redtrack.response.dto;



import com.dbs.redtrack.base.dto.AbstractBaseDTO;

public class IncidentResponseDTO extends AbstractBaseDTO{
	
	private static final long serialVersionUID = 1L;
	 
	private String message;
	private String responsecode;
	private String incidentId;
//	String incidentId;
//	 String incidenttitle;
//	 String incidentdesc;
//	 String incidentconfnum;
//	 String incidentparticipantcode;	
//	 String incidentresolution;
//	 String incidentimpacts;
//	 String incidentthreatseverity;
//	 String incidentcategory;
//	 String userotpid;
//	 String accessprofileid;
//	 String chatmessageid;
//	 String userdevictokenstr;


	public String getMessage() {
		return message;
	}

	public String getResponsecode() {
		return responsecode;
	}

	public void setResponsecode(String responsecode) {
		this.responsecode = responsecode;
	}

	public void setMessage(String message) {
		this.message = message;
	}

//	public String getIncidentId() {
//		return incidentId;
//	}
//
//	public void setIncidentId(String incidentId) {
//		this.incidentId = incidentId;
//	}
//
//	public String getIncidenttitle() {
//		return incidenttitle;
//	}
//
//	public void setIncidenttitle(String incidenttitle) {
//		this.incidenttitle = incidenttitle;
//	}
//
//	public String getIncidentdesc() {
//		return incidentdesc;
//	}
//
//	public void setIncidentdesc(String incidentdesc) {
//		this.incidentdesc = incidentdesc;
//	}
//
//	public String getIncidentconfnum() {
//		return incidentconfnum;
//	}
//
//	public void setIncidentconfnum(String incidentconfnum) {
//		this.incidentconfnum = incidentconfnum;
//	}
//
//	public String getIncidentparticipantcode() {
//		return incidentparticipantcode;
//	}
//
//	public void setIncidentparticipantcode(String incidentparticipantcode) {
//		this.incidentparticipantcode = incidentparticipantcode;
//	}
//
//	public String getIncidentresolution() {
//		return incidentresolution;
//	}
//
//	public void setIncidentresolution(String incidentresolution) {
//		this.incidentresolution = incidentresolution;
//	}
//
//	public String getIncidentimpacts() {
//		return incidentimpacts;
//	}
//
//	public void setIncidentimpacts(String incidentimpacts) {
//		this.incidentimpacts = incidentimpacts;
//	}
//
//	public String getIncidentthreatseverity() {
//		return incidentthreatseverity;
//	}
//
//	public void setIncidentthreatseverity(String incidentthreatseverity) {
//		this.incidentthreatseverity = incidentthreatseverity;
//	}
//
//	public String getIncidentcategory() {
//		return incidentcategory;
//	}
//
//	public void setIncidentcategory(String incidentcategory) {
//		this.incidentcategory = incidentcategory;
//	}
//
//	public String getUserotpid() {
//		return userotpid;
//	}
//
//	public void setUserotpid(String userotpid) {
//		this.userotpid = userotpid;
//	}
//
//	public String getAccessprofileid() {
//		return accessprofileid;
//	}
//
//	public void setAccessprofileid(String accessprofileid) {
//		this.accessprofileid = accessprofileid;
//	}
//
//	public String getChatmessageid() {
//		return chatmessageid;
//	}
//
//	public void setChatmessageid(String chatmessageid) {
//		this.chatmessageid = chatmessageid;
//	}
//
//	public String getUserdevictokenstr() {
//		return userdevictokenstr;
//	}
//
//	public void setUserdevictokenstr(String userdevictokenstr) {
//		this.userdevictokenstr = userdevictokenstr;
//	}

	public String getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(String incidentId) {
		this.incidentId = incidentId;
	}

	public IncidentResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
  
	

}
