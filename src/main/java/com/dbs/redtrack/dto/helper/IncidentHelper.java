package com.dbs.redtrack.dto.helper;



import java.util.List;

import com.dbs.redtrack.base.dto.AbstractBaseDTO;

public class IncidentHelper extends AbstractBaseDTO{
	
	private static final long serialVersionUID = 1L;
	private String incidentid;
	private String incidenttitle;
	private String incidentdesc;
	private String incidentresolution;
	private String incidentthreatseverity;
	private String incidentcategory;
	private String userotpid;
	private String incidentconfnum;
	private String incidentparticipantcode; 
	private String userFullName;
	private String dateCreated;
	private List<ApplicationCountryHelper> incidentApps;
	
	
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
	public String getUserotpid() {
		return userotpid;
	}
	public void setUserotpid(String userotpid) {
		this.userotpid = userotpid;
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
	public List<ApplicationCountryHelper> getIncidentApps() {
		return incidentApps;
	}
	public void setIncidentApps(List<ApplicationCountryHelper> incidentApps) {
		this.incidentApps = incidentApps;
	} 
  
	

}
