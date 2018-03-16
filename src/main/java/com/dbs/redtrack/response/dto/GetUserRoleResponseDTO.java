package com.dbs.redtrack.response.dto;

import com.dbs.redtrack.base.dto.AbstractBaseDTO;

public class GetUserRoleResponseDTO extends AbstractBaseDTO{
	
	private static final long serialVersionUID = 1L;
	 
	String roleis;
 
	public GetUserRoleResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetUserRoleResponseDTO(String roleis) {
		super();
		this.roleis = roleis;
	}

	public String getRoleis() {
		return roleis;
	}

	public void setRoleis(String roleis) {
		this.roleis = roleis;
	} 
	
  
	

}
