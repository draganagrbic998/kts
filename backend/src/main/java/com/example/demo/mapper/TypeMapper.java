package com.example.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.demo.dto.TypeDTO;
import com.example.demo.model.Type;

@Component
public class TypeMapper {
	
	public List<TypeDTO> map(List<Type> types) {
		return types.stream().map(type -> new TypeDTO(type)).collect(Collectors.toList());
	}

}
