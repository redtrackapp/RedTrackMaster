package com.dbs.redtrack.integration.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.jpa.entity.AuditLog;
import com.dbs.redtrack.jpa.entity.Incident;
import com.dbs.redtrack.jpa.entity.IncidentTemp;
 

@Repository
public interface AuditLogDAO {

	public void saveAuditLog(AuditLog auditLog) throws RedTrackProcessingException;

	public List<AuditLog> findAuditLogByUser(String userID) throws RedTrackProcessingException;

	public List<AuditLog> findAuditLogByDate(String strDate) throws RedTrackProcessingException;

	public List<AuditLog> findAuditLogByUserAndDate(String userID, String strDate) throws RedTrackProcessingException;
	
	 
	
	
}
