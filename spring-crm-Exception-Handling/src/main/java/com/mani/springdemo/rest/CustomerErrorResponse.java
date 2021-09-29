package com.mani.springdemo.rest;

public class CustomerErrorResponse {
	
	private int status;
	private String message;
	private long timeSatamp;
	
	public CustomerErrorResponse() {
	}
	
	public CustomerErrorResponse(int status, String message, long timeSatamp) {
		this.status = status;
		this.message = message;
		this.timeSatamp = timeSatamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTimeSatamp() {
		return timeSatamp;
	}

	public void setTimeSatamp(long timeSatamp) {
		this.timeSatamp = timeSatamp;
	}
	
	
}
