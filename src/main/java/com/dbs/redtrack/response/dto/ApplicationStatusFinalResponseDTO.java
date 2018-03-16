/**
 * 
 */
package com.dbs.redtrack.response.dto;

import java.util.List;

import com.dbs.redtrack.base.dto.AbstractBaseDTO;

/**
 * @author IBM
 *
 */
public class ApplicationStatusFinalResponseDTO extends AbstractBaseDTO  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ApplicationStatusCountries> country_names;

	/**
	 * @return the country_names
	 */
	public List<ApplicationStatusCountries> getCountry_names() {
		return country_names;
	}

	/**
	 * @param country_names the country_names to set
	 */
	public void setCountry_names(List<ApplicationStatusCountries> country_names) {
		this.country_names = country_names;
	}

	
}
