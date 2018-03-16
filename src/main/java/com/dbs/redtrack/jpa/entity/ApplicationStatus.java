package com.dbs.redtrack.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * application_status table remember to map in persistence.xml
 * 
 */
@Entity
@Table(name = "application_status")
public class ApplicationStatus implements Serializable {
	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "APP_ID")
	private String appId;

	@Column(name = "COUNTRY_CODE")
	private String countryCode;

	@Column(name = "UNIT_ID")
	private String unitId;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "APP_COMMENTS")
	private String appComments;

	@OneToOne(optional = false)
	@JoinColumn(name = "APP_ID", insertable = false, updatable = false)
	private Applications applications;
	
	
	@OneToOne(optional = false)
	@JoinColumn(name = "UNIT_ID", insertable = false, updatable = false)
	private BusinessUnit businessUnit;
	

	public String getAppComments() {
		return appComments;
	}

	public void setAppComments(String appComments) {
		this.appComments = appComments;
	}

	/**
	 * @return the applications
	 */
	public Applications getApplications() {
		return applications;
	}

	/**
	 * @param applications the applications to set
	 */
	public void setApplications(Applications applications) {
		this.applications = applications;
	}

	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode
	 *            the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * @return the unitId
	 */
	public String getUnitId() {
		return unitId;
	}

	/**
	 * @param unitId
	 *            the unitId to set
	 */
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	/**
	 * @return the appId
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * @param appId
	 *            the appId to set
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public BusinessUnit getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(BusinessUnit businessUnit) {
		this.businessUnit = businessUnit;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
