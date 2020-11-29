package com.example.demo.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.FilterParamsDTO;
import com.example.demo.dto.UniqueCheckDTO;
import com.example.demo.model.CulturalOffer;
import com.example.demo.repository.CulturalOfferRepository;

@Component
@Transactional(readOnly = true)
public class CulturalOfferService {
	
	@Autowired
	private CulturalOfferRepository culturalOfferRepository;
	
	@Autowired
	private ImageService imageService;
	
	@Transactional(readOnly = true)
	public List<String> filterNames(String filter){
		return this.culturalOfferRepository.filterNames(filter);
	}
	
	@Transactional(readOnly = true)
	public List<String> filterLocations(String filter){
		return this.culturalOfferRepository.filterLocations(filter);
	}	
	
	@Transactional(readOnly = true)
	public List<String> filterTypes(String filter){
		return this.culturalOfferRepository.filterTypes(filter);
	}	

	@Transactional(readOnly = true)
	public Page<CulturalOffer> filter(FilterParamsDTO filters, Pageable pageable){
		return this.culturalOfferRepository.filter(filters.getName(), filters.getLocation(), filters.getType(), pageable);
	}

	@Transactional(readOnly = false)
	public void delete(long id) {
		this.culturalOfferRepository.deleteById(id);
	}
	
	@Transactional(readOnly = true) 
	public boolean hasName(UniqueCheckDTO param) {
		CulturalOffer culturalOffer = this.culturalOfferRepository.hasName(param.getId(), param.getName());
		if (culturalOffer == null) {
			return false;
		}
		return true;
	}	
	
	@Transactional(readOnly = false)
	public CulturalOffer save(CulturalOffer culturalOffer, MultipartFile image) throws FileNotFoundException, IOException {
		if (image != null) {
			culturalOffer.setImage(this.imageService.store(image));
		}
		return this.culturalOfferRepository.save(culturalOffer);
	}

}
