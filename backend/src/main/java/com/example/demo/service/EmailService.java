package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
	@Autowired
	private JavaMailSenderImpl sender;

	@Async
	public void sendMessage(Email poruka) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("KTS-NVT");
		message.setTo(poruka.getTo());
		message.setSubject(poruka.getTitle());
		message.setText(poruka.getText());
		this.sender.send(message);
		
	}
}
