package com.example.demo.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.constants.Constants;
import com.example.demo.dto.ProfileUploadDTO;
import com.example.demo.dto.RegisterDTO;
import com.example.demo.model.Authority;
import com.example.demo.model.User;
import com.example.demo.repository.AuthorityRepository;
import com.example.demo.service.UserService;

@Component
public class UserMapper {
	
	@Autowired
	private AuthorityRepository authorityRepository;

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authManager;
			
	@Transactional(readOnly = true)
	public User map(RegisterDTO userDTO) {
		User user = new User();
		user.setEmail(userDTO.getEmail());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setPassword(this.passwordEncoder.encode(userDTO.getPassword()));
		Set<Authority> authorities = new HashSet<Authority>();
		authorities.add(this.authorityRepository.findByName(Constants.GUEST_AUTHORITY));
		user.setAuthorities(authorities);
		return user;
	}
	
	public User map(ProfileUploadDTO userDTO) {
		User user = this.userService.currentUser();
		user.setEmail(userDTO.getEmail());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		if (userDTO.getNewPassword() != null) {
			this.authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), userDTO.getOldPassword()));			
			user.setPassword(this.passwordEncoder.encode(userDTO.getNewPassword()));
		}
		user.setImage(userDTO.getImagePath());
		return user;
	}

}
