package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;

@Component
@Transactional(readOnly = true)
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;

	@Transactional(readOnly = true)
	public Page<Comment> list(long culturalOfferId, Pageable pageable) {
		return this.commentRepository.findByCulturalOfferIdOrderByCreatedAtDesc(culturalOfferId, pageable);
	}
	
}
