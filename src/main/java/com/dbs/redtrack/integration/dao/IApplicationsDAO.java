package com.dbs.redtrack.integration.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.jpa.entity.Applications;

/**
 * @author IBM : Select application details.
 *
 */
@Repository
public interface IApplicationsDAO {

	public List<Applications> getApplicationDetails()
			throws RedTrackProcessingException;
	

}
