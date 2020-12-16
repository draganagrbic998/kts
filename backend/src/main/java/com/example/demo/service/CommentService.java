package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Comment;
import com.example.demo.model.Image;
import com.example.demo.repository.CommentRepository;

@Service
@Transactional(readOnly = true)
public class CommentService {
		
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ImageService imageService;
	
	@Transactional(readOnly = true)
	public double totalRate(long culturalOfferId) {
		return this.commentRepository.totalRate(culturalOfferId);
	}
	
	@Transactional(readOnly = true)
	public Page<Comment> list(long culturalOfferId, Pageable pageable) {
		return this.commentRepository.findByCulturalOfferIdOrderByCreatedAtDesc(culturalOfferId, pageable);
	}
	
	@Transactional(readOnly = false)
	public long delete(long id) {		
		Comment comment = this.commentRepository.findById(id).orElse(null);
		long culturalOfferId = comment != null ? comment.getCulturalOffer().getId() : -1;
		this.commentRepository.deleteById(id);
		return culturalOfferId;
	}
	
	@Transactional(readOnly = false)
	public Comment save(Comment comment, List<MultipartFile> uploads) {
		if (uploads != null) {
			uploads.stream().forEach(upload -> {
				Image image = new Image(this.imageService.store(upload));
				comment.addImage(image);
				this.imageService.save(image);
			});
		}
		return this.commentRepository.save(comment);
	}
		
}
