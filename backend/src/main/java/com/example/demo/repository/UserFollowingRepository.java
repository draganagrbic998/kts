package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.CulturalOffer;
import com.example.demo.model.UserFollowing;

public interface UserFollowingRepository extends JpaRepository<UserFollowing, Long> {

	@Query("select uf.culturalOffer from UserFollowing uf where uf.user.id=:id")
	public List<CulturalOffer> findCulturalOfferByUserId(long id);
	
	@Query("select uf.culturalOffer from UserFollowing uf where uf.user.id = :id and ((lower(uf.culturalOffer.name) like lower(concat('%', :name, '%')) or :name='') and (lower(uf.culturalOffer.location) like lower(concat('%', :location, '%')) or :location='') and (lower(uf.culturalOffer.type.name) like lower(concat('%', :type, '%')) or :type=''))")
    public Page<CulturalOffer> filterCulturalOffers(long id, String name, String location, String type, Pageable pageable);

	
}

