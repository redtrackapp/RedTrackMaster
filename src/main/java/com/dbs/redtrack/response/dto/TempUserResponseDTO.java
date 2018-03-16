package com.dbs.redtrack.response.dto;

import java.util.List;

import com.dbs.redtrack.base.dto.AbstractBaseDTO;

public class TempUserResponseDTO extends AbstractBaseDTO{
	
	private static final long serialVersionUID = 1L;
	 
	List<RegisterUserResponseDTO> userList;

	public List<RegisterUserResponseDTO> getUserList() {
		return userList;
	}

	public void setUserList(List<RegisterUserResponseDTO> userList) {
		this.userList = userList;
	} 
	
	 
}
