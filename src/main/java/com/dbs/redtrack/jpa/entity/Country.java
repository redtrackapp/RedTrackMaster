package com.dbs.redtrack.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;


@Entity
@Table(name="country_master")
public class Country implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//@Id @GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Id
	@Column(name="COUNTRY_CODE")
	private String countryCode;
	
	@Column(name="COUNTRY_DESCRIPTION")
	private String countryDescription;
	 
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryDescription() {
		return countryDescription;
	}

	public void setCountryDescription(String countryDescription) {
		this.countryDescription = countryDescription;
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
 