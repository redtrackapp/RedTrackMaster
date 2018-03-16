package com.dbs.redtrack.integration.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dbs.redtrack.dto.helper.DeviceTokenHelper;
import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.jpa.entity.User;
import com.dbs.redtrack.jpa.entity.UserTemp;
import com.dbs.redtrack.jpa.entity.WhiteList;
 

@Repository
public interface UserRegistrationDAO {
	
	public boolean checkUserFromWhiteList(String strPhoneNumber) throws RedTrackProcessingException;
	public WhiteList getUserFromWhiteList(String strPhoneNumber) throws RedTrackProcessingException;
	public UserTemp findTempUserByPhoneNumber(String strPhoneNumber, String strCountryCode) throws RedTrackProcessingException;
	public User findUserByPhoneNumber(String strPhoneNumber, String strCountryCode) throws RedTrackProcessingException;
	public void registerTempUser(UserTemp tempUser) throws RedTrackProcessingException;
	
	public UserTemp findUserTempByID(String strID) throws RedTrackProcessingException;
	public User markUserAsActive(String strID) throws RedTrackProcessingException;
	
	
	public User updateUser(String userotpid, String usernickname, String userfullname, String userdevictokenstr) throws RedTrackProcessingException;
	public User updateUserbyID(String strID, String strNickName) throws RedTrackProcessingException;
	
	public User findUserByID(String strID) throws RedTrackProcessingException;
	public void deleteUserByID(String strID) throws RedTrackProcessingException;
	public int deleteUserTemp() throws RedTrackProcessingException;
	public List<DeviceTokenHelper> getDeviceTokenStringList(String struserotpid) throws RedTrackProcessingException;
	public User markUserAsActive(String strID, String strNewDeviceTokenString) throws RedTrackProcessingException;

 
	
	
	
//	public UserTemp updateTempUser(String strPhoneNumber, String strOTP, String strCountry) throws RedTrackProcessingException;	
//	public User markUserAsActive(String strPhoneNumber, String strCountry) throws RedTrackProcessingException;	
//	public UserTemp findTempUserByPhoneNumber(String strPhoneNumber) throws RedTrackProcessingException;
//	public UserTemp findTempUserByPhoneNumber(String strPhoneNumber, String strCountryCode) throws RedTrackProcessingException ;	
//	public User updateUser(String strPhoneNumber, String strNickName, String strCountryCode) throws RedTrackProcessingException;
//	public List<User> getUsers(String strCountry);	
//	public void registerUser(User user) throws RedTrackProcessingException;
//	public List<UserTemp> getUsersFromTemp(String strCountry, String strStatus) throws RedTrackProcessingException;
	
	
	
	
	


	
	
}
