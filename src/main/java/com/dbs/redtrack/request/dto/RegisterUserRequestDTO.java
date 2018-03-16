package com.dbs.redtrack.request.dto;

import com.dbs.redtrack.base.dto.AbstractBaseDTO;

public class RegisterUserRequestDTO extends AbstractBaseDTO{
	
	private static final long serialVersionUID = 1L;
	
	private String userotpid;
	private String usernickname;
	private String userfullname;
	private String userdevictokenstr;
	
	public String getUserotpid() {
		return userotpid;
	}
	public void setUserotpid(String userotpid) {
		this.userotpid = userotpid;
	}
	public String getUsernickname() {
		return usernickname;
	}
	public void setUsernickname(String usernickname) {
		this.usernickname = usernickname;
	}
	public String getUserfullname() {
		return userfullname;
	}
	public void setUserfullname(String userfullname) {
		this.userfullname = userfullname;
	}
	public String getUserdevictokenstr() {
		return userdevictokenstr;
	}
	public void setUserdevictokenstr(String userdevictokenstr) {
		this.userdevictokenstr = userdevictokenstr;
	}
	public RegisterUserRequestDTO(String userotpid, String usernickname, String userfullname,
			String userdevictokenstr) {
		super();
		this.userotpid = userotpid;
		this.usernickname = usernickname;
		this.userfullname = userfullname;
		this.userdevictokenstr = userdevictokenstr;
	}
	public RegisterUserRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	 

}
