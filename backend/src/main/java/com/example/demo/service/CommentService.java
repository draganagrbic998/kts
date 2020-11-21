package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.CommentRepository;

@Component
@Transactional(readOnly = true)
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Transactional(readOnly = false)
	public void deleteByCulturalOfferId(long culturalOfferId) {
		this.commentRepository.deleteByCulturalOfferId(culturalOfferId);
	}
}
