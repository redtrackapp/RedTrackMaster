package com.dbs.redtrack.request.dto;

import com.dbs.redtrack.base.dto.AbstractBaseDTO;

public class UpdateUserRequestDTO extends AbstractBaseDTO{
	
	private static final long serialVersionUID = 1L;

	private String usernickname;
	private String userotpid;
	private String usermobilenumber;
	private String countryCode;
	 

	public UpdateUserRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UpdateUserRequestDTO(String userotpid, String usernickname) {
		super();
		this.usernickname = usernickname;
		this.userotpid = userotpid;
	}
	 
	public String getUsernickname() {
		return usernickname;
	}
	public void setUsernickname(String usernickname) {
		this.usernickname = usernickname;
	}
	public String getUserotpid() {
		return userotpid;
	}
	public void setUserotpid(String userotpid) {
		this.userotpid = userotpid;
	}
	public String getUsermobilenumber() {
		return usermobilenumber;
	}
	public void setUsermobilenumber(String usermobilenumber) {
		this.usermobilenumber = usermobilenumber;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

}
