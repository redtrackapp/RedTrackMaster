/**
 * 
 */
package com.dbs.redtrack.integration.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dbs.redtrack.constants.DAOConstants;
import com.dbs.redtrack.dto.helper.AppCountry;
import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.integration.dao.DBHelperConnectionDAO;
import com.dbs.redtrack.integration.dao.IApplicationStatusDAO;
import com.dbs.redtrack.response.dto.ApplicationDetailsHelper;
import com.dbs.redtrack.response.dto.ApplicationStatusHelper;


/**
 * @author IBM : It consist all database operations those are related to
 *         Application_Status table.
 * 
 */
@Repository
public class ApplicationStatusDAOImpl extends BaseDAOImpl implements
		IApplicationStatusDAO {

	private static final Logger logger = Logger
			.getLogger(ApplicationStatusDAOImpl.class);

	@Autowired
	DBHelperConnectionDAO conncetionHelper;

	@Transactional
	private String updateApplicationsStatus_old(List<String> appStrList,
			String strStatus) throws RedTrackProcessingException {

		logger.info("Inside ApplicationStatusDAOImpl :: getApplicationsStatusUpdate() :: DAO start >> ");
		String msg = null;
		if (appStrList != null && appStrList.size() >= 1) {
			for (String strAppId : appStrList) {
				Query query = getEm().createQuery(
						DAOConstants.QUERY_APPLICATIONS_STATUS_UPDATE);
				query.setParameter("strAppId", strAppId);
				query.setParameter("strStatus", strStatus);
				int results = query.executeUpdate();
				if (results > 0) {
					logger.info("Application Status Table Updated");
					msg = "SUCCESS";
				}

			}
		}
		logger.info("Inside ApplicationStatusDAOImpl :: getApplicationsStatusUpdate() :: DAO end >> ");

		return msg;
	}
 
	@Override
	public List<ApplicationStatusHelper> getApplicationsStatus()
			throws RedTrackProcessingException {
		
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		List<ApplicationStatusHelper> appStatusHelperlist = new ArrayList<ApplicationStatusHelper>();
		
	
		
		//DBHelperConncetion conncetionHelper = new DBHelperConncetion();
		
		Connection  conn =conncetionHelper.getApplicationStatusData();
		
		 //STEP 4: Execute a query
	     //String sql = "select * from application_status order by COUNTRY_CODE, STATUS desc";
		//Note: can not modify this query. desc order is required to get latest changes
		String sql = "select a.COUNTRY_CODE, a.UNIT_ID, a.APP_ID, a.STATUS, b.APPLICATION_NAME, c.DESCRIPTION, a.APP_COMMENTS "
				+ "from application_status a, applications b, business_units c "
				+ "where a.APP_ID = b.APP_ID and a.UNIT_ID = c.UNIT_ID order by a.COUNTRY_CODE, a.STATUS desc";  
	     try{ 
	    	  pstmt = conn.prepareStatement(sql);
		      rs = pstmt.executeQuery(sql);
	      while(rs.next()) {
		      	ApplicationStatusHelper applicationStatusHelper = new ApplicationStatusHelper();
				applicationStatusHelper.setCountryCode(rs.getString(1));
				applicationStatusHelper.setBizunit_id(rs.getString(2));
				applicationStatusHelper.setAppId(rs.getString(3));
				applicationStatusHelper.setApptype_status( rs.getString(4));
				applicationStatusHelper.setApptype_name(rs.getString(5));
				applicationStatusHelper.setBizunit_name(rs.getString(6));
				//added appComments :: 17.07.17
				applicationStatusHelper.setAppComments(rs.getString(7));
				appStatusHelperlist.add(applicationStatusHelper);
	       }
	      
	      logger.info("Result Set ... >> " + rs);

	      //STEP 6: Clean-up environment
	      rs.close();
	      pstmt.close();
	      conn.close();
		//List<ApplicationStatus> results = query.getResultList();
		
		logger.info("Inside ApplicationStatusDAOImpl :: results ****** >>> ******** "  );

	}catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(pstmt!=null)
	        	 pstmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	     return appStatusHelperlist;
	}

 
 
	//Added by Raymond
	@Override
	@Transactional
	public String updateApplicationsStatus(List <AppCountry> appCountryList, String strStatus) throws RedTrackProcessingException {

		logger.info("Inside ApplicationStatusDAOImpl :: getApplicationsStatusUpdate() :: DAO start >> ");
		Query query = null;
		
			
		String msg = null;
		if (appCountryList != null && appCountryList.size() >= 1) {
			for (AppCountry appCountry : appCountryList) {
				query = getEm().createQuery(DAOConstants.QUERY_APPLICATIONS_STATUS_UPDATE2);
				if (isIncidentStatusOpen(strStatus)) {//Severity Level 1,2,3					
					query.setParameter("comment", "");
				} else if (strStatus.equals("4")) {	//Severity Level 4
					query.setParameter("comment", "Fully Operational");
				} else {
					query = getEm().createQuery(DAOConstants.QUERY_APPLICATIONS_STATUS_UPDATE);
					
				}
				query.setParameter("strAppId", appCountry.getApplicationCode()); 
				query.setParameter("countryCode", appCountry.getCountryCode());
				query.setParameter("strStatus", strStatus);
				int results = query.executeUpdate();
				if (results > 0) {
					logger.info("Application Status Table Updated");
					msg = "SUCCESS";
				}

			}
		}
		logger.info("Inside ApplicationStatusDAOImpl :: getApplicationsStatusUpdate() :: DAO end >> ");

		return msg;
	}

	

	@SuppressWarnings("unused")
	private boolean isIncidentStatusOpen(String severityLevel) {

		boolean status = true;		
		List<String> list = new ArrayList<String>(Arrays.asList("1", "2", "3"));
		
		if(list.contains(severityLevel)) {
			status = true;			
		} else {			
			status = false;
		}		
		return status;
	}
	
	
	//Added by Raymond
	@SuppressWarnings("unchecked")
	@Override
	public List<ApplicationDetailsHelper> getApplicationDetails() throws RedTrackProcessingException {
		logger.info("Inside ApplicationsDAOImpl :: getApplicationDetails() :: start");
		Query query = getEm().createQuery(DAOConstants.QUERY_GET_APPLICATIONS_DETAILS);		
		query.setParameter("appActive", "Y"); 
		
		List<ApplicationDetailsHelper> resultList = new ArrayList<ApplicationDetailsHelper>();
		ApplicationDetailsHelper appResult = null;
		List<Object[]> results = query.getResultList();
		for (Object[] record : results) {
			int i = 0;
			appResult = new ApplicationDetailsHelper();
			appResult.setCountryCode("" + record[i++]);//0
			logger.info("Country Desc: " +  record[i++]);
			appResult.setAppId("" + record[i++]);
			appResult.setAppName("" + record[i++]);
			appResult.setAppCode("" + record[i++]);
			logger.info("BU ID: " +  record[i++]);
			logger.info("BU Desc: " +  record[i++]);
			logger.info("Status: " +  record[i++]);
			appResult.setAppActive("" + record[i++]);
			resultList.add(appResult);		
//			logger.info("appresult>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+ appResult);
		}
		logger.info("Inside ApplicationsDAOImpl :: getApplicationDetails() :: end");
		return resultList;	 
	}

 

}
