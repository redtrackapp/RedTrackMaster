/**
 * 
 */
package com.dbs.redtrack.response.dto;

import java.util.ArrayList;
import java.util.List;

import com.dbs.redtrack.base.dto.AbstractBaseDTO;

/**
 * @author ADMINIBM
 * 
 */
public class ApplicationStatusBusinessUnits extends AbstractBaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String bizunit_name;

	private String bizunit_status;
	
	private String country_code;
	
	private List<ApplicationStatusApps> apptype_names;

	/**
	 * @return the country_code
	 */
	public String getCountry_code() {
		return country_code;
	}

	/**
	 * @param country_code the country_code to set
	 */
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	
	
	

	public ApplicationStatusBusinessUnits() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApplicationStatusBusinessUnits(String bizunit_name, String bizunit_status, String country_code, ApplicationStatusApps applicationStatusApps) {
		
		super();
		this.apptype_names = new ArrayList<ApplicationStatusApps>();
		this.bizunit_name = bizunit_name;
		this.bizunit_status = bizunit_status;
		this.country_code= country_code;
		this.apptype_names.add(applicationStatusApps);
		
	}

	/**
	 * @return the apptype_names
	 */
	public List<ApplicationStatusApps> getApptype_names() {
		return apptype_names;
	}

	/**
	 * @param apptype_names the apptype_names to set
	 */
	public void setApptype_names(List<ApplicationStatusApps> apptype_names) {
		this.apptype_names = apptype_names;
	}

	/**
	 * @return the bizunit_name
	 */
	public String getBizunit_name() {
		return bizunit_name;
	}

	/**
	 * @param bizunit_name
	 *            the bizunit_name to set
	 */
	public void setBizunit_name(String bizunit_name) {
		this.bizunit_name = bizunit_name;
	}

	/**
	 * @return the bizunit_status
	 */
	public String getBizunit_status() {
		return bizunit_status;
	}

	/**
	 * @param bizunit_status
	 *            the bizunit_status to set
	 */
	public void setBizunit_status(String bizunit_status) {
		this.bizunit_status = bizunit_status;
	}

}
