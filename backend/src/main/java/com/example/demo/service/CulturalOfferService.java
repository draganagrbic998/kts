package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.FilterParamsDTO;
import com.example.demo.model.CulturalOffer;
import com.example.demo.repository.CulturalOfferRepository;

@Component
@Transactional(readOnly = true)
public class CulturalOfferService {
	
	@Autowired
	private CulturalOfferRepository culturalOfferRepository;
		
	@Transactional(readOnly = true)
	public List<String> filterNames(String filterParam){
		return this.culturalOfferRepository.filterNames(filterParam);
	}
	
	@Transactional(readOnly = true)
	public List<String> filterLocations(String filterParam){
		return this.culturalOfferRepository.filterLocations(filterParam);
	}	
	
	@Transactional(readOnly = true)
	public List<String> filterTypes(String filterParam){
		return this.culturalOfferRepository.filterTypes(filterParam);
	}	

	@Transactional(readOnly = true)
	public Page<CulturalOffer> filter(FilterParamsDTO filterParams, Pageable pageable){
		System.out.println(this.culturalOfferRepository.count());
		return this.culturalOfferRepository.filter(filterParams.getName(), filterParams.getLocation(), filterParams.getType(), pageable);
	}

}
