/**
 * 
 */
package com.dbs.redtrack.response.dto;

import com.dbs.redtrack.base.dto.AbstractBaseDTO;

/**
 * @author IBM
 * 
 */
public class ApplicationStatusHelper extends AbstractBaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String countryCode;

	private String bizunit_name;

	private String bizunit_id;
	
	private String appComments;
	
	/**
	 * @return the bizunit_id
	 */
	public String getBizunit_id() {
		return bizunit_id;
	}

	/**
	 * @param bizunit_id the bizunit_id to set
	 */
	public void setBizunit_id(String bizunit_id) {
		this.bizunit_id = bizunit_id;
	}

	private String appId;

	private String apptype_status;

	private String apptype_name;
	
	

	/**
	 * @return the bizunit_name
	 */
	public String getBizunit_name() {
		return bizunit_name;
	}

	/**
	 * @param bizunit_name the bizunit_name to set
	 */
	public void setBizunit_name(String bizunit_name) {
		this.bizunit_name = bizunit_name;
	}

	/**
	 * @return the apptype_status
	 */
	public String getApptype_status() {
		return apptype_status;
	}

	/**
	 * @param apptype_status the apptype_status to set
	 */
	public void setApptype_status(String apptype_status) {
		this.apptype_status = apptype_status;
	}

	/**
	 * @return the apptype_name
	 */
	public String getApptype_name() {
		return apptype_name;
	}

	/**
	 * @param apptype_name the apptype_name to set
	 */
	public void setApptype_name(String apptype_name) {
		this.apptype_name = apptype_name;
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

	public String getAppComments() {
		return appComments;
	}

	public void setAppComments(String appComments) {
		this.appComments = appComments;
	}




}
