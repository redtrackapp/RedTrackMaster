package com.dbs.redtrack.dto.helper;

import com.dbs.redtrack.base.dto.AbstractBaseDTO;

public class AppCountry  extends AbstractBaseDTO{
	

	private static final long serialVersionUID = 1L;
	
	private String countryCode;
	private String applicationCode;	
	
 
	public AppCountry() {
		super();
		// TODO Auto-generated constructor stub
	}


	public AppCountry(String countryCode, String applicationCode) {
		super();
		this.countryCode = countryCode;
		this.applicationCode = applicationCode;
	}


	public String getCountryCode() {
		return countryCode;
	}


	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}


	public String getApplicationCode() {
		return applicationCode;
	}


	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}
	 
	
	
}
