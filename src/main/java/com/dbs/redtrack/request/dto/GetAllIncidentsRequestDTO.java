package com.dbs.redtrack.request.dto;

import com.dbs.redtrack.base.dto.AbstractBaseDTO;

public class GetAllIncidentsRequestDTO extends AbstractBaseDTO{
	
	private static final long serialVersionUID = 1L;
	
	String userdevictokenstr;
	String userotpid;
	String accessprofileid;
	
	public GetAllIncidentsRequestDTO(String userdevictokenstr2,
			String userotpid2, String accessprofileid2) {
		
		this.userdevictokenstr=userdevictokenstr2;
		this.userotpid=userotpid2;
		this.accessprofileid=accessprofileid2;
		
	}
	public String getUserdevictokenstr() {
		return userdevictokenstr;
	}
	public void setUserdevictokenstr(String userdevictokenstr) {
		this.userdevictokenstr = userdevictokenstr;
	}
	public String getUserotpid() {
		return userotpid;
	}
	public void setUserotpid(String userotpid) {
		this.userotpid = userotpid;
	}
	public String getAccessprofileid() {
		return accessprofileid;
	}
	public void setAccessprofileid(String accessprofileid) {
		this.accessprofileid = accessprofileid;
	}
	
	
	
	
	

}
