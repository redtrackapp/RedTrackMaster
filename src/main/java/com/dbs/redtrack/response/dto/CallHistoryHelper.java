package com.dbs.redtrack.response.dto;

import com.dbs.redtrack.base.dto.AbstractBaseDTO;

public class CallHistoryHelper extends AbstractBaseDTO {
	
	private static final long serialVersionUID = 1L;
	
	private String id;	 
	private String userOTPID;	 
	private String callHistoryname;	 
	private String categoryName;	  
	private String callHistoryDate;	 
 	private String callHistoryTime;	 
	private String incidentID;	 
	private String incidentName; 
	private String participantCode;	 
	private String participantPhoneNumber;	  
	private String dateCreated;
	
	private String participantNickname;
	private String participantFullName;
	
	public String getParticipantNickname() {
		return participantNickname;
	}
	public void setParticipantNickname(String participantNickname) {
		this.participantNickname = participantNickname;
	}
	public String getParticipantFullName() {
		return participantFullName;
	}
	public void setParticipantFullName(String participantFullName) {
		this.participantFullName = participantFullName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserOTPID() {
		return userOTPID;
	}
	public void setUserOTPID(String userOTPID) {
		this.userOTPID = userOTPID;
	}
	public String getCallHistoryname() {
		return callHistoryname;
	}
	public void setCallHistoryname(String callHistoryname) {
		this.callHistoryname = callHistoryname;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCallHistoryDate() {
		return callHistoryDate;
	}
	public void setCallHistoryDate(String callHistoryDate) {
		this.callHistoryDate = callHistoryDate;
	}
	public String getCallHistoryTime() {
		return callHistoryTime;
	}
	public void setCallHistoryTime(String callHistoryTime) {
		this.callHistoryTime = callHistoryTime;
	}
	public String getIncidentID() {
		return incidentID;
	}
	public void setIncidentID(String incidentID) {
		this.incidentID = incidentID;
	}
	public String getIncidentName() {
		return incidentName;
	}
	public void setIncidentName(String incidentName) {
		this.incidentName = incidentName;
	}
	public String getParticipantCode() {
		return participantCode;
	}
	public void setParticipantCode(String participantCode) {
		this.participantCode = participantCode;
	}
	public String getParticipantPhoneNumber() {
		return participantPhoneNumber;
	}
	public void setParticipantPhoneNumber(String participantPhoneNumber) {
		this.participantPhoneNumber = participantPhoneNumber;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	
}
