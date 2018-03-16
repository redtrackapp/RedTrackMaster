package com.dbs.redtrack.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
@Entity
@Table(name="white_listing")
public class WhiteList implements Serializable {
 
		
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="ID")
	private long id;
	
	@Column(name="PHONE_NUMBER")
	private String phoneNumber;
	
	@Column(name="FULL_NAME")
	private String fullName;
	
	@Column(name="ROLE")
	private int role;
	
	@Column(name="DATE_CREATED")
	private String dateCreated;

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
 
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
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
