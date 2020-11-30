package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.CulturalOffer;

public interface CulturalOfferRepository extends JpaRepository<CulturalOffer, Long> {

	@Query("select distinct co.name from CulturalOffer co where lower(co.name) like lower(concat('%', :filter, '%'))")
	public List<String> filterNames(String filter);
	
	@Query("select distinct co.location from CulturalOffer co where lower(co.location) like lower(concat('%', :filter, '%'))")
	public List<String> filterLocations(String filter);
	
	@Query("select distinct co.type.name from CulturalOffer co where lower(co.type.name) like lower(concat('%', :filter, '%'))")
	public List<String> filterTypes(String filter);
	
	@Query("select co from CulturalOffer co where (lower(co.name) like lower(concat('%', :name, '%')) or :name is null) and (lower(co.location) like lower(concat('%', :location, '%')) or :location is null) and (lower(co.type.name) like lower(concat('%', :type, '%')) or :type is null)")
    public Page<CulturalOffer> filter(String name, String location, String type, Pageable pageable);

	@Query("select co from CulturalOffer co where (co.id != :id or :id is null) and co.name=:name")
	public CulturalOffer hasName(Long id, String name);
		
}
