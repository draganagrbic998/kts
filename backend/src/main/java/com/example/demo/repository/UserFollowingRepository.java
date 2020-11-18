package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.CulturalOffer;
import com.example.demo.model.UserFollowing;

public interface UserFollowingRepository extends JpaRepository<UserFollowing, Long> {

	@Query("select uf.culturalOffer from UserFollowing uf where uf.user.id=:id")
	public List<CulturalOffer> findCulturalOfferByUserId(long id);
	
}

