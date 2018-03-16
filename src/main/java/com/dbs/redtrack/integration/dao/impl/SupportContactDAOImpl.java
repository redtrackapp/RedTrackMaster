package com.dbs.redtrack.integration.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.dbs.redtrack.constants.DAOConstants;
import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.integration.dao.SupportContactDAO;
import com.dbs.redtrack.jpa.entity.SupportContact;

@Repository
public class SupportContactDAOImpl extends BaseDAOImpl implements SupportContactDAO {

	private static final Logger logger = Logger.getLogger(SupportContactDAOImpl.class);
	 

	@SuppressWarnings("unchecked")
	@Override
	public List<SupportContact> getSupportContactList() throws RedTrackProcessingException {
		logger.info("Inside SupportContactDAOImpl :: getSupportContactList() ::start" );
		 
		Query query = getEm().createQuery(DAOConstants.QUERY_SUPPORT_CONTACT_LIST);		 
		
		List<SupportContact> results = query.getResultList();		
			
	 	logger.info("getSupportContactList:: ("+results+")");
		logger.info("Inside SupportContactDAOImpl :: getSupportContactList() :: end");

		return  results;
	}
}
