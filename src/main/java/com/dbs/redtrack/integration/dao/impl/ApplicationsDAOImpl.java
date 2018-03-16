/**
 * 
 */
package com.dbs.redtrack.integration.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.dbs.redtrack.constants.DAOConstants;
import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.integration.dao.IApplicationsDAO;
import com.dbs.redtrack.jpa.entity.Applications;

/**
 * @author IBM
 * 
 */
@Repository
public class ApplicationsDAOImpl extends BaseDAOImpl implements
		IApplicationsDAO {
	private static final Logger logger = Logger
			.getLogger(ApplicationsDAOImpl.class);


	@SuppressWarnings("unchecked")
	@Override
	public List<Applications> getApplicationDetails()
			throws RedTrackProcessingException {
		logger.info("Inside ApplicationsDAOImpl :: getApplicatinDetails() :: start");
		Query query = getEm().createQuery(
				DAOConstants.QUERY_APPLICATIONS_DETAILS);
		
		List<Applications> results = query.getResultList();
		
		logger.info("Inside ApplicationsDAOImpl :: getApplicatinDetails():: Query:>>> " + query);

		logger.info("Inside ApplicationsDAOImpl :: getApplicatinDetails():: Results:>>> " + results);

		return results;
	}

}
