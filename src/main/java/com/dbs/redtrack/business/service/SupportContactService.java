/**
 * 
 */
package com.dbs.redtrack.business.service;

import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.request.dto.CallHistoryRequestDTO;
import com.dbs.redtrack.response.dto.CallHistoryResponseDTO;
import com.dbs.redtrack.response.dto.GetCallHistoryResponseDTO;
import com.dbs.redtrack.response.dto.SupportContactResponseDTO;

public interface SupportContactService {

	public SupportContactResponseDTO getSupportContactList() throws RedTrackProcessingException;
 
}
