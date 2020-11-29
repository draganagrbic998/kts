package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constants.Constants;
import com.example.demo.dto.CulturalOfferDTO;
import com.example.demo.dto.FilterParamsDTO;
import com.example.demo.dto.UniqueCheckDTO;
import com.example.demo.mapper.CulturalOfferMapper;
import com.example.demo.model.CulturalOffer;
import com.example.demo.service.CulturalOfferService;

@RestController
@RequestMapping(value = "/api/cultural_offers", produces = MediaType.APPLICATION_JSON_VALUE)
@PreAuthorize("permitAll()")
public class CulturalOfferController {
	
	@Autowired
	private CulturalOfferService culturalOfferService;
	
	@Autowired
	private CulturalOfferMapper culturalOfferMapper;
	
	@PostMapping(value = "/filter_names")
	public ResponseEntity<List<String>> filterNames(@RequestBody String filter){
		return new ResponseEntity<>(this.culturalOfferService.filterNames(filter), HttpStatus.OK);
	}
	
	@PostMapping(value = "/filter_locations")
	public ResponseEntity<List<String>> filterLocations(@RequestBody String filter){
		return new ResponseEntity<>(this.culturalOfferService.filterLocations(filter), HttpStatus.OK);
	}
	
	@PostMapping(value = "/filter_types")
	public ResponseEntity<List<String>> filterTypes(@RequestBody String filter){
		return new ResponseEntity<>(this.culturalOfferService.filterTypes(filter), HttpStatus.OK);
	}

	@PostMapping(value = "/filter")
	public ResponseEntity<List<CulturalOfferDTO>> filter(@RequestParam int page, @RequestParam int size, @RequestBody FilterParamsDTO filters, HttpServletResponse response){
		Pageable pageable = PageRequest.of(page, size);
		Page<CulturalOffer> culturalOffers = this.culturalOfferService.filter(filters, pageable);
		response.setHeader(Constants.ENABLE_HEADER, Constants.FIRST_PAGE_HEADER + ", " + Constants.LAST_PAGE_HEADER);
		response.setHeader(Constants.FIRST_PAGE_HEADER, culturalOffers.isFirst() + "");
		response.setHeader(Constants.LAST_PAGE_HEADER, culturalOffers.isLast() + "");
		return new ResponseEntity<>(this.culturalOfferMapper.map(culturalOffers.toList()), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('admin')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable long id) {
		this.culturalOfferService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping(value = "/has_name")
	public ResponseEntity<Boolean> hasName(@RequestBody UniqueCheckDTO param) {
		return new ResponseEntity<>(this.culturalOfferService.hasName(param), HttpStatus.OK);
	}
	
}
