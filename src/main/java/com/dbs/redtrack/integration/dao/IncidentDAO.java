package com.dbs.redtrack.integration.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.jpa.entity.Incident;
import com.dbs.redtrack.jpa.entity.IncidentTemp;
 

@Repository
public interface IncidentDAO {
	
	
	public  void createIncident(Incident incidentTemp) throws RedTrackProcessingException;
	public void saveUpdateIncident(Incident incidentTemp) throws RedTrackProcessingException;
	public Incident findIncidentByID(long incidentID) throws RedTrackProcessingException;
	public  int archiveIncident(String strincidentid, String struserotpid) throws RedTrackProcessingException;
	public List<Incident> getallincidents() throws RedTrackProcessingException; 
	public List<Incident> getallArchivedincidents(String struserotpid) throws RedTrackProcessingException;
	
	public List<Incident> getallincidentMessages(String userOTPID) throws RedTrackProcessingException;
	
	public Incident editIncident(IncidentTemp incidentTemp) throws RedTrackProcessingException;
	
	public List<String> getallincidentMessages(String strincidentid, String struserotpid, String strchatmessageid) throws RedTrackProcessingException;
	
	public List<Incident> getallincidentsByStatus(String incidentStatus) throws RedTrackProcessingException;
	public Incident getIIncidentByAppID(String strAppID) throws RedTrackProcessingException;
	public Incident findIncidentById(String incidentId) throws RedTrackProcessingException;
	
	
	
	
}
