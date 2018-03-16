package com.dbs.redtrack.jpa.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
 
@Entity
@Table(name="incident")
public class Incident implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//changed 19.07.17 updated incidentid to be manually enteredd by user
	@Id
	@Column(name="INCIDENT_ID")
	private long incidentId;
	
//	@Id
//	@Column(name="INCIDENT_ID")
//	private String incidentId;
	
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

	@Column(name="INCIDENT_THREAT_SEVERITY")
	private String incidentthreatseverity;
	
	@Column(name="INCIDENT_CATEGORY")
	private String incidentcategory;
	
	@Column(name="USEROTP_ID")
	private Long userotpid;
	
    @OneToOne(optional=false)
    @JoinColumn(name = "USEROTP_ID", insertable=false, updatable=false)
    private User userDetail;   
	
	@Column(name="USERDEVICE_TOKENSTR")
	private String userdevictokenstr;
	
	@Column(name="DATE_CREATED")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;

	@Column(name="UPDATED_BY")
	private Long updatedBy;
	
	@Column(name="DATE_UPDATED")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateUpdated;
	 
	@Column(name="STATUS")
	private String status;
	
	@Column(name="INCIDENT_STATUS")
	private String incidentStatus;
	
 
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "incident", orphanRemoval = true)
    private List<IncidentMap> incidentMap = new ArrayList<>();

 

	public String getIncidentStatus() {
		return incidentStatus;
	}

	public void setIncidentStatus(String incidentStatus) {
		this.incidentStatus = incidentStatus;
	}

	public User getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(User userDetail) {
		this.userDetail = userDetail;
	}

	public List<IncidentMap> getIncidentMap() {
		return incidentMap;
	}

	public void setIncidentMap(List<IncidentMap> incidentMap) {
		this.incidentMap = incidentMap;
	}
	
	
 
    public void addIncidentMap(IncidentMap imap) {
    	incidentMap.add(imap);
    	imap.setIncident(this);
    }
 
    public void removeIncidentMap(IncidentMap imap) {
    	incidentMap.remove(imap);
    	imap.setIncident(null);
    }
 
//	
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "inicedentmap", orphanRemoval = true)
//    private Collection<IncidentMap> incidentMap;
//	 
	
//	
//	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//	@JoinColumn(name = "INCIDENT_ID")
//	private List<IncidentMap> incidentMap = new ArrayList<>();
//	
	
//	
// 
//	public Collection<IncidentMap> getIncidentMap() {
//		return incidentMap;
//	}
//
//	public void setIncidentMap(Collection<IncidentMap> incidentMap) {
//		this.incidentMap = incidentMap;
//	}

//	public List<IncidentMap> getIncidentMap() {
//		return incidentMap;
//	}
//
//	public void setIncidentMap(List<IncidentMap> incidentMap) {
//		this.incidentMap = incidentMap;
//	}


//	public long getIncidentId() {
//		return incidentId;
//	}
//
//	public void setIncidentId(long incidentId) {
//		this.incidentId = incidentId;
//	}

	public String getIncidenttitle() {
		return incidenttitle;
	}

//	public String getIncidentId() {
//		return incidentId;
//	}
//
//	public void setIncidentId(String incidentId) {
//		this.incidentId = incidentId;
//	}

	public void setIncidenttitle(String incidenttitle) {
		this.incidenttitle = incidenttitle;
	}

	public long getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(long incidentId) {
		this.incidentId = incidentId;
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
 

	public String getUserdevictokenstr() {
		return userdevictokenstr;
	}

	public void setUserdevictokenstr(String userdevictokenstr) {
		this.userdevictokenstr = userdevictokenstr;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
 

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public Long getUserotpid() {
		return userotpid;
	}

	public void setUserotpid(Long userotpid) {
		this.userotpid = userotpid;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}


 