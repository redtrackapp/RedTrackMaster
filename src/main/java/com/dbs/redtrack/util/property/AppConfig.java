package com.dbs.redtrack.util.property;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.dbs.redtrack.constants.BusinessConstants;

@Configuration
@PropertySource(BusinessConstants.REDTRACK_CONFIG_PROPERTY)
public class AppConfig {
	
    @Autowired
    Environment env;

	private static final Logger logger = Logger.getLogger(AppConfig.class);
	

    public String getProperty(String strName) {

    	logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>inside getProperty:");
    	logger.info("inside: getProperty():"+ strName);
    		return( env.getProperty(strName));    		

    }
}