package com.dbs.redtrack.response.dto;

import java.util.List;

import com.dbs.redtrack.base.dto.AbstractBaseDTO;

public class GetApplicationStatusResponseDTO_old extends AbstractBaseDTO{
	
	private static final long serialVersionUID = 1L;
	 
	List<ApplicationStatusHelper> applicationStatus;

	public List<ApplicationStatusHelper> getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(List<ApplicationStatusHelper> applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
 
	

}
