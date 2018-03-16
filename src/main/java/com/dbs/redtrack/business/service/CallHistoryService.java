/**
 * 
 */
package com.dbs.redtrack.business.service;

import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.request.dto.CallHistoryRequestDTO;
import com.dbs.redtrack.response.dto.CallHistoryResponseDTO;
import com.dbs.redtrack.response.dto.GetCallHistoryResponseDTO;

public interface CallHistoryService {

	public CallHistoryResponseDTO saveCallHistory(CallHistoryRequestDTO callHistryRequestDTO) throws RedTrackProcessingException;

	public GetCallHistoryResponseDTO getCallHistory(String userOTPID) throws RedTrackProcessingException;

	public GetCallHistoryResponseDTO getCallHistoryTest(String userOTPID) throws RedTrackProcessingException;
	
	 
}
