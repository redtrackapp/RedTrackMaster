package com.dbs.redtrack.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;


@Entity
@Table(name="incident_history")
public class InicidentHistory implements Serializable {
	
	private static final long serialVersionUID = 1L;
	 
	@Id
	@Column(name="INCIDENT_ID")
	private long incidentID;
	
	@Column(name="SEVERITY_LEVEL")
	private String severityLevel;
	
	@Column(name="STATUS")
	private String status;
	 
	
	public long getIncidentID() {
		return incidentID;
	}


	public void setIncidentID(long incidentID) {
		this.incidentID = incidentID;
	}


	public String getSeverityLevel() {
		return severityLevel;
	}


	public void setSeverityLevel(String severityLevel) {
		this.severityLevel = severityLevel;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	} 
}