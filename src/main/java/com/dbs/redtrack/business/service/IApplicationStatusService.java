/**
 * 
 */
package com.dbs.redtrack.business.service;

import java.util.List;

import com.dbs.redtrack.dto.helper.AppCountry;
import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.response.dto.ApplicationStatusCountries;

/**
 * @author IBM
 * 
 *         Interface to get all database operations those are related to
 *         Application_Status table.
 * 
 */
public interface IApplicationStatusService {
 
	
	public List<ApplicationStatusCountries> getApplicationsStatus()	throws RedTrackProcessingException;
 
	//changed by raymond
   	public String updateApplicationsStatus(List<AppCountry> appCountryList, String strStatus) throws RedTrackProcessingException;

	public String getApplicationRemarks(String applicationID) throws RedTrackProcessingException;


 

 
 

}
