package com.dbs.redtrack.integration.dao;

import java.sql.Connection;

import org.springframework.stereotype.Repository;

import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.jpa.entity.InicidentHistory;

@Repository
public interface DBHelperConnectionDAO {

	Connection getApplicationStatusData();
 
	
}
