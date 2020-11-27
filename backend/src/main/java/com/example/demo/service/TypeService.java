package com.example.demo.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Type;
import com.example.demo.repository.TypeRepository;

@Component
@Transactional(readOnly = true)
public class TypeService {
	
	@Autowired
	private TypeRepository typeRepository;

	public Collection<Type> list() {
		return typeRepository.findAll();
	}
	
	@Transactional(readOnly = false)
	public void delete(long id) {
		this.typeRepository.deleteById(id);
	}

}
