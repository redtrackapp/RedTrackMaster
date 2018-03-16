package com.dbs.redtrack.jpa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/*
 * Remember to add mapping to persistence.xml
 * */
@Entity
@Table(name="user_temp")
public class UserTemp implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	private long id;
	
	@Column(name="PHONE_NUMBER")
	private String phoneNumber;
	
	@Column(name="FULL_NAME")
	private String fullName;
	 
	
	@Column(name="NICK_NAME")
	private String nickName;
	
	@Column(name="ROLE")
	private int role;
	
	@Column(name="COUNTRY_CODE")
	private String countryCode;

	@Column(name="STATUS")
	private String status;
	
	@Column(name="OTP")
	private String otp;
		
	@Column(name="DEVICE_TOKEN_STRING")
	private String deviceTokenString;
	
	@Column(name="DEVICE_ID")
	private String deviceID;
	
	@Column(name="DEVICE_PLATFORM")
	private int devicePlatform;
	
	@Column(name="OTP_VALID_START_DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date validityStartDate;
	
	@Column(name="OTP_VALID_END_DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date validtityEndDate;
	
	@Column(name="DATE_CREATED")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getDeviceTokenString() {
		return deviceTokenString;
	}

	public void setDeviceTokenString(String deviceTokenString) {
		this.deviceTokenString = deviceTokenString;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public int getDevicePlatform() {
		return devicePlatform;
	}

	public void setDevicePlatform(int devicePlatform) {
		this.devicePlatform = devicePlatform;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	
	
	public Date getValidityStartDate() {
		return validityStartDate;
	}

	public void setValidityStartDate(Date validityStartDate) {
		this.validityStartDate = validityStartDate;
	}

	public Date getValidtityEndDate() {
		return validtityEndDate;
	}

	public void setValidtityEndDate(Date validtityEndDate) {
		this.validtityEndDate = validtityEndDate;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
	
} 