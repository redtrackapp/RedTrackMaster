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

/**
 * Applications table remember to map in persistence.xml
 * 
 */
@Entity
@Table(name = "audit_log")
public class AuditLog implements Serializable {
	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "AUDIT_ID")
	private String auditID;

	@Column(name = "USER_ID")
	private String userID;
	
    @OneToOne(optional=false)
    @JoinColumn(name = "USER_ID", insertable=false, updatable=false)
    private User userDetail;   

	@Column(name = "METHOD_NAME")
	private String methodName;

	@Column(name = "METHOD_PARAMETERS")
	private String methodParams;

	@Column(name = "TIME_STAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;

	 
	public User getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(User userDetail) {
		this.userDetail = userDetail;
	}

	public String getAuditID() {
		return auditID;
	}

	public void setAuditID(String auditID) {
		this.auditID = auditID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodParams() {
		return methodParams;
	}

	public void setMethodParams(String methodParams) {
		this.methodParams = methodParams;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
 

}
