package com.dbs.redtrack.exception;

public class RedTrackException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String errorCode;
	private String errorMessage;
	
	
	public RedTrackException() {
		super();
	}

    public RedTrackException(String errorCode) {
        super(errorCode);
    	this.errorCode = errorCode;
    }
    
	public RedTrackException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
    public RedTrackException(String errorCode, Throwable throwable) {
        super(errorCode, throwable);
        this.errorCode = errorCode;
        this.errorMessage = throwable.getMessage();
    }

    public RedTrackException(Throwable throwable) {
        super(throwable);
    }

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
    
}
