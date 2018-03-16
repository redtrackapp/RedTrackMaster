package com.dbs.redtrack.response.dto;

import java.util.List;

import com.dbs.redtrack.base.dto.AbstractBaseDTO;
import com.dbs.redtrack.dto.helper.IncidentHelper;

public class GetAllIncidentResponseDTO extends AbstractBaseDTO{
	
	private static final long serialVersionUID = 1L;
	 
	List<IncidentHelper> allincidents;
 
	String responsecode;
	
	String roleis;
	  

	public String getRoleis() {
		return roleis;
	}

	public void setRoleis(String roleis) {
		this.roleis = roleis;
	} 

	public List<IncidentHelper> getAllincidents() {
		return allincidents;
	}

	public void setAllincidents(List<IncidentHelper> allincidents) {
		this.allincidents = allincidents;
	}

	public String getResponsecode() {
		return responsecode;
	}

	public void setResponsecode(String responsecode) {
		this.responsecode = responsecode;
	}
 
 
 
	

}
