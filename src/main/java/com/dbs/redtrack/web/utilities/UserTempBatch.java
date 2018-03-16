package com.dbs.redtrack.web.utilities;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dbs.redtrack.business.service.UserRegistrationService;
import com.dbs.redtrack.business.services.impl.UserRegistrationServiceImpl;
import com.dbs.redtrack.constants.BusinessConstants;

@Component
public class UserTempBatch {
	@Autowired
	UserRegistrationService userRegistrationService;
	
	private static final Logger logger = Logger.getLogger(UserRegistrationServiceImpl.class);
	
	@Scheduled(cron =BusinessConstants.CRON_ENTRY_20,zone=BusinessConstants.TIME_ZONE)
    public void dealWithScheduledDelete() { 
		logger.info("Cron running deleted: dealWithScheduledDelete() :: start" );
		//TODO Comment as failing OTP validation
		//userRegistrationService.deleteUserTemp();
		logger.info("Cron running deleted: dealWithScheduledDelete() :: end" );		
     }
	
	 
}
