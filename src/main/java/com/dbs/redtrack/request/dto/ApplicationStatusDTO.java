package com.dbs.redtrack.request.dto;

/**
 * 
 * @author Imran
 *
 */

public class ApplicationStatusDTO  {
	
		
	private String appId;
	
	
	private String countryCode;

	
	private String unitId;
	

	private String status;

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

	
	
}


 