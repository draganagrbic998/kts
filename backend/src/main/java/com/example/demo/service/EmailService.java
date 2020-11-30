package com.example.demo.service;

import java.io.File;
import java.util.Set;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.example.demo.model.Image;

@Component
public class EmailService {
	
	@Autowired
	private JavaMailSender sender;

	@Autowired
	private JavaMailSenderImpl senderImpl;
	
	@Async
	public void sendMessage(Email email) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email.getTo());
		message.setSubject(email.getSubject());
		message.setText(email.getText());
		this.senderImpl.send(message);
	}
	
	@Async
	public void sendMessageWithAttachments(String userEmail, String title, String text, Set<Image> images) {
	    MimeMessagePreparator preparator = new MimeMessagePreparator() 
	    {
			@Override
	        public void prepare(MimeMessage mimeMessage) throws Exception 
	        {
	            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
	            mimeMessage.setFrom(new InternetAddress("admin@gmail.com"));
	            mimeMessage.setSubject(title);
	            mimeMessage.setText(text);
	            
	            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	            helper.setText(text, true);
	            FileSystemResource file = null;
	            
	            for (Image image : images) {
	            	String[] temp = image.getPath().split("/");
	            	file = new FileSystemResource(new File("../backend/src/main/resources/static/" + temp[temp.length - 1]));
		            helper.addAttachment(temp[temp.length - 1], file);
	            }
	        }
	    };
	     
	    try {
	    	sender.send(preparator);
	    }
	    catch (MailException ex) {
	        // simply log it and go on...
	        System.err.println(ex.getMessage());
	    }
	}

}
