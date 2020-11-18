package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.FilterParamsDTO;
import com.example.demo.model.CulturalOffer;
import com.example.demo.repository.UserFollowingRepository;

@Component
@Transactional(readOnly = true)
public class UserFollowingService {
	
	@Autowired
	private UserFollowingRepository userFollowingRepository;
	
	@Autowired
	private UserService userService;
	
	@Transactional(readOnly = true)
	public Page<CulturalOffer> filter(FilterParamsDTO filterParams, Pageable pageable){
		return this.userFollowingRepository.filterCulturalOffers(this.userService.getCurrentUser().getId(), filterParams.getName(), filterParams.getLocation(), filterParams.getType(), pageable);
	}

}
