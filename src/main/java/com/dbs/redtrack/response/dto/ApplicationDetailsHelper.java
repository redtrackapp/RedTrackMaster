/**
 * 
 */
package com.dbs.redtrack.response.dto;

import com.dbs.redtrack.base.dto.AbstractBaseDTO;

/**
 * @author IBM
 * 
 *         POJO class to map the Applications table data.
 * 
 */

public class ApplicationDetailsHelper extends AbstractBaseDTO {

	private static final long serialVersionUID = 1L;

	private String appId;

	private String appName;

	private String appCode;

	private String appActive;

	private String countryCode;
	
	

	public ApplicationDetailsHelper() {
		super();
		// TODO Auto-generated constructor stub
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

}
