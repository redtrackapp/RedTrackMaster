package com.dbs.redtrack.request.dto;

import com.dbs.redtrack.base.dto.AbstractBaseDTO;

public class ValidateOTPRequestDTO extends AbstractBaseDTO{
	
	private static final long serialVersionUID = 1L;

	private String userenteredotp;
	private String userotpid;
	private String usermobilenumber;
	private String userdevictokenstr;
	
	public String getUserenteredotp() {
		return userenteredotp;
	}
	public void setUserenteredotp(String userenteredotp) {
		this.userenteredotp = userenteredotp;
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
	
	
	public String getUserdevictokenstr() {
		return userdevictokenstr;
	}
	public void setUserdevictokenstr(String userdevictokenstr) {
		this.userdevictokenstr = userdevictokenstr;
	}
	public ValidateOTPRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ValidateOTPRequestDTO(String userenteredotp, String userotpid, String usermobilenumber) {
		super();
		this.userenteredotp = userenteredotp;
		this.userotpid = userotpid;
		this.usermobilenumber = usermobilenumber;
	}
	
	public ValidateOTPRequestDTO(String userenteredotp, String userotpid, String usermobilenumber,
			String userdevictokenstr) {
		super();
		this.userenteredotp = userenteredotp;
		this.userotpid = userotpid;
		this.usermobilenumber = usermobilenumber;
		this.userdevictokenstr = userdevictokenstr;
	}
 

}
