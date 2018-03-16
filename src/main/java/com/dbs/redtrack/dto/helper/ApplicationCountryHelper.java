package com.dbs.redtrack.dto.helper;

import java.util.ArrayList;
import java.util.List;

import com.dbs.redtrack.base.dto.AbstractBaseDTO;

public class ApplicationCountryHelper  extends AbstractBaseDTO{
	

	private static final long serialVersionUID = 1L;
	
	private String countryCode;
	private List<ApplicationHelper> applicationList;	
	
 
	public ApplicationCountryHelper() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public ApplicationCountryHelper(String countryCode, String application) {
		super();
		this.countryCode = countryCode;
		this.applicationList = new ArrayList<ApplicationHelper>();
		this.applicationList.add(new ApplicationHelper(application));
	}


	public List<ApplicationHelper> getApplicationList() {
		return applicationList;
	}
	public void setApplicationList(List<ApplicationHelper> applicationList) {
		this.applicationList = applicationList;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	
}
