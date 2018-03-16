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


@Entity
@Table(name="user")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//@Id @GeneratedValue(strategy = GenerationType.IDENTITY)	
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

	@Column(name="DEVICE_TOKEN_STRING")
	private String deviceTokenString;

	@Column(name="DEVICE_ID")
	private String deviceID;
	
	@Column(name="DEVICE_PLATFORM")
	private int devicePlatform;
	
	@Column(name="DATE_CREATED")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	
	@Column(name="STATUS")
	private String status;

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
  
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
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

 

	public int getDevicePlatform() {
		return devicePlatform;
	}

	public void setDevicePlatform(int devicePlatform) {
		this.devicePlatform = devicePlatform;
	}



	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
//	
//	@OneToMany(mappedBy="customer",targetEntity=CallHistory.class,
//			   fetch=FetchType.EAGER)
//			   private Collection callHistory;  
}

/*
 CREATE TABLE `user` (
	`ID` INT(11) NOT NULL AUTO_INCREMENT,
	`PHONE_NUMBER` VARCHAR(50) NULL DEFAULT NULL,
	`FIRST_NAME` VARCHAR(50) NULL DEFAULT NULL,
	`LAST_NAME` VARCHAR(50) NULL DEFAULT NULL,
	`COUNTRY` VARCHAR(50) NULL DEFAULT NULL,
	`DATE_CREATED` DATETIME NULL DEFAULT NULL,
	PRIMARY KEY (`ID`)
)
 * */
 