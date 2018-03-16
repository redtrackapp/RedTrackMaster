package com.dbs.redtrack.business.service;

import java.util.List;

import com.dbs.redtrack.response.dto.ApplicationsDetailsResponseDTO;

/**
 * @author IBM
 * 
 * Interface to get all details of application like appId,appCode and appStatus etc.
 *
 */
public interface IApplicationDetailsService {
	
	public List<ApplicationsDetailsResponseDTO>  getApplicationDetails();

}
