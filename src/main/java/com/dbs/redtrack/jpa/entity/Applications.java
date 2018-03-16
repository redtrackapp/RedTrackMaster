package com.dbs.redtrack.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Applications table remember to map in persistence.xml
 * 
 */
@Entity
@Table(name = "applications")
public class Applications implements Serializable {
	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "APP_ID")
	private String appId;

	@Column(name = "APPLICATION_NAME")
	private String appName;

	@Column(name = "APP_CODE")
	private String appCode;

	@Column(name = "APP_ACTIVE")
	private String appActive;

	@Column(name = "COUNTRY_CODE")
	private String countryCode;

	/**
	 * @return the appActive
	 */
	public String getAppActive() {
		return appActive;
	}

	/**
	 * @param appActive
	 *            the appActive to set
	 */
	public void setAppActive(String appActive) {
		this.appActive = appActive;
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
	 * @return the appName
	 */
	public String getAppName() {
		return appName;
	}

	/**
	 * @param appName
	 *            the appName to set
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}

	/**
	 * @return the appCode
	 */
	public String getAppCode() {
		return appCode;
	}

	/**
	 * @param appCode
	 *            the appCode to set
	 */
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}



	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
