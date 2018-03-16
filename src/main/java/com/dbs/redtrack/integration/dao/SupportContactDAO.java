package com.dbs.redtrack.integration.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.jpa.entity.CallHistory;
import com.dbs.redtrack.jpa.entity.SupportContact;
 

@Repository
public interface SupportContactDAO {

	public List<SupportContact> getSupportContactList() throws RedTrackProcessingException;
 
	
	
	 
}
