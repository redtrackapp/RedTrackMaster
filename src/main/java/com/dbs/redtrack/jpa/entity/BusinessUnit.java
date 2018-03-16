package com.dbs.redtrack.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;


@Entity
@Table(name="business_units")
public class BusinessUnit implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//@Id @GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Id
	@Column(name="UNIT_ID")
	private String businessUnitID;
	
	@Column(name="DESCRIPTION")
	private String businessUnitDesc;
	  
	public String getBusinessUnitID() {
		return businessUnitID;
	}

	public void setBusinessUnitID(String businessUnitID) {
		this.businessUnitID = businessUnitID;
	}

	public String getBusinessUnitDesc() {
		return businessUnitDesc;
	}

	public void setBusinessUnitDesc(String businessUnitDesc) {
		this.businessUnitDesc = businessUnitDesc;
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
 