package com.dbs.redtrack.response.dto;

import java.util.List;

import com.dbs.redtrack.base.dto.AbstractBaseDTO;

public class SupportContactResponseDTO extends AbstractBaseDTO{
	
	private static final long serialVersionUID = 1L;
	 
	List<SupportContactHelper> supportContactList;

	public List<SupportContactHelper> getSupportContactList() {
		return supportContactList;
	}

	public void setSupportContactList(List<SupportContactHelper> supportContactList) {
		this.supportContactList = supportContactList;
	}
 
	
	
}
