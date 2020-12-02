package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.UniqueCheckDTO;
import com.example.demo.model.Type;
import com.example.demo.repository.TypeRepository;

@Component
@Transactional(readOnly = true)
public class TypeService {
	
	@Autowired
	private TypeRepository typeRepository;
	
	@Autowired
	private ImageService imageService;
	
	@Transactional(readOnly = true)
	public List<String> filterNames(String filter){
		return this.typeRepository.filterNames(filter);
	}
	
	@Transactional(readOnly = true)
	public Page<Type> list(Pageable pageable) {
		return this.typeRepository.findAll(pageable);
	}

	@Transactional(readOnly = false)
	public void save(Type type,MultipartFile upload) {
		if(upload != null) {
			try {
				type.setPlacemarkIcon(imageService.store(upload));
			}
			catch(Exception e) {
				;
			}
		}
		this.typeRepository.save(type);
	}
	
	@Transactional(readOnly = false)
	public void delete(long id) {
		this.typeRepository.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public boolean hasName(UniqueCheckDTO param) {
		Type type = this.typeRepository.findByName(param.getName());
		if (type == null) {
			return false;
		}
		return true;
	}

}
