package com.dbs.redtrack.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dbs.redtrack.business.service.IncidentService;
import com.dbs.redtrack.constants.BusinessConstants;
import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.request.dto.ArchiveIncidentRequestDTO;
import com.dbs.redtrack.request.dto.CreateIncidentRequestDTO;
import com.dbs.redtrack.request.dto.CreateIncidentTestDTO;
import com.dbs.redtrack.request.dto.EditIncidentRequestDTO;
import com.dbs.redtrack.response.dto.GetAllIncidentResponseDTO;
import com.dbs.redtrack.response.dto.GetIncidentbyAppIDResponseDTO;
import com.dbs.redtrack.response.dto.IncidentResponseDTO;
import com.dbs.redtrack.util.property.AppConfig;
import com.dbs.redtrack.web.utilities.RequestValidator;
/**
 * 
 * @author Raymond
 *
 */
@Controller
public class IncidentController {

	private static final Logger logger = Logger.getLogger(IncidentController.class);

	@Autowired
	IncidentService incidentService;	
	
	@Autowired
	AppConfig appconfig;

	
	
	//@UserCheck(getService = BusinessConstants.CREATE_INCIDENT, isActiveUser = true, isManageRole = true)
//	@AuditLog(getService = BusinessConstants.CREATE_INCIDENT)
	@RequestMapping(value = "/createTest", method = RequestMethod.POST)
	public @ResponseBody IncidentResponseDTO createIncidentTest(@RequestBody CreateIncidentTestDTO request)
			throws RedTrackProcessingException {
		logger.info("start IncidentController :: createIncident :: start");
 
		logger.info("request: "+ request.getIncidentdesc());

		IncidentResponseDTO incidentResponseDTO = new IncidentResponseDTO();
		incidentResponseDTO.setIncidentId("111"+ request.getIncidentthreatseverity());
		incidentResponseDTO.setMessage("Hello World" + request.toString());
		incidentResponseDTO.setResponsecode("0000");
		
		logger.info("end IncidentController :: createIncident :: end");
		return incidentResponseDTO;
	}
	
	
	/**
	 * @author Raymond
	 * @param request
	 * @return
	 * @throws RedTrackProcessingException
	 */
	
	//@UserCheck(getService = BusinessConstants.CREATE_INCIDENT, isActiveUser = true, isManageRole = true)
//	@AuditLog(getService = BusinessConstants.CREATE_INCIDENT)
	@RequestMapping(value = BusinessConstants.CREATE_INCIDENT, method = RequestMethod.POST)
	public @ResponseBody IncidentResponseDTO createIncident(@RequestBody CreateIncidentRequestDTO request)
			throws RedTrackProcessingException {
		logger.info("start IncidentController :: createIncident :: start");

		 if(!RequestValidator.validateCreateIncidentRequest(request)) {	 
			 throw new RedTrackProcessingException(BusinessConstants.ERROR_00005, BusinessConstants.INVALID_REQUEST);
		 }
		IncidentResponseDTO incidentResponseDTO = incidentService.createIncident(request);
		
		logger.info("end IncidentController :: createIncident :: end");
		return incidentResponseDTO;
	}

	
	/**
	 * @author Raymond
	 * @param request
	 * @return
	 * @throws RedTrackProcessingException
	 */ 
	@RequestMapping(value = BusinessConstants.EDIT_INCIDENT, method = RequestMethod.POST)
	public @ResponseBody IncidentResponseDTO editIncident(@RequestBody EditIncidentRequestDTO request)
			throws RedTrackProcessingException {
		logger.info("start IncidentController :: editIncident :: start");
 

		 if (!RequestValidator.validateEditIncidentRequest(request)) {
			 throw new RedTrackProcessingException(BusinessConstants.ERROR_00001, BusinessConstants.INVALID_REQUEST);
		 }

		IncidentResponseDTO incidentResponseDTO = incidentService.editIncident(request); 
		logger.info("end IncidentController :: editIncident :: end");
		return incidentResponseDTO;
	}

	
	/**
	 * @author Raymond
	 * @param userotpid
	 * @param incidentid
	 * @return
	 * @throws RedTrackProcessingException
	 */ 
	@RequestMapping(value = BusinessConstants.ARCHIVE_INCIDENT, method = RequestMethod.POST)
	public @ResponseBody IncidentResponseDTO archiveIncident(@RequestParam("userotpid") String userotpid,
			@RequestParam("incidentid") String incidentid) throws RedTrackProcessingException {

		logger.info("start IncidentController :: archiveIncident :: start");
		ArchiveIncidentRequestDTO request = new ArchiveIncidentRequestDTO(userotpid, incidentid);

		 if(!RequestValidator.validateArchiveIncidentRequest(request)) {
			 throw new RedTrackProcessingException(BusinessConstants.ERROR_00001, BusinessConstants.INVALID_REQUEST);
		 }
		
		IncidentResponseDTO incidentResponseDTO = incidentService.archiveIncident(request);
 
		logger.info("start IncidentController :: archiveIncident :: end");
		return incidentResponseDTO;
	}
	
	
	/**
	 * @author Raymond
	 * @return
	 * @throws RedTrackProcessingException
	 */ 
	@RequestMapping(value = BusinessConstants.GET_ALL_INCIDENTS, method = RequestMethod.GET)
	public @ResponseBody GetAllIncidentResponseDTO getallincidents() throws RedTrackProcessingException {
		logger.info("start IncidentController :: getallincidents :: start");
//		@RequestParam("userotpid") String userotpid
//		if(!RequestValidator.validateGetAllIncidents(userotpid)) {
//			throw new RedTrackProcessingException(BusinessConstants.ERROR_00005, BusinessConstants.INVALID_REQUEST); 	
//		}
		String userotpid ="";
		GetAllIncidentResponseDTO getAllIncidentResponseDTO = incidentService.getallincidents(userotpid);

		logger.info("end IncidentController :: getallincidents :: end");
		return getAllIncidentResponseDTO;
	}

	
	/**
	 * @author Raymond
	 * @param userotpid
	 * @return
	 * @throws RedTrackProcessingException
	 */ 
	@RequestMapping(value = BusinessConstants.GET_ALL_ARCHIVED_INCIDENTS, method = RequestMethod.GET)
	public @ResponseBody GetAllIncidentResponseDTO getAllArchivedincidents(@RequestParam("userotpid") String userotpid)
			throws RedTrackProcessingException {
		logger.info("start IncidentController :: getallincidents :: start(" + userotpid + ")");

		GetAllIncidentResponseDTO getAllIncidentResponseDTO = incidentService.getAllArchivedincidents(userotpid);

		logger.info("end IncidentController :: getallincidents :: end");
		return getAllIncidentResponseDTO;
	}
	
	
	/**
	 * @author Raymond
	 * @param appid
	 * @return
	 * @throws RedTrackProcessingException
	 */ 
	@RequestMapping(value = BusinessConstants.GET_INCIDENT_BY_APPID, method = RequestMethod.GET)
	public @ResponseBody GetIncidentbyAppIDResponseDTO getIncidentByAppID(@RequestParam("appid") String appid)
			throws RedTrackProcessingException {
		logger.info("start IncidentController :: getIncidentByAppID :: start(" + appid + ")");

		GetIncidentbyAppIDResponseDTO getIncidentbyAppIDResponseDTO = incidentService.getIncidentByAppID(appid);

		logger.info("end IncidentController :: getIncidentByAppID :: end");
		return getIncidentbyAppIDResponseDTO;
	}
	
	
	 
}
