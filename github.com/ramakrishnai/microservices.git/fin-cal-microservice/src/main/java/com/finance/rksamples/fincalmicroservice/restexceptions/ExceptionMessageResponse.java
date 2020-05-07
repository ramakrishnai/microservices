package com.finance.rksamples.fincalmicroservice.restexceptions;

import java.util.Date;


public class ExceptionMessageResponse {
	
	//Timestamp, Exception Message, Details
	//check the industry structure to create similar properties.
	private Date timestamp;
	private String message;
	private String details;
	
	
	public ExceptionMessageResponse(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	
	
	
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
	

}
