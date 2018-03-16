package com.dbs.redtrack.integration.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.integration.dao.IncidentHistoryDAO;
import com.dbs.redtrack.jpa.entity.InicidentHistory;

@Repository
public class IncidentHistoryDAOImpl extends BaseDAOImpl implements IncidentHistoryDAO {

	private static final Logger logger = Logger.getLogger(IncidentHistoryDAOImpl.class);
	 
	
	/**
	 * comments here
	 */
	@Override	
	public InicidentHistory findIncidentHistoryByID(long incidentID) throws RedTrackProcessingException {
		logger.info("Inside IncidentHistoryDAOImpl :: findIncidentHistoryByID() :: start");
		

		InicidentHistory inicidentHistory = (InicidentHistory) find(InicidentHistory.class, incidentID);
		
		logger.info("Existing Entity from DB for findIncidentHistoryByID: (" + inicidentHistory+ ")");	
		logger.info("Inside IncidentHistoryDAOImpl :: findIncidentHistoryByID() :: end");
		
		return inicidentHistory;
	}
	
	
	    
	@Override
	@Transactional
	public void updateIncidentHistory(String strIncidentID, String strStatus)throws RedTrackProcessingException {
 
		logger.info("Inside IncidentHistoryDAOImpl :: updateIncidentHistory() :: start:("+ strIncidentID +") strStatus:("+strStatus+")");
		InicidentHistory inicidentHistory = findIncidentHistoryByID(Long.parseLong(strIncidentID));
		 
		if (null != inicidentHistory) {
			inicidentHistory.setStatus(strStatus);
			save(inicidentHistory); 
			logger.info("Inside IncidentHistoryDAOImpl :: updateIncidentHistory() :: saved successfully");
		}   else {			
			logger.info("Inside IncidentDAOImpl :: updateIncidentHistory() :: unable to  save strIncidentID("+ strIncidentID+")");
		}
		logger.info("Inside IncidentDAOImpl :: updateIncidentHistory() :: end");
	}
	  

	@Override
	@Transactional
	public void saveIncidentHistory(InicidentHistory inicidentHistory) throws RedTrackProcessingException {
 
		logger.info("Inside IncidentHistoryDAOImpl :: saveIncidentHistory() :: start : inicidentHistory("+ inicidentHistory +")");
		 
		if (null != inicidentHistory) {
			save(inicidentHistory); 
			logger.info("Inside IncidentHistoryDAOImpl :: saveIncidentHistory() :: saved successfully");
		}   else {			
			logger.info("Inside IncidentDAOImpl :: saveIncidentHistory() :: unable to  save ");
		}
		logger.info("Inside IncidentDAOImpl :: saveIncidentHistory() :: end");
	}
	
	
}
