package com.example.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.demo.dto.CommentDTO;
import com.example.demo.model.Comment;

@Component
public class CommentMapper {

	public List<CommentDTO> map(List<Comment> comments){
		return comments.stream().map(comment -> new CommentDTO(comment)).collect(Collectors.toList());
	}
	
}
