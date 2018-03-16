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
@Table(name="chat_messages")
public class ChatMessage  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	private long ID;
	
	//changed 19.07.17 updated incidentid to be manually enteredd by user
	@Column(name="INCIDENT_ID")	
	private long incidentID;
	//private String incidentID;
	
	@Column(name="USER_ID")
	private long userID;
	
	@Column(name="MESSAGE")
	private String message;

	@Column(name="DATE_CREATED")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
 
    @OneToOne(optional=false)
    @JoinColumn(  name="USER_ID", unique=true, insertable=false, updatable=false)
    private User user;
	
    
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}
//
//	public long getIncidentID() {
//		return incidentID;
//	}
//
//	public void setIncidentID(long incidentID) {
//		this.incidentID = incidentID;
//	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	} 
	
	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

//	public String getIncidentID() {
//		return incidentID;
//	}
//
//	public void setIncidentID(String incidentID) {
//		this.incidentID = incidentID;
//	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public long getIncidentID() {
		return incidentID;
	}

	public void setIncidentID(long incidentID) {
		this.incidentID = incidentID;
	}
}

