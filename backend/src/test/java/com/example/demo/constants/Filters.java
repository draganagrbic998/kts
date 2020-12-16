package com.example.demo.constants;

import org.springframework.stereotype.Component;

import com.example.demo.dto.FilterParamsDTO;

@Component
public class Filters {

	public FilterParamsDTO filtersEmpty() {
		FilterParamsDTO filters = new FilterParamsDTO();
		filters.setName(FilterConstants.FILTER_ALL);
		filters.setLocation(FilterConstants.FILTER_ALL);
		filters.setType(FilterConstants.FILTER_ALL);
		return filters;
	}
	
	public FilterParamsDTO filtersAll() {
		FilterParamsDTO filters = new FilterParamsDTO();
		filters.setName(CulturalOfferConstants.FILTER_NAME_ALL);
		filters.setLocation(CulturalOfferConstants.FILTER_LOCATION_ALL);
		filters.setType(CulturalOfferConstants.FILTER_TYPE_ALL);
		return filters;
	}
	
	public FilterParamsDTO filtersOne() {
		FilterParamsDTO filters = new FilterParamsDTO();
		filters.setName(FilterConstants.FILTER_ONE);
		filters.setLocation(FilterConstants.FILTER_ONE);
		filters.setType(FilterConstants.FILTER_ONE);
		return filters;
	}
	
	public FilterParamsDTO filtersOneName() {
		FilterParamsDTO filters = new FilterParamsDTO();
		filters.setName(FilterConstants.FILTER_ONE);
		filters.setLocation(FilterConstants.FILTER_ALL);
		filters.setType(FilterConstants.FILTER_ALL);
		return filters;
	}
	
	public FilterParamsDTO filtersOneLocation() {
		FilterParamsDTO filters = new FilterParamsDTO();
		filters.setName(FilterConstants.FILTER_ALL);
		filters.setLocation(FilterConstants.FILTER_ONE);
		filters.setType(FilterConstants.FILTER_ALL);
		return filters;
	}
	
	public FilterParamsDTO filtersOneType() {
		FilterParamsDTO filters = new FilterParamsDTO();
		filters.setName(FilterConstants.FILTER_ALL);
		filters.setLocation(FilterConstants.FILTER_ALL);
		filters.setType(FilterConstants.FILTER_ONE);
		return filters;
	}
	
	public FilterParamsDTO filtersNone() {
		FilterParamsDTO filters = new FilterParamsDTO();
		filters.setName(FilterConstants.FILTER_ALL);
		filters.setLocation(FilterConstants.FILTER_ALL);
		filters.setType(FilterConstants.FILTER_NONE);
		return filters;
	}
	
}
