package com.dbs.redtrack.dto.helper;

import com.dbs.redtrack.base.dto.AbstractBaseDTO;

public class ApplicationHelper  extends AbstractBaseDTO{
	

	private static final long serialVersionUID = 1L;
	
	private String applicationID; 
	
	public ApplicationHelper() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public ApplicationHelper(String applicationID) {
		super();
		this.applicationID = applicationID;
	}



	public String getApplicationID() {
		return applicationID;
	}
	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	} 
	
	
}
