package com.dbs.redtrack.response.dto;
 
import com.dbs.redtrack.base.dto.AbstractBaseDTO;

public class SupportContactHelper extends AbstractBaseDTO {
	
	private static final long serialVersionUID = 1L;
	
 
	private String name; 
	private String contactNumber; 
	private String email; 
	private String imageName;	 
	private String supportedApplication; 
	private String status;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getSupportedApplication() {
		return supportedApplication;
	}
	public void setSupportedApplication(String supportedApplication) {
		this.supportedApplication = supportedApplication;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	  
	
}
