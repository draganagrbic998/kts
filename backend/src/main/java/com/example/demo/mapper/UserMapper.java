package com.example.demo.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.dto.ProfileUploadDTO;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Component
public class UserMapper {
		
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User map(ProfileUploadDTO userDTO) {
		User user = this.userService.getCurrentUser();
		user.setEmail(userDTO.getEmail());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		if (userDTO.getNewPassword() != null) {
			user.setPassword(this.passwordEncoder.encode(userDTO.getNewPassword()));
		}
		user.setImage(userDTO.getImagePath());
		return user;
	}

}
