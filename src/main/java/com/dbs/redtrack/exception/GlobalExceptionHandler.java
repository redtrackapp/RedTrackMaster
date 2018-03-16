package com.dbs.redtrack.exception;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.twilio.sdk.TwilioRestException;


@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger log = Logger.getLogger(GlobalExceptionHandler.class);
	
	@Autowired
	ErrorMessage errorMessage;
	 
	
	@ExceptionHandler(value = RedTrackProcessingException.class)
	@ResponseBody
	public ResponseEntity<ErrorMessage> handleProcessingException(RedTrackProcessingException e){
		log.error("GlobalExceptionHandler.handleProcessingException()" + e);
		e.printStackTrace();
		errorMessage.setErrorCode(e.getErrorCode());
		errorMessage.setErrorMessage(e.getErrorMessage());
		if (e.getErrorCode().equals("00005"))
			return new ResponseEntity<ErrorMessage>(errorMessage,HttpStatus.OK);
		else
			return new ResponseEntity<ErrorMessage>(errorMessage,HttpStatus.BAD_REQUEST);
		//return new ResponseEntity<ErrorMessage>(errorMessage,HttpStatus.OK);
	}

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ResponseEntity<ErrorMessage> handleGenericException(Exception e){
		
		log.error("GlobalExceptionHandler.handleGenericException()" ,e);
		errorMessage.setErrorCode("987");
		errorMessage.setErrorMessage("bad Request");
		return new ResponseEntity<ErrorMessage>(errorMessage,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	@ResponseBody
	public ResponseEntity<ErrorMessage> handleRequestException(HttpMessageNotReadableException e){
		
		log.info("GlobalExceptionHandler.handleRequestException()" +e.getMessage());
		errorMessage.setErrorCode("88");
		
		errorMessage.setErrorMessage("bad Request.....");
		return new ResponseEntity<ErrorMessage>(errorMessage,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = TwilioRestException.class)
	@ResponseBody
	public ResponseEntity<ErrorMessage> handleGenericException(TwilioRestException e){
		
		log.error("GlobalExceptionHandler.handleGenericException()" ,e);
		errorMessage.setErrorCode("988");
		errorMessage.setErrorMessage("Error sending SMS");
		return new ResponseEntity<ErrorMessage>(errorMessage,HttpStatus.BAD_REQUEST);
	}
	
	
}
