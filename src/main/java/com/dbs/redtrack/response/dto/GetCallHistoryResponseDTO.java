package com.dbs.redtrack.response.dto;

import java.util.List;

import com.dbs.redtrack.base.dto.AbstractBaseDTO;

public class GetCallHistoryResponseDTO extends AbstractBaseDTO{
	
	private static final long serialVersionUID = 1L;
	 
	List<CallHistoryHelper> callHistory;

	public List<CallHistoryHelper> getCallHistory() {
		return callHistory;
	}

	public void setCallHistory(List<CallHistoryHelper> callHistory) {
		this.callHistory = callHistory;
	}

	
	
}
