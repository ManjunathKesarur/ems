package com.tcs.ems.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.ems.dto.RegisterRequest;
import com.tcs.ems.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	

	@PostMapping("/register")
	public String userRegister(@RequestBody RegisterRequest registerRequest) {
		return userService.register(registerRequest);
	}
	
}
