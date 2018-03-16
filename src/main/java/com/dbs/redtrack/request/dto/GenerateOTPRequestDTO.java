package com.dbs.redtrack.request.dto;

import com.dbs.redtrack.base.dto.AbstractBaseDTO;

public class GenerateOTPRequestDTO extends AbstractBaseDTO {

	private static final long serialVersionUID = 1L;

//	//String phoneNumber;
////	String firstName;
////	String lastName;
//	//String nickName;
//	String countryCode;
////	String otp;
//	//String deviceTokenString;
//	String deviceID;
//	String devicePlatform;
//	String status;
//	String startDate;
//	String enddate;

	//
	private String usermobiledeviceid;
	private String usermobilenumber;
	private String usercountrycode;
	private String usermobileplatform;
	
	public String getUsermobiledeviceid() {
		return usermobiledeviceid;
	}
	public void setUsermobiledeviceid(String usermobiledeviceid) {
		this.usermobiledeviceid = usermobiledeviceid;
	}
	public String getUsermobilenumber() {
		return usermobilenumber;
	}
	public void setUsermobilenumber(String usermobilenumber) {
		this.usermobilenumber = usermobilenumber;
	}
	public String getUsercountrycode() {
		return usercountrycode;
	}
	public void setUsercountrycode(String usercountrycode) {
		this.usercountrycode = usercountrycode;
	}
	public String getUsermobileplatform() {
		return usermobileplatform;
	}
	public void setUsermobileplatform(String usermobileplatform) {
		this.usermobileplatform = usermobileplatform;
	}
	
	
	public GenerateOTPRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public GenerateOTPRequestDTO(String usermobiledeviceid, String usermobilenumber, String usercountrycode,
			String usermobileplatform) {
		super();
		this.usermobiledeviceid = usermobiledeviceid;
		this.usermobilenumber = usermobilenumber;
		this.usercountrycode = usercountrycode;
		this.usermobileplatform = usermobileplatform;
	}	  
 

}
