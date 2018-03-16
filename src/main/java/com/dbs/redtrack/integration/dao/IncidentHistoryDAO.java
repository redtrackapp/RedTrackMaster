package com.dbs.redtrack.integration.dao;

import org.springframework.stereotype.Repository;

import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.jpa.entity.InicidentHistory;

@Repository
public interface IncidentHistoryDAO {

	public InicidentHistory findIncidentHistoryByID(long incidentID) throws RedTrackProcessingException;

	public void updateIncidentHistory(String strIncidentID, String strStatus) throws RedTrackProcessingException;

	public void saveIncidentHistory(InicidentHistory inicidentHistory) throws RedTrackProcessingException;

	
}
