package com.example.demo.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.constants.Constants;
import com.example.demo.dto.UniqueCheckDTO;
import com.example.demo.model.CulturalOffer;
import com.example.demo.model.User;
import com.example.demo.repository.UserFollowingRepository;
import com.example.demo.repository.UserRepository;

@Component
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserFollowingRepository userFollowingRepository;
	
	@Autowired
	private ImageService imageService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.userRepository.findByEmail(username);	//ovu klasu cemo koristiti i za security
	}
	
	public User getCurrentUser() {
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (obj instanceof User) {
			return (User) obj;			
		}
		return null;
	}
	
	@Transactional(readOnly = false)
	public User save(User user, MultipartFile upload) throws FileNotFoundException, IOException {
		if (upload != null) {
			user.setImage(this.imageService.store(upload));
		}
		return this.userRepository.save(user);
	}
	
	@Transactional(readOnly = true)
	public boolean hasEmail(UniqueCheckDTO param) {
		User user = this.userRepository.hasEmail(param.getId(), param.getName());
		if (user == null) {
			return false;
		}
		return true;
	}
	
	@Transactional(readOnly = true)
	public boolean userIsFollowing(CulturalOffer culturalOffer) {
		User user = this.getCurrentUser();
		if (user == null) {
			return false;
		}
		if (user.getAuthority().getName() == Constants.ADMIN_AUTHORITY) {
			return false;
		}
		for (CulturalOffer co: this.userFollowingRepository.findCulturalOfferByUserId(user.getId())) {
			if (co.getId().equals(culturalOffer.getId())) {
				return true;
			}
		}
		return false;
	}

		
}
