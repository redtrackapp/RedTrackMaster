package com.dbs.redtrack.response.dto;

import com.dbs.redtrack.base.dto.AbstractBaseDTO;

public class ValidateOTPResponseDTO extends AbstractBaseDTO{
	
	private static final long serialVersionUID = 1L;
 

	String responsecode;
	String roleis;
	
	
	public String getResponsecode() {
		return responsecode;
	}
	public void setResponsecode(String responsecode) {
		this.responsecode = responsecode;
	}
	public String getRoleis() {
		return roleis;
	}
	public void setRoleis(String roleis) {
		this.roleis = roleis;
	} 
	
}
