package com.tcs.ems.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tcs.ems.dto.VerifyOtpRequest;
import com.tcs.ems.entity.User;
import com.tcs.ems.repository.UserRepository;

@Service
public class OtpService {
	private UserRepository userRepository;

	public OtpService(UserRepository userRepository) {
		
		this.userRepository = userRepository;
	}
public String VerifyOtp(VerifyOtpRequest verifyOtpRequest) {
	Optional<User> optionalUser = userRepository.getByEmail(verifyOtpRequest.getEmail());
	if(optionalUser.isPresent()) {
		User user = optionalUser.get();
		if(!user.getOtp().equals(verifyOtpRequest.getOtp())) {
		
			return " invalid OTP";
	}
		if(LocalDateTime.now().isAfter(user.getOtpexpirytime())) {
			return "OTP expired";
		}
		else {
			user.setVerified(true);
			user.setOtp(null);
			user.setOtpexpirytime(null);
			userRepository.save(user);
			return"otp verified successfully";
		}
}else {
	return "no such user is present";
}
	
}}
