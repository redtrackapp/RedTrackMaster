package com.dbs.redtrack.dto.helper;

import com.dbs.redtrack.base.dto.AbstractBaseDTO;

public class DeviceTokenHelper  extends AbstractBaseDTO{
	

	private static final long serialVersionUID = 1L;
	
	private int devicePlatform;
	private String deviceTokenString;
 
	public String getDeviceTokenString() {
		return deviceTokenString;
	}
	public void setDeviceTokenString(String deviceTokenString) {
		this.deviceTokenString = deviceTokenString;
	}
	public int getDevicePlatform() {
		return devicePlatform;
	}
	public void setDevicePlatform(int devicePlatform) {
		this.devicePlatform = devicePlatform;
	}	
	
  
	 
	
	
}
