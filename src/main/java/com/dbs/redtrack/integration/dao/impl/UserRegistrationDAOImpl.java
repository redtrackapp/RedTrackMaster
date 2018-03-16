package com.dbs.redtrack.integration.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dbs.redtrack.constants.BusinessConstants;
import com.dbs.redtrack.constants.DAOConstants;
import com.dbs.redtrack.dto.helper.DeviceTokenHelper;
import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.integration.dao.UserRegistrationDAO;
import com.dbs.redtrack.jpa.entity.User;
import com.dbs.redtrack.jpa.entity.UserTemp;
import com.dbs.redtrack.jpa.entity.WhiteList;

@Repository
public class UserRegistrationDAOImpl extends BaseDAOImpl implements UserRegistrationDAO {

	private static final Logger logger = Logger.getLogger(UserRegistrationDAOImpl.class);
	
	
	/**
	 * comments here
	 */ 
	@SuppressWarnings("unchecked")
	@Override	
	public boolean checkUserFromWhiteList(String strPhoneNumber) throws RedTrackProcessingException {

		logger.info("Inside UserRegistrationDAOImpl :: checkUserFromWhiteList() :: start");
		boolean isFound = true;
		Query query = getEm().createQuery(DAOConstants.QUERY_WHITE_LIST_BY_PHONENUMBER);		 
		query.setParameter("strPhoneNumber", strPhoneNumber);

		List<WhiteList> results = query.getResultList();		
		   
		logger.info("checkUserFromWhiteList() :: Results: " + results);
		if ((null == results) || results.size() == 0) {
			isFound = false;
		}
		logger.info("Inside UserRegistrationDAOImpl :: checkUserFromWhiteList() :: end " + isFound);
		return isFound;	
	}	
	
	
	/**
	 * comments here
	 */ 
	@SuppressWarnings("unchecked")
	@Override	
	public WhiteList getUserFromWhiteList(String strPhoneNumber) throws RedTrackProcessingException {
		WhiteList whiteList = null;
		logger.info("Inside UserRegistrationDAOImpl :: getUserFromWhiteList() :: start");
		
		Query query = getEm().createQuery(DAOConstants.QUERY_WHITE_LIST_BY_PHONENUMBER);		 
		query.setParameter("strPhoneNumber", strPhoneNumber);

		List<WhiteList> results = query.getResultList();		
		   
		logger.info("getUserFromWhiteList() :: Results: " + results);
		if ((null != results) && results.size() != 0) {
			whiteList = results.get(0); 
		}
		
		logger.info("Inside UserRegistrationDAOImpl :: getUserFromWhiteList() :: end (" + whiteList +")");
		return whiteList;	
	}		
	
	
	/**
	 * comments here
	 */
	@Override
	@Transactional
	public void registerTempUser(UserTemp tempUser) throws RedTrackProcessingException {

		logger.info("Inside UserRegistrationDAOImpl :: registerTempUser() :: start");
		//UserTemp userTemp = findTempUserByPhoneNumber(tempUser.getPhoneNumber(), tempUser.getCountryCode());
		UserTemp userTemp = findUserTempByID(String.valueOf(tempUser.getId()));
		
		
		if (null == userTemp) {
			save(tempUser);
		} else {			
			userTemp.setDateCreated(tempUser.getDateCreated());
			userTemp.setValidityStartDate(tempUser.getValidityStartDate());
			userTemp.setValidtityEndDate(tempUser.getValidtityEndDate());
			userTemp.setStatus(tempUser.getStatus());
			userTemp.setCountryCode(tempUser.getCountryCode());
			userTemp.setPhoneNumber(tempUser.getPhoneNumber());			
			userTemp.setDevicePlatform(tempUser.getDevicePlatform());
			userTemp.setDeviceID(tempUser.getDeviceID());	
			save(userTemp);
//			todo: add other fields			
			//added otp: july 17, 2017
			userTemp.setOtp(tempUser.getOtp());	 
		}
		logger.info("Inside UserRegistrationDAOImpl :: registerTempUser() :: end");
	}
	
 

	/**
	 * comments here
	 */
	@Override	
	public UserTemp findUserTempByID(String strID) throws RedTrackProcessingException {
		logger.info("Inside UserRegistrationDAOImpl :: findUserTempByID() :: start");
		
		
		UserTemp userTemp = (UserTemp)find(UserTemp.class, Long.parseLong(strID));
		
		logger.info("Existing Entity from DB for userTemp: " + userTemp);	
		logger.info("Inside UserRegistrationDAOImpl :: findUserTempByID() :: end");
		
		return userTemp;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public UserTemp findTempUserByPhoneNumber(String strPhoneNumber, String strCountryCode) throws RedTrackProcessingException {
		logger.info("Inside UserRegistrationDAOImpl :: findTempUserByPhoneNumber() :: ("+strPhoneNumber+", "+strCountryCode+") ::start" );
		UserTemp usertemp = null;
		Query query = getEm().createQuery(DAOConstants.QUERY_USER_TEMP_BY_PHONENUMBER_COUNTRY_CODE);		 
		query.setParameter("strPhoneNumber", strPhoneNumber);
		query.setParameter("strCountryCode", strCountryCode);
		
		List<UserTemp> results = query.getResultList();		
			
		if (null != results && results.size() != 0) {
 			usertemp =  results.get(0);
			logger.info("Existing Entity from DB for UserTemp: " +  results);	
		}
		logger.info("findTempUserByPhoneNumber:: userTemp ("+usertemp+")");
		logger.info("Inside UserRegistrationDAOImpl :: findTempUserByPhoneNumber() :: end");

		return  usertemp;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public User findUserByPhoneNumber(String strPhoneNumber, String strCountryCode) throws RedTrackProcessingException {
		logger.info("Inside UserRegistrationDAOImpl :: findUserByPhoneNumber() :: ("+strPhoneNumber+", "+strCountryCode+") ::start" );
		User user = null;
		Query query = getEm().createQuery(DAOConstants.QUERY_USER_BY_PHONENUMBER_COUNTRY);		 
		query.setParameter("strPhoneNumber", strPhoneNumber);
		query.setParameter("strCountryCode", strCountryCode);
		
		List<User> results = query.getResultList();		
			
		if (null != results && results.size() != 0) {
			user =  results.get(0);
			logger.info("Existing Entity from DB for UserTemp: " +  results);	
		}
		logger.info("findUserByPhoneNumber:: userTemp ("+user+")");
		logger.info("Inside UserRegistrationDAOImpl :: findUserByPhoneNumber() :: end");

		return  user;
	}
	 
	
	@Override
	@Transactional
	public int deleteUserTemp()throws RedTrackProcessingException {
		
		logger.info("Inside UserRegistrationDAOImpl :: deleteUserTemp() :: start");
  
		String queryString =  DAOConstants.DELETE_USER_TEMP;
		Query query = getEm().createQuery(queryString);
		query.setParameter("strStatus", BusinessConstants.STATUS_PROCESSING); 
		
		int deletestatus = query.executeUpdate();
		logger.info("Temp Delete status" + deletestatus); 
		
		int results = query.executeUpdate();
		
		if (results > 0) {
			logger.info("Temporary User delete is Done...");
		}

		logger.info("Inside UserRegistrationDAOImpl :: deleteUserTemp() :: end");
		return results;
	}	
	

	
	
	@Override
	@Transactional
	public User markUserAsActive(String strID) throws RedTrackProcessingException {

		logger.info("Inside UserRegistrationDAOImpl :: markUserAsActive() :: start");
		User user = new User();
		UserTemp userTemp = findUserTempByID(strID);

		logger.info("markUserAsActive.findUserTempByID :: userTemp: " +  userTemp);
		
		if (null != userTemp) {
			User oldUserRecord = findUserByID(String.valueOf(userTemp.getId()));
			if (oldUserRecord!=null) {
				logger.info("markUserAsActive.findUserByID :: found old record: " +  oldUserRecord);
				oldUserRecord.setNickName(userTemp.getNickName());
				oldUserRecord.setRole(userTemp.getRole());
				oldUserRecord.setDeviceTokenString(userTemp.getDeviceTokenString());
				oldUserRecord.setDeviceID(userTemp.getDeviceID());
				oldUserRecord.setDevicePlatform(userTemp.getDevicePlatform());
				oldUserRecord.setDateCreated(userTemp.getDateCreated());				
				oldUserRecord.setStatus(BusinessConstants.STATUS_ACTIVE);
				save(oldUserRecord);
			} else {
				user.setId(userTemp.getId());
				user.setCountryCode(userTemp.getCountryCode());
				user.setNickName(userTemp.getNickName());
				user.setRole(userTemp.getRole());
				user.setPhoneNumber(userTemp.getPhoneNumber());
				user.setDeviceTokenString(userTemp.getDeviceTokenString());
				user.setDeviceID(userTemp.getDeviceID());
				user.setDevicePlatform(userTemp.getDevicePlatform());
				user.setDateCreated(userTemp.getDateCreated());
				user.setStatus(BusinessConstants.STATUS_ACTIVE);
				save(user);
				
				//delete from Temp table
				remove(userTemp);
				//todo: changed here and service generateOTP() line 78 
//				userTemp.setStatus(BusinessConstants.STATUS_ACTIVE);
//				save(userTemp);
			}
			
		}	
		
		logger.info("Inside UserRegistrationDAOImpl :: markUserAsActive() :: end " + userTemp);
		return user;	
	}	
	


	@Override
	@Transactional
	public User markUserAsActive(String strID, String strNewDeviceTokenString) throws RedTrackProcessingException {

		logger.info("Inside UserRegistrationDAOImpl :: markUserAsActive() :: start");
		User user = new User();
		UserTemp userTemp = findUserTempByID(strID);

		logger.info("markUserAsActive.findUserTempByID :: userTemp: " +  userTemp);
		
		if (null != userTemp) {
			User oldUserRecord = findUserByID(String.valueOf(userTemp.getId()));
			if (oldUserRecord!=null) {
				logger.info("markUserAsActive.findUserByID :: found old record: " +  oldUserRecord);
				oldUserRecord.setNickName(userTemp.getNickName());
				oldUserRecord.setRole(userTemp.getRole());
				oldUserRecord.setDeviceTokenString(strNewDeviceTokenString);
				oldUserRecord.setDeviceID(userTemp.getDeviceID());
				oldUserRecord.setDevicePlatform(userTemp.getDevicePlatform());
				oldUserRecord.setDateCreated(userTemp.getDateCreated());				
				oldUserRecord.setStatus(BusinessConstants.STATUS_ACTIVE);
				save(oldUserRecord);
			} else {
				user.setId(userTemp.getId());
				user.setCountryCode(userTemp.getCountryCode());
				user.setNickName(userTemp.getNickName());
				user.setRole(userTemp.getRole());
				user.setPhoneNumber(userTemp.getPhoneNumber());
				user.setDeviceTokenString(strNewDeviceTokenString);
				user.setDeviceID(userTemp.getDeviceID());
				user.setDevicePlatform(userTemp.getDevicePlatform());
				user.setDateCreated(userTemp.getDateCreated());
				user.setStatus(BusinessConstants.STATUS_ACTIVE);
				save(user);
				
				//delete from Temp table
				remove(userTemp);
 			}
			
		}	
		
		logger.info("Inside UserRegistrationDAOImpl :: markUserAsActive() :: end " + userTemp);
		return user;	
	}	
	
	
	@Override
	@Transactional
	public User updateUser(String strID, String usernickname, String userfullname, String userdevictokenstr) throws RedTrackProcessingException {

		logger.info("Inside UserRegistrationDAOImpl :: updateUser() :: start");
		
		User user = findUserByID(strID);

		logger.info("updateTempUser.findTempUserByPhoneNumber :: userTemp: " +  user);
		
		if (null != user) { 
			user.setNickName(usernickname);
			user.setFullName(userfullname);
			user.setDeviceTokenString(userdevictokenstr); 
			save(user);
		}

		logger.info("Inside UserRegistrationDAOImpl :: updateUser() :: end " + user);
		return user;	
	}
	
	
	@Override
	@Transactional
	public User updateUserbyID(String strID, String strNickName) throws RedTrackProcessingException {

		logger.info("Inside UserRegistrationDAOImpl :: updateUserbyID() :: start");
		
		User user = findUserByID(strID);

		logger.info("updateTempUser.findTempUserByPhoneNumber :: userTemp: " +  user);
		
		if (null != user) { 
			user.setNickName(strNickName);
			save(user);
		}

		logger.info("Inside UserRegistrationDAOImpl :: updateUserbyID() :: end " + user);
		return user;	
	}
	
	

	
	
	/**
	 * comments here
	 */
	@Override	
	public User findUserByID(String strID) throws RedTrackProcessingException {
		logger.info("Inside UserRegistrationDAOImpl :: findUserByID() :: start");
		

		User user = (User)find(User.class, Long.parseLong(strID));
		
		logger.info("Existing Entity from DB for User: " + user);	
		logger.info("Inside UserRegistrationDAOImpl :: findUserByID() :: end");
		
		return user;
	}
	
	 

	  
	
	/**
	 * comments here
	 */
	@Override
	@Transactional
	public void deleteUserByID(String strID) throws RedTrackProcessingException {
		logger.info("Inside UserRegistrationDAOImpl :: deleteUserByID()(" + strID + ")");
		
		User user = findUserByID(strID);
		
		if (null != user) {
			remove(user);
		}
//		Query query = getEm().createQuery(DAOConstants.QUERY_USER_SESSION_BY_SERVERID_DELETE);
//		query.setParameter(DAOConstants.SERVERNAME, serverName);
//		query.executeUpdate();
		
		logger.info("Inside UserRegistrationDAOImpl :: deleteUserByID() ID:(" + strID + ") deleted successfully");

	}
  
	 
	@SuppressWarnings({ "unchecked", "unused" })
 	private List<String> getDeviceTokenStringList_old(String struserotpid ) throws RedTrackProcessingException {
		logger.info("Inside UserRegistrationDAOImpl :: getDeviceTokenStringList() :: ("+struserotpid +") ::start" );
		Query query = getEm().createQuery(DAOConstants.QUERY_DEVICE_TOKEN_LIST);		
		query.setParameter("struserotpid", Long.parseLong(struserotpid));
		query.setParameter("status", "A");
		
		List<String> resultList = new ArrayList<String>();
//		try {
		List<String> results = query.getResultList();

		for (String record : results) {
			resultList.add(record);		
		}
//		} catch(Exception ex){
//			logger.info("Exception: "+ ex.getMessage());
//			ex.printStackTrace();
//		}
		logger.info("Inside ApplicationsDAOImpl :: getDeviceTokenStringList() :: end(" +resultList+")");

		return resultList;	 
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<DeviceTokenHelper> getDeviceTokenStringList(String struserotpid ) throws RedTrackProcessingException {
		logger.info("Inside UserRegistrationDAOImpl :: getDeviceTokenStringList() :: ("+struserotpid +") ::start" );
		Query query = getEm().createQuery(DAOConstants.QUERY_DEVICE_TOKEN_LIST);		
		query.setParameter("struserotpid", Long.parseLong(struserotpid));
		query.setParameter("status", "A");
		
		List<DeviceTokenHelper> resultList = new ArrayList<DeviceTokenHelper>();
		DeviceTokenHelper deviceTokenHelper = null;
		try {
			List<Object[]> results = query.getResultList();
	
			for (Object[] record: results) {
				deviceTokenHelper = new DeviceTokenHelper();
				deviceTokenHelper.setDeviceTokenString("" + record[0]);
				deviceTokenHelper.setDevicePlatform((int)record[1]);
				resultList.add(deviceTokenHelper);		
			}
		} catch(Exception ex){
			logger.info("Exception: "+ ex.getMessage());
			ex.printStackTrace();
		}
		logger.info("Inside ApplicationsDAOImpl :: getDeviceTokenStringList() :: end(" +resultList+")");

		return resultList;	 
	}
	
	


//
//	@Override
//	@Transactional
//	public User markUserAsActive(String strPhoneNumber, String strCountry) throws RedTrackProcessingException {
//
//		logger.info("Inside UserRegistrationDAOImpl :: markUserAsActive() :: start");
//		User user = new User();
//		UserTemp userTemp = findTempUserByPhoneNumber(strPhoneNumber, strCountry);
//		logger.info("updateTempUser.findTempUserByPhoneNumber :: userTemp: " +  userTemp);
//		
//		if (null != userTemp) {
//			//java.util.Date date = new Date();			
//			user.setId(userTemp.getId());
//			user.setCountry(userTemp.getCountryCode());
//			user.setFirstName(userTemp.getFirstName());
//			user.setLastName(userTemp.getLastName());
//			user.setNickName(userTemp.getNickName());
//			user.setPhoneNumber(userTemp.getPhoneNumber());
//			user.setDeviceTokenString(userTemp.getDeviceTokenString());
//			user.setDeiceID(userTemp.getDeviceID());
//			user.setDevicePlatform(userTemp.getDevicePlatform());
//			user.setDateCreated(userTemp.getDateCreated());
//			user.setStatus(BusinessConstants.STATUS_ACTIVE);
//			save(user);
//			userTemp.setStatus(BusinessConstants.STATUS_ACTIVE);
//			save(userTemp);
//		}
//		logger.info("Inside UserRegistrationDAOImpl :: markUserAsActive() :: end " + userTemp);
//		return user;	
//	}	
//	
//	
//	@SuppressWarnings("unchecked")
//	@Override
//	public UserTemp findTempUserByPhoneNumber(String strPhoneNumber) throws RedTrackProcessingException {
//		logger.info("Inside UserRegistrationDAOImpl :: findTempUserByPhoneNumber() :: start");
//		UserTemp usertemp = null;
//		Query query = getEm().createQuery(DAOConstants.QUERY_USER_TEMP_BY_PHONENUMBER);		 
//		query.setParameter("strPhoneNumber", strPhoneNumber);
//
//		List<UserTemp> results = query.getResultList();		
//			
//		if (null != results && results.size() != 0) {
// 			usertemp =  results.get(0);
//			logger.info("Existing Entity from DB for UserTemp: " +  results);	
//		}
//		
//		logger.info("Inside UserRegistrationDAOImpl :: findTempUserByPhoneNumber() :: end");
//
//		return  usertemp;
//	}
//	@Override
//	@Transactional
//	public UserTemp updateTempUser(String strPhoneNumber, String strOTP, String strCountry) throws RedTrackProcessingException {
//
//		logger.info("Inside UserRegistrationDAOImpl :: updateTempUser() :: start");
//		
//		UserTemp userTemp = findTempUserByPhoneNumber(strPhoneNumber, strCountry);
//
//		logger.info("updateTempUser.findTempUserByPhoneNumber :: userTemp: " +  userTemp);
//		
//		if (null != userTemp) {
//			java.util.Date date = new Date(); 
//			//userTemp.setStartDate(new java.sql.Timestamp(date.getTime()));			
//			userTemp.setStartDate(date);
//			date = new Date(System.currentTimeMillis() + 5*60*1000);
//			userTemp.setEndDate((new java.sql.Timestamp(date.getTime())));
//			userTemp.setOtp(strOTP);
//			
//			save(userTemp);
//		}
//		
//		
//		logger.info("Inside UserRegistrationDAOImpl :: updateTempUser() :: end " + userTemp);
//		return userTemp;	
//	}	
//	@Override
//	@Transactional
//	public User updateUser(String strPhoneNumber, String strNickName, String strCountry) throws RedTrackProcessingException {
//
//		logger.info("Inside UserRegistrationDAOImpl :: updateUser() :: start");
//		
//		User user = findUserByPhoneNumber(strPhoneNumber, strCountry);
//
//		logger.info("updateTempUser.findTempUserByPhoneNumber :: userTemp: " +  user);
//		
//		if (null != user) { 
//			user.setNickName(strNickName);
//			save(user);
//		}
//
//		logger.info("Inside UserRegistrationDAOImpl :: updateUser() :: end " + user);
//		return user;	
//	}
//	/**
//	 * comments here
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	@Transactional
//	public List<User> getUsers(String strCountry) throws RedTrackProcessingException {
//		logger.info("Inside UserRegistrationDAOImpl :: getUsers()(" + strCountry + ")");
//		
//		Query query = getEm().createQuery(DAOConstants.QUERY_USER__SELECT_ALL);		 
//		query.setParameter("country", strCountry);
//		//em.createQuery("SELECT c FROM Country c", Country.class);
//		//List<User> finalResults = new ArrayList<User>();
//		List<User> results = query.getResultList();		
//		  
////		for (Object[] record : results) {
////			int i = 0;
////			User user = new User();
////			employee.setEmployeeId(Long.parseLong(""+record[i++]));			
////			employee.setEmployeeName(""+record[i++]);
////			loggerger.info(""+record[i++]);
////			//employee.setEmployeeHireDate(""+record[i++]); 
////			employee.setEmployeeSalary(Double.valueOf(""+record[i++]));
////			finalResults.add(employee);
////		   
////		}		
//		logger.info("getEmployee() :: Results: " + results);
//		
//		return results;
//	}
//	
//	
//	
//	/**
//	 * comments here
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	@Transactional
//	public List<UserTemp> getUsersFromTemp(String strCountry, String strStatus) throws RedTrackProcessingException {
//		logger.info("Inside UserRegistrationDAOImpl :: getUsers()(" + strCountry +":" + strStatus + ")");
//		
//		Query query = getEm().createQuery(DAOConstants.QUERY_USER_TEMP__SELECT_ALL);		 
//		query.setParameter("strCountry", strCountry);
//		query.setParameter("strStatus", strStatus);
//		//em.createQuery("SELECT c FROM Country c", Country.class);
//		//List<User> finalResults = new ArrayList<User>();
//		List<UserTemp> results = query.getResultList();		
//		  
////		for (Object[] record : results) {
////			int i = 0;
////			User user = new User();
////			employee.setEmployeeId(Long.parseLong(""+record[i++]));			
////			employee.setEmployeeName(""+record[i++]);
////			loggerger.info(""+record[i++]);
////			//employee.setEmployeeHireDate(""+record[i++]); 
////			employee.setEmployeeSalary(Double.valueOf(""+record[i++]));
////			finalResults.add(employee);
////		   
////		}		
//		logger.info("getEmployee() :: Results: " + results);
//		
//		return results;
//	}
	 
}
