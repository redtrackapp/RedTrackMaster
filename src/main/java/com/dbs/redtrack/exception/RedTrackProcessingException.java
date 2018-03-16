package com.dbs.redtrack.exception;

public class RedTrackProcessingException extends RedTrackException {

	private static final long serialVersionUID = 1L;

	/*
	* No Argument Constructor
	*/
    public RedTrackProcessingException() {
    }

	/* 
	*@ argument constructor
	*@ param String errorCode
	*/
    public RedTrackProcessingException(String errorCode) {
        super(errorCode);
    }

    /* 
	*@ argument constructor
	*@ param String errorCode, String errorMessage
	*/
    public RedTrackProcessingException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    /* 
	*@ argument constructor
	*@ param String strErrorCode,Thorwable object
	*/
    public RedTrackProcessingException(String errorCode, Throwable throwable) {
        super(errorCode, throwable);
    }

	/* 
	*@ argument constructor
	*@ param Thorwable object
	*/
    public RedTrackProcessingException(Throwable throwable) {
        super(throwable);
    }	
}
