package com.flightbooking.app.controller;


public class LoginResponse {
	
	private String msg;
	
	private String accessToken;
	
	private String name;
	
	private String username;

		
	public LoginResponse(String msg, String accessToken, String name, String username) {
		super();
		this.msg = msg;
		this.accessToken = accessToken;
		this.name = name;
		this.username = username;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}	

	
	

}
