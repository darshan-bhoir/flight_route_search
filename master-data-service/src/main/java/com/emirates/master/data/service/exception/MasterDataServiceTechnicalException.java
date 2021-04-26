package com.emirates.master.data.service.exception;

public class MasterDataServiceTechnicalException  extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private String errorCode;
	private String errorMessage;
	private Throwable exception;

	public MasterDataServiceTechnicalException() {}

	public MasterDataServiceTechnicalException(String errorCode, String errorMessage, Throwable ex) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.exception = ex;
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

	public Throwable getException() {
		return exception;
	}

	public void setException(Throwable exception) {
		this.exception = exception;
	}
}
