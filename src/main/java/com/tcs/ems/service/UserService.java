package com.tcs.ems.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tcs.ems.dto.RegisterRequest;
import com.tcs.ems.entity.User;
import com.tcs.ems.repository.UserRepository;

@Service
public class UserService {
	
	
	

	private UserRepository userRepository;


	
	
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}





	public String register(RegisterRequest registerRequest) {
		
		Optional<User> ou= userRepository.getByEmail(registerRequest.getEmail());
		if(ou.isPresent()) {
			return "The Email Is Already Exists Please Enter The Unique Email";
		}else {
			User user=new User();
			user.setName(registerRequest.getName());
			user.setEmail(registerRequest.getEmail());
			user.setPassword(registerRequest.getPassword());
			
			userRepository.save(user);
			
			return "Please Enter The Otp For Verification";
		}
	}
	
	
}
