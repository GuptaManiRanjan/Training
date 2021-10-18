package com.flightbooking.app.service;

import com.flightbooking.app.model.UserEntity;

public interface UserService 
{
	public int userRegister(UserEntity userEntity);
	public boolean userLogin(UserEntity userEntity);
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
}
