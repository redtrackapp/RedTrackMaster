/**
 * 
 */
package com.dbs.redtrack.integration.dao.impl;
import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.dbs.redtrack.controller.test.TestImpl;
import com.dbs.redtrack.exception.RedTrackException;
import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.integration.dao.DBHelperConnectionDAO;
import com.dbs.redtrack.util.property.AppConfig;


@Service
@Configurable
public class DBHelperConncetion  extends BaseDAOImpl implements DBHelperConnectionDAO {

	private static final Logger logger = Logger.getLogger(DBHelperConncetion.class);
	
	@Autowired
	AppConfig appconfig;

		   
	@Override
    public Connection getApplicationStatusData() throws RedTrackProcessingException {
 
//		String JDBC_DRIVER = appconfig.getProperty("JDBC_DRIVER");  
//		String DB_URL = appconfig.getProperty("DB_URL");
//		String USER = appconfig.getProperty("USER");
//	    String PASS = appconfig.getProperty("PASS");

		/***************** Development ****************/
//	    String DB_URL= "jdbc:mysql://au-cdbr-sl-syd-01.cleardb.net/ibmx_3fe5f5663289e82";
//	    String  USER= "b500b1addceea6";
//	    String PASS= "0217b14c";

	    /***************** PILOT  ****************/
	    String DB_URL= "jdbc:mysql://bipredtrackdb.cutguhbkcwjr.ap-southeast-2.rds.amazonaws.com/bipredtrackdb";
	    String  USER= "bipredtrack";
	    String PASS= "bipredtrack123";
	    
    	Connection conn = null;
    	try { 
    		Class.forName("com.mysql.jdbc.Driver");
    		logger.info("DBHelperConncetion ... Connecting to database...");
    		conn = DriverManager.getConnection(DB_URL,USER,PASS); 
    	}catch(Exception se) {
    		logger.info(se.getMessage());
    		throw new RedTrackException("0001","Unable to Conect to DB");    		
    		//se.printStackTrace();
    	}
    	return conn;
    }
}


