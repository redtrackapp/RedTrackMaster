/**
 * 
 */
package com.dbs.redtrack.business.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.redtrack.business.service.SupportContactService;
import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.integration.dao.SupportContactDAO;
import com.dbs.redtrack.jpa.entity.CallHistory;
import com.dbs.redtrack.jpa.entity.SupportContact;
import com.dbs.redtrack.response.dto.CallHistoryHelper;
import com.dbs.redtrack.response.dto.GetCallHistoryResponseDTO;
import com.dbs.redtrack.response.dto.SupportContactHelper;
import com.dbs.redtrack.response.dto.SupportContactResponseDTO;
import com.dbs.redtrack.web.utilities.DateTimeConstants;
import com.dbs.redtrack.web.utilities.DateTimeUtil;

@Service
public class SupportContactImp implements SupportContactService {
	private static final Logger logger = Logger.getLogger(SupportContactImp.class);

	@Autowired
	SupportContactDAO supportContactDAO; 
	
 

	/**
	 * Created by: Raymond
	 * Method name: saveCallHistory()
	 *  
	 */
	@Override
	public SupportContactResponseDTO getSupportContactList() throws RedTrackProcessingException {
		logger.info("SupportContactImp : getSupportContactList : start");
		SupportContactResponseDTO supportContactResponseDTO = new SupportContactResponseDTO();	
		SupportContactHelper supportContactHelper = null;
		List<SupportContactHelper> supportContactList = new ArrayList<SupportContactHelper>();
		List<SupportContact> contactList = supportContactDAO.getSupportContactList();
		
		for(SupportContact supportContact: contactList){
			supportContactHelper = new SupportContactHelper();
			
			supportContactHelper.setName(supportContact.getName());
			supportContactHelper.setContactNumber(supportContact.getContactNumber());
			supportContactHelper.setEmail(supportContact.getEmail());
			supportContactHelper.setImageName(supportContact.getImageName());
			supportContactHelper.setSupportedApplication(supportContact.getSupportedApplication());
			supportContactHelper.setStatus(supportContact.getStatus());
			supportContactList.add(supportContactHelper);
		}
		
		supportContactResponseDTO.setSupportContactList(supportContactList);
		
		logger.info("SupportContactImp : getSupportContactList : end");
		return supportContactResponseDTO;
	}
	
	
	
	 
}
