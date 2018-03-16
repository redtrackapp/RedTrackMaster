/**
 * 
 */
package com.dbs.redtrack.integration.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dbs.redtrack.dto.helper.AppCountry;
import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.jpa.entity.ApplicationStatus;
import com.dbs.redtrack.jpa.entity.Applications;
import com.dbs.redtrack.response.dto.ApplicationDetailsHelper;
import com.dbs.redtrack.response.dto.ApplicationStatusHelper;

/**
 * @author IBM : Select application status.
 * 
 */
@Repository
public interface IApplicationStatusDAO {
	

	
	public List<ApplicationStatusHelper> getApplicationsStatus() throws RedTrackProcessingException;

 

	public List<ApplicationDetailsHelper> getApplicationDetails() throws RedTrackProcessingException;

	public String updateApplicationsStatus(List<AppCountry> appCountryList, String strStatus)
			throws RedTrackProcessingException;

	 

}
