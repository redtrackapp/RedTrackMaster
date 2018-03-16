package com.dbs.redtrack.request.dto;

import com.dbs.redtrack.base.dto.AbstractBaseDTO;

public class ArchiveIncidentRequestDTO extends AbstractBaseDTO{
	
	private static final long serialVersionUID = 1L;

	
	private String userotpid;
	private String incidentid;
	
	
	public ArchiveIncidentRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ArchiveIncidentRequestDTO(String userotpid2, String incidentid2) {
		
		this.userotpid=userotpid2;
		this.incidentid=incidentid2;
	}
	public String getUserotpid() {
		return userotpid;
	}
	public void setUserotpid(String userotpid) {
		this.userotpid = userotpid;
	}
	public String getIncidentid() {
		return incidentid;
	}
	public void setIncidentid(String incidentid) {
		this.incidentid = incidentid;
	}
	
	
	

}
