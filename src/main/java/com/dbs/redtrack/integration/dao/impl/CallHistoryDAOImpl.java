package com.dbs.redtrack.integration.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dbs.redtrack.constants.DAOConstants;
import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.integration.dao.CallHistoryDAO;
import com.dbs.redtrack.jpa.entity.CallHistory;

@Repository
public class CallHistoryDAOImpl extends BaseDAOImpl implements CallHistoryDAO {

	private static final Logger logger = Logger.getLogger(CallHistoryDAOImpl.class);
	
	
	
	/**
	 * comments here
	 */
	@SuppressWarnings("unchecked")
	@Override	
	public List<CallHistory> findCallHistoryByUser(String strUserID) throws RedTrackProcessingException {
		logger.info("Inside CallHistoryDAOImpl :: findCallHistoryByUser() :: start");
		

		Query query = getEm().createQuery(DAOConstants.QUERY_CALL_HISTORY_BY_USER);		 
		query.setParameter("strUserID", strUserID);

		List<CallHistory> results = query.getResultList();		
			
		logger.info("findTempUserByPhoneNumber:: userTemp ("+results+")");		
		 
		logger.info("Inside CallHistoryDAOImpl :: findCallHistoryByUser() :: end");
		
		return results;
	}
	
	
	/**
	 * comments here
	 */
		
	private CallHistory findCallHistoryByID(String strID) throws RedTrackProcessingException {
		logger.info("Inside CallHistoryDAOImpl :: findCallHistoryByID() :: start");
		 
		CallHistory callHistory = (CallHistory)find(CallHistory.class, Long.parseLong(strID));
 	 
		logger.info("findCallHistoryByID:: CallHistory ("+callHistory+")");		
		 
		logger.info("Inside CallHistoryDAOImpl :: findCallHistoryByID() :: end");
		
		return callHistory;
	}
	
	 
	
	/**
	 * comments here
	 */
	@Override
	@Transactional
	public boolean saveCallHistory(CallHistory callHistory) throws RedTrackProcessingException {

		boolean isSaved = false;
		logger.info("Inside CallHistoryDAOImpl :: saveCallHistory() :: start");
		CallHistory oldCallHistory = findCallHistoryByID(String.valueOf(callHistory.getId()));
		
		
		if (null == oldCallHistory) {
			save(callHistory);
			isSaved = true;
		} else {		 
			oldCallHistory.setParticipantCode(callHistory.getParticipantCode());
//			todo: add more fields			
			save(oldCallHistory);
			isSaved = true;
		}
		
		logger.info("Inside CallHistoryDAOImpl :: saveCallHistory() :: end");
		return isSaved;
	}
	
 

	@SuppressWarnings("unchecked")
	@Override
	public List<CallHistory> getcallHistory(String userOTPID) throws RedTrackProcessingException {
		logger.info("Inside CallHistoryDAOImpl :: getcallHistory() :: ("+userOTPID+") ::start" );
		 
		Query query = getEm().createQuery(DAOConstants.QUERY_CALL_HISTORY_BY_USER);		 
		query.setParameter("userOTPID", Long.parseLong(userOTPID)); 
		
		List<CallHistory> results = query.getResultList();		
			
	 	logger.info("getcallHistory:: CallHistory ("+results+")");
		logger.info("Inside CallHistoryDAOImpl :: getcallHistory() :: end");

		return  results;
	}
}
