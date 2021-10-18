package com.flightbooking.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flightbooking.app.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>
{
	@Query("from UserEntity where email=:email and password=:password")
    UserEntity login(@Param("email") String email, @Param("password") String password);
	
	UserEntity findByUsername(String username);
	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
