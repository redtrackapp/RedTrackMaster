package com.dbs.redtrack.integration.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dbs.redtrack.constants.BusinessConstants;
import com.dbs.redtrack.constants.DAOConstants;
import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.integration.dao.IncidentDAO;
import com.dbs.redtrack.jpa.entity.ArchiveIncident;
import com.dbs.redtrack.jpa.entity.Incident;
import com.dbs.redtrack.jpa.entity.IncidentTemp;

@Repository
public class IncidentDAOImpl extends BaseDAOImpl implements IncidentDAO {

	private static final Logger logger = Logger.getLogger(IncidentDAOImpl.class);
	
	

	@Override	
	@SuppressWarnings("unchecked")
	public Incident findIncidentById(String incidentId) throws RedTrackProcessingException {
		logger.info("Inside IncidentDAOImpl :: findIncidentById() :: start");
		Incident incident = null;
		Query query = getEm().createQuery(DAOConstants.QUERY_INCIDENT_BY_INCIDENT_ID);		 
		query.setParameter("strincidentId", incidentId);
		
		List<Incident> results = query.getResultList();		
			
		if (null != results && results.size() != 0) {
			incident =  results.get(0);
			logger.info("Existing Entity from DB for Incident: " +  results);	
		}
		
		logger.info("Inside IncidentDAOImpl :: findIncidentById() :: end");

		return  incident;
	}
	
	
	/**
	 * comments here
	 */
	@Override	
	public Incident findIncidentByID(long incidentID) throws RedTrackProcessingException {
		logger.info("Inside UserRegistrationDAOImpl :: findUserTempByID() :: start");
		

		Incident incident = (Incident)find(Incident.class, incidentID);
		
		logger.info("Existing Entity from DB for incident: " + incident);	
		logger.info("Inside IncidentDAOImpl :: findIncidentByID() :: end");
		
		return incident;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public ArchiveIncident findArchiveIncidentById(String incidentId) throws RedTrackProcessingException {
		logger.info("Inside IncidentDAOImpl :: findArchiveIncidentById() :: start");
		ArchiveIncident archiveIncident = null;
		Query query = getEm().createQuery(DAOConstants.QUERY_INCIDENT_BY_INCIDENT_ID);		 
		query.setParameter("strincidentId", incidentId);
		
		List<ArchiveIncident> results = query.getResultList();		
			
		if (null != results && results.size() != 0) {
			archiveIncident =  results.get(0);
			logger.info("Existing Entity from DB for ArchiveIncident: " +  results);	
		}
		
		logger.info("Inside IncidentDAOImpl :: findArchiveIncidentById() :: end");

		return  archiveIncident;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public ArchiveIncident findallarchivedIncidentsByotpId(String userotpid) throws RedTrackProcessingException {
		logger.info("Inside IncidentDAOImpl :: findallarchivedIncidentsByotpId() :: start");
		ArchiveIncident archiveIncident = null;
		Query query = getEm().createQuery(DAOConstants.QUERY_ARCHIVE_INCIDENT_BY_USEROTP_ID);		 
		query.setParameter("struserotpid", userotpid);
		
		List<ArchiveIncident> results = query.getResultList();		
			
		if (null != results && results.size() != 0) {
			archiveIncident =  results.get(0);
			logger.info("Existing Entity from DB for ArchiveIncident: " +  results);	
		}
		
		logger.info("Inside IncidentDAOImpl :: findallarchivedIncidentsByotpId() :: end");

		return  archiveIncident;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Incident> getallincidentsByStatus(String incidentStatus)throws RedTrackProcessingException {
	
		logger.info("Inside IncidentDAOImpl :: getallincidentsByStatus() :: start");
			 
		Query query = getEm().createQuery(DAOConstants.QUERY_ALL_INCIDENT_BY_STATUS);
		query.setParameter("incidentStatus", incidentStatus);
		
		List<Incident> results = query.getResultList();		
		return results;
	}	 
	

	@Override
	@Transactional
	public void createIncident(Incident incidentTemp)throws RedTrackProcessingException {
		
		logger.info("Inside IncidentDAOImpl :: createIncident() :: start");
	//	UserTemp userTemp = findIncidentById(incidentTemp.());
		
		if (null != incidentTemp) {
			save(incidentTemp);
			logger.info("Inside IncidentDAOImpl :: createIncident() :: incident saved");
		} else {
			logger.info("Inside IncidentDAOImpl :: createIncident() :: end");	
		}		 
	}
	
	
	@Override
	@Transactional
	public void saveUpdateIncident(Incident newIncident)throws RedTrackProcessingException {
 

		logger.info("Inside IncidentDAOImpl :: registerTempUser() :: start");
		//UserTemp userTemp = findTempUserByPhoneNumber(tempUser.getPhoneNumber(), tempUser.getCountryCode());
		//changed 19.07.17 updated incidentid to be manually enteredd by user
		Incident incident = findIncidentByID(newIncident.getIncidentId());
		//Incident incident = findIncidentById(newIncident.getIncidentId());
		
		
		if (null == incident) {
			save(newIncident);
			logger.info("Inside IncidentDAOImpl :: saveUpdateIncident() :: incident saved successfully");
		} else {			 
			removeChild(incident); 
			incident.setDateUpdated(new Date());
			incident.setIncidenttitle(newIncident.getIncidenttitle());
			incident.setIncidentdesc(newIncident.getIncidentdesc());
			incident.setIncidentresolution(newIncident.getIncidentresolution());
			incident.setIncidentthreatseverity(newIncident.getIncidentthreatseverity());
			incident.setIncidentcategory(newIncident.getIncidentcategory());
			incident.setUserotpid(newIncident.getUserotpid());
			incident.setIncidentconfnum(newIncident .getIncidentconfnum());
			incident.setIncidentparticipantcode(newIncident.getIncidentparticipantcode()); 
			incident.setIncidentStatus(newIncident.getIncidentStatus());
			incident.setUpdatedBy(newIncident.getUpdatedBy());
 
			incident.setIncidentMap(newIncident.getIncidentMap());

			save(incident);  

			logger.info("Inside IncidentDAOImpl :: saveUpdateIncident() :: incident updated successfully");
		}
		logger.info("Inside IncidentDAOImpl :: saveUpdateIncident() :: end");
	}
	
	
	@Transactional
	private int removeChild(Incident newIncident) {
		
		logger.info("Inside IncidentDAOImpl :: removeChild() :: start"); 
		Query query = getEm().createQuery(DAOConstants.DELETE_ICIDENT_BY_ID);		 
		query.setParameter("incidentID", newIncident);
		
		int results = query.executeUpdate();		
			
		if (results > 0) {
			logger.info("Temporary User delete is Done...");
		}
		
		logger.info("Inside IncidentDAOImpl :: removeChild() :: end");

		return  results;
	}		 
	
	//UPDATE_ICIDENT_BY_ID = "Update Incident a set a.status=: strStatus where a.incident =:incidentID";

	@Override
	@Transactional
	public int archiveIncident(String strincidentid, String struserotpid)	throws RedTrackProcessingException {
		
		logger.info("Inside IncidentDAOImpl :: archiveIncident() :: start"); 
		Query query = getEm().createQuery(DAOConstants.UPDATE_ICIDENT_BY_ID);		 
		query.setParameter("strStatus", BusinessConstants.STATUS_ARCIVED);
		query.setParameter("incidentStatus", BusinessConstants.STATUS_CLOSED);
		query.setParameter("updatedBy", Long.parseLong(struserotpid));
		//changed 19.07.17 updated incidentid to be manually enteredd by user
		query.setParameter("incidentID", Long.parseLong(strincidentid));
		//query.setParameter("incidentID", strincidentid);
		
		int results = query.executeUpdate();		
			
		if (results > 0) {
			logger.info("Archive of Incident is Done...");
		}
		
		logger.info("Inside IncidentDAOImpl :: archiveIncident() :: end");

		return  results;
			
	}
	
	

	@Override
	@Transactional
	public Incident editIncident(IncidentTemp incidentTemp)	throws RedTrackProcessingException {
		
		
		logger.info("Inside IncidentDAOImpl :: editIncident() :: start");
		Incident incident = findIncidentById(incidentTemp.getIncidentId());
			
			if (null != incident) {
				//changed 19.07.17 updated incidentid to be manually enteredd by user
				incident.setIncidentId(Long.parseLong(incidentTemp.getIncidentId()));
				//incident.setIncidentId(incidentTemp.getIncidentId());
				incident.setIncidentcategory(incidentTemp.getIncidentcategory());
				incident.setIncidentconfnum(incidentTemp.getIncidentconfnum());
				incident.setIncidentdesc(incidentTemp.getIncidentdesc());
				incident.setIncidentparticipantcode(incidentTemp.getIncidentparticipantcode());
				incident.setIncidentresolution(incidentTemp.getIncidentresolution());
				incident.setIncidentthreatseverity(incidentTemp.getIncidentthreatseverity());
				incident.setIncidenttitle(incidentTemp.getIncidenttitle());
				incident.setUserotpid(Long.parseLong(incidentTemp.getUserotpid()));
				
				save(incident);
				logger.info("Inside IncidentDAOImpl :: editIncident() :: editIncident saved");
			} else {
				logger.info("Inside IncidentDAOImpl :: editIncident() :: end");	
			}
			
			return incident;
		
			}


	
	@SuppressWarnings("unchecked")
	@Override
	public List<Incident> getallincidentMessages(String strUserotpid) throws RedTrackProcessingException {
		logger.info("Inside IncidentDAOImpl :: getallincidentMessages() :: ("+strUserotpid+") ::start" );
		 
		Query query = getEm().createQuery(DAOConstants.QUERY_INCIDENT_BY_USER);		 
		//query.setParameter("strUserotpid", Long.parseLong(strUserotpid));
		query.setParameter("strUserotpid", strUserotpid);
		
		List<Incident> results = query.getResultList();		
			
	 	logger.info("getallincidentMessages:: CallHistory ("+results+")");
		logger.info("Inside IncidentDAOImpl :: getallincidentMessages() :: end");

		return  results;
	}
	
	
	@Override
	@Transactional
	public List<String> getallincidentMessages(String strincidentid,String struserotpid, String strchatmessageid)throws RedTrackProcessingException {
		
		logger.info("Inside IncidentDAOImpl :: getallincidentMessages() :: start");
		Incident incident = findIncidentById(strincidentid);
		List<String> messageString=new ArrayList<String>();
		
		if (null != incident) {
				
			messageString.add("Chat Message need to get");
			messageString.add("Chat Message need to get2");
			
				logger.info("Inside IncidentDAOImpl :: getallincidentMessages() ::  messages reterive");
			} else {
				logger.info("Inside IncidentDAOImpl :: getallincidentMessages() :: end");	
			}
			
			return messageString;
		
	}


 


//	@SuppressWarnings("unchecked")
//	@Override
//	@Transactional
//	public List<Incident> getallincidents(String userdevictokenstr,String userotpid, String accessprofileid)throws RedTrackProcessingException {
//	
//		logger.info("Inside IncidentDAOImpl :: getallincidents() :: start");
//		//	ArchiveIncident archiveIncident = findallarchivedIncidentsByotpId(struserotpid);
//			 
//			Query query = getEm().createQuery(DAOConstants.QUERY_ALL_INCIDENT_BY_USERDEVICTOKENSTR_USEROTPID);		 
//			query.setParameter("struserdevictokenstr", userdevictokenstr);
//			query.setParameter("struserotpid", userotpid);
//			
//			List<Incident> results = query.getResultList();		
//				
//
//			return results;
//	}	 
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Incident> getallincidents()throws RedTrackProcessingException {
	
		logger.info("Inside IncidentDAOImpl :: getallincidents() :: start");
		//	ArchiveIncident archiveIncident = findallarchivedIncidentsByotpId(struserotpid);
			 
			Query query = getEm().createQuery(DAOConstants.QUERY_ALL_INCIDENT);		 
			query.setParameter("incidentStatus", "Open");
			
			List<Incident> results = query.getResultList();		
				

			return results;
	}	 
		
	
		
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Incident> getallArchivedincidents(String struserotpid)throws RedTrackProcessingException {
	
		logger.info("Inside IncidentDAOImpl :: getallincidents() :: start("+ struserotpid+")");
		//	ArchiveIncident archiveIncident = findallarchivedIncidentsByotpId(struserotpid);
		
//		Query query = getEm().createQuery(DAOConstants.QUERY_ARCHIVE_INCIDENT_BY_USEROTP_ID);		 
		Query query = getEm().createQuery(DAOConstants.QUERY_ARCHIVE_INCIDENT);
		query.setParameter("strStatus", BusinessConstants.STATUS_ARCIVED);
		//query.setParameter("struserotpid", Long.parseLong(struserotpid));
		List<Incident> results = query.getResultList();		

		logger.info("Inside IncidentDAOImpl :: getallincidents() :: end("+ results.size()+")");

		return results;
	}	 
	
	
 
	@SuppressWarnings("unchecked")
	@Override	
	public Incident getIIncidentByAppID(String strAppID)throws RedTrackProcessingException {
	
		logger.info("Inside IncidentDAOImpl :: getIIncidentByAppID() :: start("+ strAppID+")");
		 
		Query query = getEm().createQuery(DAOConstants.QUERY_INCIDENT_BY_APPLICATION_ID);		 
		query.setParameter("incidentStatus", BusinessConstants.STATUS_OPEN);
		query.setParameter("applicationCode",  strAppID);
		
		List<Incident> results= query.getResultList();		
		
		Incident result = null;
		if (null != results && results.size()!=0) {
			logger.info("getIIncidentByAppID size: ("+ results.size()+")");
				result= results.get(0);
		}   
		logger.info("Inside IncidentDAOImpl :: getIIncidentByAppID() :: end(Incident:"+ result+")");

		return result;
	}	 
 
	 




	
	

	  
	
	
	
	


	
}
