package com.dbs.redtrack.integration.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.jpa.entity.CallHistory;
 

@Repository
public interface CallHistoryDAO {

	public List<CallHistory> findCallHistoryByUser(String strID) throws RedTrackProcessingException;

	public boolean saveCallHistory(CallHistory callHistory) throws RedTrackProcessingException;

	public List<CallHistory> getcallHistory(String userOTPID) throws RedTrackProcessingException;
	
	
	


	
	
}
