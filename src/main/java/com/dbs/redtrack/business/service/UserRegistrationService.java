/**
 * 
 */
package com.dbs.redtrack.business.service;

import com.dbs.redtrack.exception.RedTrackProcessingException;
import com.dbs.redtrack.request.dto.GenerateOTPRequestDTO;
import com.dbs.redtrack.request.dto.RegisterUserRequestDTO;
import com.dbs.redtrack.request.dto.UpdateUserRequestDTO;
import com.dbs.redtrack.request.dto.ValidateOTPRequestDTO;
import com.dbs.redtrack.response.dto.GenerateOTPResponseDTO;
import com.dbs.redtrack.response.dto.GetUserRoleResponseDTO;
import com.dbs.redtrack.response.dto.RegisterUserResponseDTO;
import com.dbs.redtrack.response.dto.UpdateUserResponseDTO;
import com.dbs.redtrack.response.dto.ValidateOTPResponseDTO;
import com.twilio.sdk.TwilioRestException;

public interface UserRegistrationService {
	
	
	public GenerateOTPResponseDTO generateOTP(GenerateOTPRequestDTO generateOTPRequestDTO)throws RedTrackProcessingException, TwilioRestException;
	public ValidateOTPResponseDTO validateOTP(ValidateOTPRequestDTO validateOTPRequestDTO) throws RedTrackProcessingException;
	public RegisterUserResponseDTO registerTempUser(RegisterUserRequestDTO registerUserRequestDTO) throws RedTrackProcessingException;
	public UpdateUserResponseDTO updateUser(UpdateUserRequestDTO updateUserRequestDTO) throws RedTrackProcessingException;
	

	public UpdateUserResponseDTO findActiveUserByID(UpdateUserRequestDTO updateUserRequestDTO) throws RedTrackProcessingException;	
	public int deleteUserTemp() throws RedTrackProcessingException;
	public UpdateUserResponseDTO findTempUserByID(UpdateUserRequestDTO updateUserRequestDTO)
			throws RedTrackProcessingException;
	public GetUserRoleResponseDTO getUserRole(String userOTPID) throws RedTrackProcessingException;
	

}
