package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.constants.Constants;
import com.example.demo.model.AccountActivation;
import com.example.demo.model.User;
import com.example.demo.repository.AccountActivationRepository;

@Service
@Transactional(readOnly = true)
public class AccountActivationService {
	
	@Autowired
	private AccountActivationRepository accountRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserService userService;

	@Transactional(readOnly = false)
	public AccountActivation save(User user) {
		AccountActivation accountActivation = new AccountActivation(user);
		this.accountRepository.save(accountActivation);
		String link = Constants.FRONTEND_URL + "/user/activate/" + accountActivation.getCode();
		String message = "You have been successfully registered! Click on link " + link + " to activate your account.";
		this.emailService.sendEmail(new Email(user.getEmail(), "Account Activation", message));
		return accountActivation;
	}
	
	@Transactional(readOnly = false)
	public void activate(String code) {
		User user =  this.accountRepository.findByCode(code).getUser();
		user.setEnabled(true);
		this.userService.save(user, null);
	}
	
}
