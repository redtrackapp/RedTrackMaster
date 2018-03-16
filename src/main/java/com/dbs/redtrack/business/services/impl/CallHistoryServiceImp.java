/**
 * 
 */
package com.dbs.redtrack.business.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.redtrack.business.service.CallHistoryService;
import com.dbs.redtrack.constants.BusinessConstants;
import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.integration.dao.CallHistoryDAO;
import com.dbs.redtrack.jpa.entity.CallHistory;
import com.dbs.redtrack.jpa.entity.User;
import com.dbs.redtrack.request.dto.CallHistoryRequestDTO;
import com.dbs.redtrack.response.dto.CallHistoryHelper;
import com.dbs.redtrack.response.dto.CallHistoryResponseDTO;
import com.dbs.redtrack.response.dto.GetCallHistoryResponseDTO;
import com.dbs.redtrack.web.utilities.DateTimeConstants;
import com.dbs.redtrack.web.utilities.DateTimeUtil;

@Service
public class CallHistoryServiceImp implements CallHistoryService {
	private static final Logger logger = Logger.getLogger(CallHistoryServiceImp.class);

	@Autowired
	CallHistoryDAO callHistoryDAO; 
	

	/**
	 * Created by: Raymond
	 * Method name: saveCallHistory()
	 * 
	 */
	@Override
	public CallHistoryResponseDTO saveCallHistory(CallHistoryRequestDTO callHistoryRequestDTO) throws RedTrackProcessingException {
		logger.info("CallHistoryServiceImp : saveCallHistory : start");
		CallHistoryResponseDTO callHistoryResponseDTO = new CallHistoryResponseDTO();	
		
		CallHistory callHistory = new CallHistory(); 
		
		long otpID = DateTimeUtil.getCurrentTimeStamp();//0L; //milliSecondsotpID = dte.getTime();
		callHistory.setId(otpID);
		callHistory.setCallHistoryname(callHistoryRequestDTO.getCallhistname());
		callHistory.setCallHistoryDate(DateTimeUtil.getCurrentDate());
		callHistory.setCallHistoryTime(DateTimeUtil.getCurrentTimeString());
		callHistory.setDateCreated(DateTimeUtil.getCurrentDate());
		
		//callHistory.setIncidentID(Long.parseLong(callHistoryRequestDTO.getIncidentid()));
		callHistory.setIncidentID(callHistoryRequestDTO.getIncidentid());
		callHistory.setIncidentName(callHistoryRequestDTO.getIncidentname());
		callHistory.setParticipantCode(Long.parseLong(callHistoryRequestDTO.getParticipantcode()));
		callHistory.setParticipantPhoneNumber(callHistoryRequestDTO.getPhonenumber());
		callHistory.setUserOTPID(Long.parseLong(callHistoryRequestDTO.getUserotpid()));
		callHistory.setCategoryName(callHistoryRequestDTO.getCategoryname());
		
		boolean isSaved = callHistoryDAO.saveCallHistory(callHistory);
		
		if (isSaved){ 
			callHistoryResponseDTO.setResponsecode("0000");
			callHistoryResponseDTO.setMessage(BusinessConstants.USER_UPDATE_SUCCESSFULL);
			logger.info("validateOTP:: saveCallHistory:: "+ BusinessConstants.USER_UPDATE_SUCCESSFULL);
		} else {			
			callHistoryResponseDTO.setResponsecode("0001");
			callHistoryResponseDTO.setMessage(BusinessConstants.ERROR_UPDATING_USER);
			logger.info("validateOTP:: saveCallHistory:: "+ BusinessConstants.ERROR_UPDATING_USER);
		}
		 
		logger.info("CallHistoryServiceImp : saveCallHistory : end");
		return callHistoryResponseDTO;
	}
	
	

	/**
	 * Created by: Raymond
	 * Method name: saveCallHistory()
	 *  
	 */
	@Override
	public GetCallHistoryResponseDTO getCallHistory(String userOTPID) throws RedTrackProcessingException {
		logger.info("CallHistoryServiceImp : getCallHistory : start ("+ userOTPID +")");
		GetCallHistoryResponseDTO getCallHistoryResponseDTO = new GetCallHistoryResponseDTO();	
		CallHistoryHelper callHistoryResponse = null;
		List<CallHistoryHelper> historyList = new ArrayList<CallHistoryHelper>();
		List<CallHistory> callHistoryList = callHistoryDAO.getcallHistory(userOTPID);
		
		for(CallHistory callHistory: callHistoryList){
			callHistoryResponse = new CallHistoryHelper();
			callHistoryResponse.setId(String.valueOf(callHistory.getId()));
			callHistoryResponse.setUserOTPID(String.valueOf(callHistory.getUserOTPID()));			
			callHistoryResponse.setCallHistoryname(callHistory.getCallHistoryname());
			callHistoryResponse.setCategoryName(callHistory.getCategoryName());
			callHistoryResponse.setCallHistoryDate(DateTimeUtil.convertDateToString(callHistory.getCallHistoryDate(), DateTimeConstants.TIMESTAMP_PATTERN_12));
			callHistoryResponse.setCallHistoryTime(String.valueOf(callHistory.getCallHistoryTime()));
			callHistoryResponse.setIncidentID(String.valueOf(callHistory.getIncidentID()));
			callHistoryResponse.setIncidentName(callHistory.getIncidentName());
			callHistoryResponse.setParticipantCode(String.valueOf(callHistory.getParticipantCode()));
			callHistoryResponse.setParticipantPhoneNumber(callHistory.getParticipantPhoneNumber());
			callHistoryResponse.setDateCreated(String.valueOf(callHistory.getDateCreated()));
			historyList.add(callHistoryResponse);
		}
		
		getCallHistoryResponseDTO.setCallHistory(historyList);
		
		logger.info("CallHistoryServiceImp : getCallHistory : end");
		return getCallHistoryResponseDTO;
	}
	
	
	
	


	/**
	 * Created by: Raymond
	 * Method name: saveCallHistory()
	 * 
	 */
	@Override
	public GetCallHistoryResponseDTO getCallHistoryTest(String userOTPID) throws RedTrackProcessingException {
		logger.info("CallHistoryServiceImp : getCallHistory : start ("+ userOTPID +")");
		GetCallHistoryResponseDTO getCallHistoryResponseDTO = new GetCallHistoryResponseDTO();	
		CallHistoryHelper callHistoryResponse = null;
		List<CallHistoryHelper> historyList = new ArrayList<CallHistoryHelper>();
		List<CallHistory> callHistoryList = callHistoryDAO.getcallHistory(userOTPID);
		User tmpUser = null;
		for(CallHistory callHistory: callHistoryList){
			callHistoryResponse = new CallHistoryHelper();
			callHistoryResponse.setId(String.valueOf(callHistory.getId()));
			callHistoryResponse.setUserOTPID(String.valueOf(callHistory.getUserOTPID()));			
			callHistoryResponse.setCallHistoryname(callHistory.getCallHistoryname());
			callHistoryResponse.setCategoryName(callHistory.getCategoryName());
			callHistoryResponse.setCallHistoryDate(String.valueOf(callHistory.getCallHistoryDate()));
			callHistoryResponse.setCallHistoryTime(String.valueOf(callHistory.getCallHistoryTime()));
			callHistoryResponse.setIncidentID(String.valueOf(callHistory.getIncidentID()));
			callHistoryResponse.setIncidentName(callHistory.getIncidentName());
			callHistoryResponse.setParticipantCode(String.valueOf(callHistory.getParticipantCode()));
			callHistoryResponse.setParticipantPhoneNumber(callHistory.getParticipantPhoneNumber());
			callHistoryResponse.setDateCreated(String.valueOf(callHistory.getDateCreated()));
			tmpUser = callHistory.getParticipant();
			if (null != tmpUser) {
				callHistoryResponse.setParticipantNickname(tmpUser.getNickName());
				callHistoryResponse.setParticipantFullName(tmpUser.getFullName());
			}
			historyList.add(callHistoryResponse);
		}
		
		getCallHistoryResponseDTO.setCallHistory(historyList);
		
		logger.info("CallHistoryServiceImp : getCallHistory : end");
		return getCallHistoryResponseDTO;
	}
}
