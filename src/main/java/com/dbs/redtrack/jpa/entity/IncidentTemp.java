package com.dbs.redtrack.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/*
 * Remember to add mapping to persistence.xml
 * */
@Entity
@Table(name="incident_temp")
public class IncidentTemp implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//@Id @GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Id
	@Column(name="INCIDENT_ID")
	private String incidentId;
	
	@Column(name="INCIDENT_TITLE")
	private String incidenttitle;
	
	@Column(name="INCIDENT_DESC")
	private String incidentdesc;
	
	@Column(name="INCIDENT_CONF_NUM")
	private String incidentconfnum;
	
	@Column(name="INCIDENT_PARTICIPANT_CODE")
	private String incidentparticipantcode;
	
	@Column(name="INCIDENT_RESOLUTION")
	private String incidentresolution;
	

	@Column(name="INCIDENT_IMPACTS")
	private String incidentimpacts;

	@Column(name="INCIDENT_THREAT_SEVERITY")
	private String incidentthreatseverity;
	
	@Column(name="INCIDENT_CATEGORY")
	private String incidentcategory;

	
	@Column(name="USEROTP_ID")
	private String userotpid;

	@Column(name="ACCESSPROFILE_ID")
	private String accessprofileid;

	@Column(name="CHATMESSAGE_ID")
	private String chatmessageid;
	
	@Column(name="USERDEVICE_TOKENSTR")
	private String userdevictokenstr;
	
	/*
	 CREATE TABLE `IncidentTemp` (
		`INCIDENT_ID` INT(11) NOT NULL AUTO_INCREMENT,
		`INCIDENT_TITLE` VARCHAR(50) NULL DEFAULT NULL,
		`INCIDENT_DESC` VARCHAR(50) NULL DEFAULT NULL,
		`INCIDENT_CONF_NUM` VARCHAR(50) NULL DEFAULT NULL,
		`INCIDENT_PARTICIPANT_CODE` VARCHAR(50) NULL DEFAULT NULL,
		`INCIDENT_RESOLUTION` VARCHAR(50) NULL DEFAULT NULL,
		`INCIDENT_IMPACTS` VARCHAR(50) NULL DEFAULT NULL,
		`INCIDENT_THREAT_SEVERITY` VARCHAR(50) NULL DEFAULT NULL,
		`INCIDENT_CATEGORY` VARCHAR(50) NULL DEFAULT NULL,
		`USEROTP_ID` VARCHAR(50) NULL DEFAULT NULL,
		`ACCESSPROFILE_ID` VARCHAR(50) NULL DEFAULT NULL,
		`CHATMESSAGE_ID` VARCHAR(50) NULL DEFAULT NULL,
		`USERDEVICE_TOKENSTR` VARCHAR(50) NULL DEFAULT NULL,
		PRIMARY KEY (`INCIDENT_ID`)
	)
	 * */
	

	
	public String getIncidentId() {
		return incidentId;
	}





	public String getChatmessageid() {
		return chatmessageid;
	}





	public void setChatmessageid(String chatmessageid) {
		this.chatmessageid = chatmessageid;
	}





	public String getUserdevictokenstr() {
		return userdevictokenstr;
	}





	public void setUserdevictokenstr(String userdevictokenstr) {
		this.userdevictokenstr = userdevictokenstr;
	}





	public String getAccessprofileid() {
		return accessprofileid;
	}





	public void setAccessprofileid(String accessprofileid) {
		this.accessprofileid = accessprofileid;
	}





	public void setIncidentId(String incidentId) {
		this.incidentId = incidentId;
	}





	public String getIncidenttitle() {
		return incidenttitle;
	}





	public void setIncidenttitle(String incidenttitle) {
		this.incidenttitle = incidenttitle;
	}





	public String getIncidentdesc() {
		return incidentdesc;
	}





	public void setIncidentdesc(String incidentdesc) {
		this.incidentdesc = incidentdesc;
	}





	public String getIncidentconfnum() {
		return incidentconfnum;
	}





	public void setIncidentconfnum(String incidentconfnum) {
		this.incidentconfnum = incidentconfnum;
	}





	public String getIncidentparticipantcode() {
		return incidentparticipantcode;
	}





	public void setIncidentparticipantcode(String incidentparticipantcode) {
		this.incidentparticipantcode = incidentparticipantcode;
	}





	public String getIncidentresolution() {
		return incidentresolution;
	}





	public void setIncidentresolution(String incidentresolution) {
		this.incidentresolution = incidentresolution;
	}





	public String getIncidentimpacts() {
		return incidentimpacts;
	}





	public void setIncidentimpacts(String incidentimpacts) {
		this.incidentimpacts = incidentimpacts;
	}





	public String getIncidentthreatseverity() {
		return incidentthreatseverity;
	}





	public void setIncidentthreatseverity(String incidentthreatseverity) {
		this.incidentthreatseverity = incidentthreatseverity;
	}





	public String getIncidentcategory() {
		return incidentcategory;
	}





	public void setIncidentcategory(String incidentcategory) {
		this.incidentcategory = incidentcategory;
	}





	public String getUserotpid() {
		return userotpid;
	}





	public void setUserotpid(String userotpid) {
		this.userotpid = userotpid;
	}





	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	
	
}


 