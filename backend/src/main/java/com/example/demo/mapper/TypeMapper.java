package com.example.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.TypeDTO;
import com.example.demo.dto.TypeUploadDTO;
import com.example.demo.model.Type;
import com.example.demo.repository.CategoryRepository;

@Component
public class TypeMapper {
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<TypeDTO> map(List<Type> types) {
		return types.stream().map(type -> new TypeDTO(type)).collect(Collectors.toList());
	}

	public Type map(TypeUploadDTO typeDTO) {
		Type type = new Type();
		type.setName(typeDTO.getName());
		type.setCategory(categoryRepository.findByName(typeDTO.getCategory()));
		return type;
	}

}
