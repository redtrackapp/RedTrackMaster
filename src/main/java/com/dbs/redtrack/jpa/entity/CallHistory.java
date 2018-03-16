package com.dbs.redtrack.jpa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Entity
@Table(name="call_history")
public class CallHistory  implements Serializable {
	
	private static final long serialVersionUID = 1L;
 
	@Id
	@Column(name="ID")
	private long id;
	
	@Column(name="USER_OTP_ID")
	private long userOTPID;
	
	@Column(name="CALL_HISTORY_NAME")
	private String callHistoryname;
	
	@Column(name="CATEGORY_NAME")
	private String categoryName;
	 
	@Column(name="CALL_HISTORY_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date callHistoryDate;
	
	@Column(name="CALL_HISTORY_TIME")
 	private String callHistoryTime;
	
	@Column(name="INCIDENT_ID")
	//private long incidentID;
	private String incidentID;
	
	@Column(name="INCIDENT_NAME")
	private String incidentName;

	@Column(name="PARTICIPANT_CODE")
	private long participantCode;
	
	//todo: test
    @OneToOne(optional=false)
    @JoinColumn(name = "PARTICIPANT_CODE1") 
    private User participant;   
	
	public User getParticipant() {
		return participant;
	}

	public void setParticipant(User participant) {
		this.participant = participant;
	}

	@Column(name="PARTICIPANT_PHONE_NUMBER")
	private String participantPhoneNumber;
	 
	@Column(name="DATE_CREATED")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserOTPID() {
		return userOTPID;
	}

	public void setUserOTPID(long userOTPID) {
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

	public Date getCallHistoryDate() {
		return callHistoryDate;
	}

	public void setCallHistoryDate(Date callHistoryDate) {
		this.callHistoryDate = callHistoryDate;
	}

	public String getCallHistoryTime() {
		return callHistoryTime;
	}

	public void setCallHistoryTime(String callHistoryTime) {
		this.callHistoryTime = callHistoryTime;
	}

//	public long getIncidentID() {
//		return incidentID;
//	}
//
//	public void setIncidentID(long incidentID) {
//		this.incidentID = incidentID;
//	}

	public String getIncidentName() {
		return incidentName;
	}

	public String getIncidentID() {
		return incidentID;
	}

	public void setIncidentID(String incidentID) {
		this.incidentID = incidentID;
	}

	public void setIncidentName(String incidentName) {
		this.incidentName = incidentName;
	}

	public long getParticipantCode() {
		return participantCode;
	}

	public void setParticipantCode(long participantCode) {
		this.participantCode = participantCode;
	}

	public String getParticipantPhoneNumber() {
		return participantPhoneNumber;
	}

	public void setParticipantPhoneNumber(String participantPhoneNumber) {
		this.participantPhoneNumber = participantPhoneNumber;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
