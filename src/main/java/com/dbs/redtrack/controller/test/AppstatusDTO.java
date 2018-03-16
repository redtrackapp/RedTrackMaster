package com.dbs.redtrack.controller.test;

import java.io.Serializable;

import com.dbs.redtrack.base.dto.AbstractBaseDTO;

public class AppstatusDTO  extends AbstractBaseDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String apptype_name_id;
	 String  apptype_name; 
	 String apptype_type_status; 
	 String apptype_type_long_desc;
	 
	 

		public AppstatusDTO() {
			super();
			// TODO Auto-generated constructor stub
		}
		
	public AppstatusDTO(String apptype_name_id, String apptype_name, String apptype_type_status,
			String apptype_type_long_desc) {
		super();
		this.apptype_name_id = apptype_name_id;
		this.apptype_name = apptype_name;
		this.apptype_type_status = apptype_type_status;
		this.apptype_type_long_desc = apptype_type_long_desc;
	}
	public String getApptype_name_id() {
		return apptype_name_id;
	}
	public void setApptype_name_id(String apptype_name_id) {
		this.apptype_name_id = apptype_name_id;
	}
	public String getApptype_name() {
		return apptype_name;
	}
	public void setApptype_name(String apptype_name) {
		this.apptype_name = apptype_name;
	}
	public String getApptype_type_status() {
		return apptype_type_status;
	}
	public void setApptype_type_status(String apptype_type_status) {
		this.apptype_type_status = apptype_type_status;
	}
	public String getApptype_type_long_desc() {
		return apptype_type_long_desc;
	}
	public void setApptype_type_long_desc(String apptype_type_long_desc) {
		this.apptype_type_long_desc = apptype_type_long_desc;
	}
	 
	 
	
}
