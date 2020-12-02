package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Comment;
import com.example.demo.model.Image;
import com.example.demo.repository.CommentRepository;

@Component
@Transactional(readOnly = true)
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ImageService imageService;
	
	@Transactional(readOnly = true)
	public Page<Comment> list(long culturalOfferId, Pageable pageable) {
		return this.commentRepository.findByCulturalOfferIdOrderByCreatedAtDesc(culturalOfferId, pageable);
	}
	
	@Transactional(readOnly = false)
	public double save(Comment comment, List<MultipartFile> uploads) {
		if (uploads != null) {
			uploads.stream().forEach(upload -> {
				try {
					Image image = new Image(this.imageService.store(upload));
					comment.addImage(image);
					this.imageService.save(image);
				} 
				catch (Exception e) {
					;
				}
			});
		}
		this.commentRepository.save(comment);
		return this.commentRepository.totalRate(comment.getCulturalOffer().getId());
	}

	@Transactional(readOnly = false)
	public void delete(long id) {
		this.commentRepository.deleteById(id);
	}
		
}
