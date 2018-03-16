package com.dbs.redtrack.response.dto;

import com.dbs.redtrack.base.dto.AbstractBaseDTO;

public class GenerateOTPResponseDTO  extends AbstractBaseDTO{
	
	private static final long serialVersionUID = 1L;
	
	String insertedotpid;
	String isOtpSent;
	String isoldotpclaimed;
	String responsecode;
	String roleis;
	String useralreadyexists;
	String useravatarlocation;
	String userfullname;
	String validityduration;
	
	public String getInsertedotpid() {
		return insertedotpid;
	}
	public void setInsertedotpid(String insertedotpid) {
		this.insertedotpid = insertedotpid;
	}
	public String getIsOtpSent() {
		return isOtpSent;
	}
	public void setIsOtpSent(String isOtpSent) {
		this.isOtpSent = isOtpSent;
	}
	public String getIsoldotpclaimed() {
		return isoldotpclaimed;
	}
	public void setIsoldotpclaimed(String isoldotpclaimed) {
		this.isoldotpclaimed = isoldotpclaimed;
	}
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
	public String getUseralreadyexists() {
		return useralreadyexists;
	}
	public void setUseralreadyexists(String useralreadyexists) {
		this.useralreadyexists = useralreadyexists;
	}
	public String getUseravatarlocation() {
		return useravatarlocation;
	}
	public void setUseravatarlocation(String useravatarlocation) {
		this.useravatarlocation = useravatarlocation;
	}
	public String getUserfullname() {
		return userfullname;
	}
	public void setUserfullname(String userfullname) {
		this.userfullname = userfullname;
	}
	public String getValidityduration() {
		return validityduration;
	}
	public void setValidityduration(String validityduration) {
		this.validityduration = validityduration;
	}
	

}
