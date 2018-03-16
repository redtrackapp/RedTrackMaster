package com.dbs.redtrack.request.dto;

import com.dbs.redtrack.base.dto.AbstractBaseDTO;

public class GetAllArchivedIncidentsRequestDTO extends AbstractBaseDTO{
	
	private static final long serialVersionUID = 1L;

	String userotpid;

	public GetAllArchivedIncidentsRequestDTO(String userotpid2) {
		this.userotpid=userotpid2;
	}

	public String getUserotpid() {
		return userotpid;
	}

	public void setUserotpid(String userotpid) {
		this.userotpid = userotpid;
	}
	
	
 

}
