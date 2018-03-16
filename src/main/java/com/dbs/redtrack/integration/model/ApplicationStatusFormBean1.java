package com.dbs.redtrack.integration.model;

import java.util.List;

import com.dbs.redtrack.request.dto.ApplicationStatusDTO;

public class ApplicationStatusFormBean1 {

		List<ApplicationStatusDTO> applicationStatusList;

		public List<ApplicationStatusDTO> getApplicationStatusList() {
				return applicationStatusList;
		}

		public void setApplicationStatusList(List<ApplicationStatusDTO> applicationStatusList) {
			this.applicationStatusList = applicationStatusList;
		}

}
