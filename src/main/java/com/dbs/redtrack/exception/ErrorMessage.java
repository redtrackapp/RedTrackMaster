package com.dbs.redtrack.exception;


import java.io.Serializable;

import org.springframework.stereotype.Component;
 
@Component
public class ErrorMessage implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String errorCode = null;
	private String errorMessage = null;

	/**
	 * 
	 */
	public ErrorMessage(){
		super();
	}
	
	/**
	 * @param errorCode
	 * @param errorMessage
	 */
	public ErrorMessage(String errorCode, String errorMessage){
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	/**
	 * @return
	 */
	public String getErrorCode() {
		return errorCode;
	}
	
	/**
	 * @param errorCode
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	/**
	 * @return
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	
	/**
	 * @param errorMessage
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
