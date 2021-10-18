package com.flightbooking.app.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.flightbooking.app.model.UserEntity;
import com.flightbooking.app.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService
{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity= userRepository.findByUsername(username);
		return new User(userEntity.getUsername(),userEntity.getPassword(),new ArrayList<>());
	}
	
	public UserEntity userDetailByUsername(String username) {
		return userRepository.findByUsername(username);
		
	}

}