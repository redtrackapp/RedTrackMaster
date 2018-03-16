package com.dbs.redtrack.request.dto;

import com.dbs.redtrack.base.dto.AbstractBaseDTO;

public class CallHistoryRequestDTO extends AbstractBaseDTO{
	
	private static final long serialVersionUID = 1L;
	
	private String incidentid;
	private String incidentname;
	private String userotpid;	
	private String participantcode;
 	private String callhistorydate; 
	private String callhistorytime;
	private String callhistname; 
	private String categoryname;
	private String phonenumber;
	
	public CallHistoryRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CallHistoryRequestDTO(String incidentid, String incidentname, String userotpid, String participantcode,
			String callhistorydate, String callhistorytime, String callhistname, String categoryname,
			String phonenumber) {
		super();
		this.incidentid = incidentid;
		this.incidentname = incidentname;
		this.userotpid = userotpid;
		this.participantcode = participantcode;
		this.callhistorydate = callhistorydate;
		this.callhistorytime = callhistorytime;
		this.callhistname = callhistname;
		this.categoryname = categoryname;
		this.phonenumber = phonenumber;
	}

	public String getIncidentid() {
		return incidentid;
	}

	public void setIncidentid(String incidentid) {
		this.incidentid = incidentid;
	}

	public String getIncidentname() {
		return incidentname;
	}

	public void setIncidentname(String incidentname) {
		this.incidentname = incidentname;
	}

	public String getUserotpid() {
		return userotpid;
	}

	public void setUserotpid(String userotpid) {
		this.userotpid = userotpid;
	}

	public String getParticipantcode() {
		return participantcode;
	}

	public void setParticipantcode(String participantcode) {
		this.participantcode = participantcode;
	}

	public String getCallhistorydate() {
		return callhistorydate;
	}

	public void setCallhistorydate(String callhistorydate) {
		this.callhistorydate = callhistorydate;
	}

	public String getCallhistorytime() {
		return callhistorytime;
	}

	public void setCallhistorytime(String callhistorytime) {
		this.callhistorytime = callhistorytime;
	}

	public String getCallhistname() {
		return callhistname;
	}

	public void setCallhistname(String callhistname) {
		this.callhistname = callhistname;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	
}
