package com.flightbooking.app.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.flightbooking.app.jwt.util.JwtUtil;
import com.flightbooking.app.model.AuthRequest;
import com.flightbooking.app.model.UserEntity;
import com.flightbooking.app.serviceImpl.CustomUserDetailsService;
import com.flightbooking.app.serviceImpl.UserServiceImpl;
import com.flightbooking.app.utils.NotificationByEmail;

@RestController
@RequestMapping("/flight/user")
@CrossOrigin(origins = "*")
public class UserController {
	private static Logger log = Logger.getLogger(UserController.class);
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private NotificationByEmail notificationByEmail;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@PostMapping("/register")
	@ResponseBody
	public String userRegister(@RequestBody UserEntity userEntity) {
		log.info("[UserController] signupUser in");
		String msg = null;
		try {
			if (userService.existsByUsername(userEntity.getUsername())) {
				msg = "Email already taken!";
				log.info("[UserController] signupUser::" + msg);
			} else if (userService.existsByEmail(userEntity.getEmail())) {
				msg = "Email already registered!";
				log.info("[UserController] signupUser::" + msg);
			} else {
				userEntity.setPassword(encoder.encode(userEntity.getPassword()));
				userEntity.setRole("USER");
				userEntity.setStatus("1");

				int id = userService.userRegister(userEntity);
				log.info("[UserController] signupUser Id::" + id);

				String text = "Hi " + userEntity.getName() + ", Your Signup successfully. Login User Id is :"
						+ userEntity.getUsername();
				if (id > 0) {
					msg = "User Registration successfully";
					notification(userEntity.getEmail(), text);
				}
			}
		} catch (Exception e) {
			msg = "Something went wrong!";
			log.error("[UserController] exception occured!!!" + e.getLocalizedMessage());
			e.printStackTrace();
		}
		return msg;
	}

	@PostMapping("/login")
	public LoginResponse userLogin(@RequestBody AuthRequest authRequest) {
		log.info("[AuthController(UserController)] authRequest in>>>");
		String jwt = null;
		String msg = null;
		String name = null;
		UserEntity userEntity = null;
		if(authRequest!=null && authRequest.getUsername()!=null && authRequest.getUsername()!=null) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
			msg = "Valid";
			userEntity = customUserDetailsService.userDetailByUsername(authRequest.getUsername());
			jwt = jwtUtil.generateToken(authRequest.getUsername());
			name=userEntity.getName();			
		} catch (NullPointerException e) {
			msg = "Invalid User Id and Password";
		} catch (Exception e) {
			msg = "Invalid User Id and Password";
			e.getMessage();
			log.error("[UserController] \"Invalid username and password!\"" + e.getLocalizedMessage());
		}

		return new LoginResponse(msg, jwt, name, authRequest.getUsername());
		}
		else {
			return new LoginResponse("Kindly Provide User ID and Password", jwt, name, authRequest.getUsername());
		}
	}

	public void notification(String email, String msg) {
		try {
			notificationByEmail.mailOTP(email, msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
			log.info("Mail SMTP Server is Busy......");
		}
	}

}
