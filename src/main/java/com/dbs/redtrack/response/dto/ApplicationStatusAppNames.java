/**
 * 
 */
package com.dbs.redtrack.response.dto;

import com.dbs.redtrack.base.dto.AbstractBaseDTO;

/**
 * @author ADMINIBM
 *
 */
public class ApplicationStatusAppNames extends AbstractBaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    
	/**
	 * @return the apptype_name
	 */
	public String getApptype_name() {
		return apptype_name;
	}
	/**
	 * @param apptype_name the apptype_name to set
	 */
	public void setApptype_name(String apptype_name) {
		this.apptype_name = apptype_name;
	}
	/**
	 * @return the apptype_status
	 */
	public String getApptype_status() {
		return apptype_status;
	}
	/**
	 * @param apptype_status the apptype_status to set
	 */
	public void setApptype_status(String apptype_status) {
		this.apptype_status = apptype_status;
	}
	private String apptype_name;
	private String apptype_status;

}
