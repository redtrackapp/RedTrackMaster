package com.dbs.redtrack.request.dto;

import java.util.List;

import com.dbs.redtrack.base.dto.AbstractBaseDTO;
import com.dbs.redtrack.dto.helper.ApplicationCountryHelper;

public class EditIncidentRequestDTO extends AbstractBaseDTO{
	
	private static final long serialVersionUID = 1L;


	String incidentid;	
	String incidenttitle;
	String incidentdesc;
	String incidentresolution;
	String incidentthreatseverity;
	String incidentcategory;
	String userotpid;
	String incidentconfnum;
	String incidentparticipantcode; 
	private List<ApplicationCountryHelper> incidentApps; 
	
	
	
	public EditIncidentRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EditIncidentRequestDTO(String incidenttitle2, String incidentdesc2,
			String incidentconfnum2, String incidentparticipantcode2,
			String incidentresolution2, String incidentimpacts2,
			String incidentthreatseverity2, String incidentcategory2,
			String userotpid2, String incidentid2) {
		
		this.incidenttitle=incidenttitle2;
		this.incidentdesc=incidentdesc2;
		this.incidentcategory=incidentcategory2;
		this.incidentparticipantcode=incidentparticipantcode2;
		this.incidentresolution=incidentresolution2;
		this.incidentthreatseverity=incidentthreatseverity2;
		this.userotpid=userotpid2;
		
		
		this.incidentconfnum=incidentconfnum2;		
 
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
	public String getIncidenttitle() {
		return incidenttitle;
	}
	public void setIncidenttitle(String incidenttitle) {
		this.incidenttitle = incidenttitle;
	}
	public String getIncidentid() {
		return incidentid;
	}
	public void setIncidentid(String incidentid) {
		this.incidentid = incidentid;
	}
	public List<ApplicationCountryHelper> getIncidentApps() {
		return incidentApps;
	}
	public void setIncidentApps(List<ApplicationCountryHelper> incidentApps) {
		this.incidentApps = incidentApps;
	}
 
 

}
