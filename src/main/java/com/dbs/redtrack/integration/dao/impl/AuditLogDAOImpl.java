package com.dbs.redtrack.integration.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dbs.redtrack.constants.DAOConstants;
import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.integration.dao.AuditLogDAO;
import com.dbs.redtrack.jpa.entity.AuditLog; 

@Repository
public class AuditLogDAOImpl extends BaseDAOImpl implements AuditLogDAO {

	private static final Logger logger = Logger.getLogger(AuditLogDAOImpl.class);
	
	

	@Override
	@Transactional
	public void saveAuditLog(AuditLog auditLog) throws RedTrackProcessingException {
		
		logger.info("Inside IncidentDAOImpl :: saveAuditLog() :: start");
	//	UserTemp userTemp = findIncidentById(incidentTemp.());
		
		if (null != auditLog) {
			save(auditLog);
			logger.info("Inside AuditLogDAOImpl :: saveAuditLog() ::  AuditLog saved");
		} else {
			logger.info("Inside AuditLogDAOImpl :: saveAuditLog() :: end");	
		}		 
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AuditLog> findAuditLogByUser(String userID) throws RedTrackProcessingException {
		logger.info("Inside AuditLogDAOImpl ::  findAuditLogByUser :: start");
		Query query = getEm().createQuery(DAOConstants.QUERY_AUDIT_LOG_BY_USERID);		 
		query.setParameter("userID", Long.parseLong(userID));
		
		List<AuditLog> results = query.getResultList();
		logger.info("Existing Entity from DB : " +  results);	
		logger.info("Inside AuditLogDAOImpl :: findAuditLogByUser() :: end");
		return  results;
	}
 
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AuditLog> findAuditLogByDate(String strDate) throws RedTrackProcessingException {
		logger.info("Inside AuditLogDAOImpl ::  findAuditLogByDate :: start");
//		 select * from incident where  DATE_FORMAT(date_created, '%Y-%m-%d') = '2017-06-09'
		Query query = getEm().createQuery(DAOConstants.QUERY_AUDIT_LOG_BY_DATE);		 
		query.setParameter("dateCreated", strDate);
		
		List<AuditLog> results = query.getResultList();
		logger.info("Existing Entity from DB : " +  results);	
		logger.info("Inside AuditLogDAOImpl :: findAuditLogByDate() :: end");
		return  results;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<AuditLog> findAuditLogByUserAndDate(String userID, String strDate) throws RedTrackProcessingException {
		logger.info("Inside AuditLogDAOImpl ::  findAuditLogByDate :: start");
		//format: '2017-06-09'
		Query query = getEm().createQuery(DAOConstants.QUERY_AUDIT_LOG_BY_USER_AND_DATE);		 
		query.setParameter("strDate", Long.parseLong(userID));
		query.setParameter("dateCreated", strDate);
		
		List<AuditLog> results = query.getResultList();
		logger.info("Existing Entity from DB : " +  results);	
		logger.info("Inside AuditLogDAOImpl :: findAuditLogByDate() :: end");
		return  results;
	}	
}
