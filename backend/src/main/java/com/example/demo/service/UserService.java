package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.constants.Constants;
import com.example.demo.dto.UniqueCheckDTO;
import com.example.demo.model.AccountActivation;
import com.example.demo.model.CulturalOffer;
import com.example.demo.model.User;
import com.example.demo.repository.AccountActivationRepository;
import com.example.demo.repository.UserFollowingRepository;
import com.example.demo.repository.UserRepository;

@Service
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccountActivationRepository accountRepository;
	
	@Autowired
	private UserFollowingRepository userFollowingRepository;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private EmailService emailService;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) {
		return this.userRepository.findByEmail(username);	
	}
	
	@Transactional(readOnly = true)
	public User currentUser() {
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (obj instanceof User) {
			return (User) obj;			
		}
		return null;
	}
		
	@Transactional(readOnly = false)
	public void register(User user)  {
		this.userRepository.save(user);
		AccountActivation accountActivation = new AccountActivation(user);
		this.accountRepository.save(accountActivation);
		String link = Constants.FRONTEND_URL + "/user/activate/" + accountActivation.getCode();
		String message = "You have been successfully registered! Click on link " + link + " to activate your account.";
		this.emailService.sendEmail(new Email(user.getEmail(), "Account Activation", message));
	}
	
	@Transactional(readOnly = false)
	public void activate(String code) {
		User user =  this.accountRepository.findByCode(code).getUser();
		user.setEnabled(true);
		this.userRepository.save(user);
	}
	
	@Transactional(readOnly = false)
	public User save(User user, MultipartFile upload) {
		if (upload != null) {
			user.setImage(this.imageService.store(upload));
		}
		return this.userRepository.save(user);
	}
	
	@Transactional(readOnly = true)
	public boolean hasEmail(UniqueCheckDTO param) {
		return this.userRepository.hasEmail(param.getId(), param.getName()) != null;
	}
		
	@Transactional(readOnly = true)
	public boolean userIsFollowing(CulturalOffer culturalOffer) {
		User user = this.currentUser();
		if (user == null) {
			return false;
		}
		if (user.getAuthority().getName().equals(Constants.ADMIN_AUTHORITY)) {
			return false;
		}
		return this.userFollowingRepository.findByUserIdAndCulturalOfferId(user.getId(), culturalOffer.getId()) != null;
	}

}
