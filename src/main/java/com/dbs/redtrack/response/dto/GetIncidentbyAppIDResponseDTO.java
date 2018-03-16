package com.dbs.redtrack.response.dto;



import com.dbs.redtrack.base.dto.AbstractBaseDTO;

public class GetIncidentbyAppIDResponseDTO extends AbstractBaseDTO{
	
	private static final long serialVersionUID = 1L;
	private String incidentid;
	private String incidenttitle;
	private String incidentdesc;
	private String incidentresolution;
	private String incidentthreatseverity;
	private String incidentcategory;
	private String userFullName;
	private String dateCreated; 
	
	
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getUserFullName() {
		return userFullName;
	}
	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}
	public String getIncidentid() {
		return incidentid;
	}
	public void setIncidentid(String incidentid) {
		this.incidentid = incidentid;
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
	public String getIncidentresolution() {
		return incidentresolution;
	}
	public void setIncidentresolution(String incidentresolution) {
		this.incidentresolution = incidentresolution;
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
  
	

}
