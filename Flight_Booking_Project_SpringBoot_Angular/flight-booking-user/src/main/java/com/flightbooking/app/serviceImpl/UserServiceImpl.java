package com.flightbooking.app.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import com.flightbooking.app.model.UserEntity;
import com.flightbooking.app.repository.UserRepository;
import com.flightbooking.app.service.UserService;
@Service
public class UserServiceImpl implements UserService
{
	private static Logger log = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
    UserRepository userRepository;

	@Override
	public int userRegister(UserEntity userEntity) {
		UserEntity entity=userRepository.save(userEntity);
		return entity.getUserId();
	}

	@Override
	public boolean userLogin(UserEntity userEntity) {
		UserEntity entity=null;
		entity= userRepository.login(userEntity.getEmail(), userEntity.getPassword());
		
		return entity!=null?true:false;
	}

	@Override
	public boolean existsByUsername(String username) {
		log.info("[UserServiceImpl] method=existsByUsername param:"+username);
		return userRepository.existsByUsername(username);
	}

	@Override
	public boolean existsByEmail(String email) {
		log.info("[UserServiceImpl] method=existsByEmail param:"+email);
		return userRepository.existsByEmail(email);
	}
	
}
