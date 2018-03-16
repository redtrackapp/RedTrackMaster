/**
 * 
 */
package com.dbs.redtrack.business.service;

import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.request.dto.ArchiveIncidentRequestDTO;
import com.dbs.redtrack.request.dto.CreateIncidentRequestDTO;
import com.dbs.redtrack.request.dto.EditIncidentRequestDTO;
import com.dbs.redtrack.response.dto.GetAllIncidentResponseDTO;
import com.dbs.redtrack.response.dto.GetIncidentbyAppIDResponseDTO;
import com.dbs.redtrack.response.dto.IncidentResponseDTO;

public interface IncidentService {
	
	public IncidentResponseDTO createIncident(CreateIncidentRequestDTO createIncidentRequestDTO) throws RedTrackProcessingException;
	
	public IncidentResponseDTO editIncident(EditIncidentRequestDTO editIncidentRequestDTO) throws RedTrackProcessingException;	
	
	public GetAllIncidentResponseDTO getallincidents(String userotpid) throws RedTrackProcessingException;
	
	public IncidentResponseDTO archiveIncident(ArchiveIncidentRequestDTO archiveIncidentRequestDTO) throws RedTrackProcessingException;
		
	public GetAllIncidentResponseDTO getAllArchivedincidents(String strUserOtpID) throws RedTrackProcessingException;

	public GetIncidentbyAppIDResponseDTO getIncidentByAppID(String strAppID) throws RedTrackProcessingException;
	
	//
	//public IncidentResponseDTO getallarchivedIncidents(GetAllArchivedIncidentsRequestDTO getAllArchivedIncidentsRequestDTO) throws RedTrackProcessingException;
	


}
