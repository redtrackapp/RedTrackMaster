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
public class ApplicationStatusCountries extends AbstractBaseDTO {




	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String country_name;
	
	private String country_status;

	private List<ApplicationStatusBusinessUnits> bizunit_names;
	
	
	public ApplicationStatusCountries() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApplicationStatusCountries(String country_name, String country_status,
			ApplicationStatusBusinessUnits applicationStatusBusinessUnits) {
		super();
			this.country_name = country_name;
			this.country_status= country_status;
			this.bizunit_names = new ArrayList<ApplicationStatusBusinessUnits>();
			this.bizunit_names.add(applicationStatusBusinessUnits);
	}
	
	/**
	 * @return the country_status
	 */
	public String getCountry_status() {
		return country_status;
	}

	/**
	 * @param country_status the country_status to set
	 */
	public void setCountry_status(String country_status) {
		this.country_status = country_status;
	}

	/**
	 * @return the country_name
	 */
	public String getCountry_name() {
		return country_name;
	}

	/**
	 * @param country_name the country_name to set
	 */
	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}
	
	/**
	 * @return the bizunit_names
	 */
	public List<ApplicationStatusBusinessUnits> getBizunit_names() {
		return bizunit_names;
	}

	/**
	 * @param bizunit_names the bizunit_names to set
	 */
	public void setBizunit_names(List<ApplicationStatusBusinessUnits> bizunit_names) {
		this.bizunit_names = bizunit_names;
	}


}
