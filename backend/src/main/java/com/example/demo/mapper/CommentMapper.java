package com.example.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.CommentUploadDTO;
import com.example.demo.model.Comment;
import com.example.demo.model.CulturalOffer;
import com.example.demo.model.User;
import com.example.demo.repository.CulturalOfferRepository;
import com.example.demo.repository.ImageRepository;
import com.example.demo.service.UserService;

@Component
public class CommentMapper {
	
	@Autowired
	private CulturalOfferRepository culturalOfferRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ImageRepository imageRepository;

	public List<CommentDTO> map(List<Comment> comments){
		return comments.stream().map(comment -> new CommentDTO(comment)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public Comment map(long culturalOfferId, CommentUploadDTO commentDTO) {
		CulturalOffer culturalOffer = this.culturalOfferRepository.findById(culturalOfferId).get();
		User user = this.userService.currentUser();
		Comment comment = new Comment();
		comment.setId(commentDTO.getId());
		comment.setRate(commentDTO.getRate());
		comment.setText(commentDTO.getText());
		comment.setCulturalOffer(culturalOffer);
		comment.setUser(user);
		if (commentDTO.getImagePaths() != null) {
			for (String image: commentDTO.getImagePaths()) {
				comment.addImage(this.imageRepository.findByPath(image));
			}			
		}
		return comment;
	}
	
}
