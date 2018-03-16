package com.dbs.redtrack.base.dto;

public class ResponseDTO<T> extends AbstractBaseDTO{

	private static final long serialVersionUID = 1L;

	private HeaderDTO header;

	private T body;

	public HeaderDTO getHeader() {
		return header;
	}

	public void setHeader(HeaderDTO header) {
		this.header = header;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}
	
	
}

