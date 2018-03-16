/**
 * 
 */
package com.dbs.redtrack.response.dto;

import java.util.ArrayList;
import java.util.List;

import com.dbs.redtrack.base.dto.AbstractBaseDTO;

/**
 * @author IBM
 * 
 */
public class ApplicationsDetailsResponseDTO extends AbstractBaseDTO {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	List<ApplicationDetailsHelper> appList;
	
	
	
	public ApplicationsDetailsResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApplicationsDetailsResponseDTO(String countryCode,
			ApplicationDetailsHelper appDetailHelper ) {
		super();
		//this.appList = appList;
		this.appList  = new ArrayList<ApplicationDetailsHelper>();
		this.countryCode = countryCode;
		this.appList.add(appDetailHelper);
	}

	private String countryCode;
	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * @return the appList
	 */
	public List<ApplicationDetailsHelper> getAppList() {
		return appList;
	}

	/**
	 * @param appList
	 *            the appList to set
	 */
	public void setAppList(List<ApplicationDetailsHelper> appList) {
		this.appList = appList;
	}

}
