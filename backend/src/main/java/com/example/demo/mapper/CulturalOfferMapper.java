package com.example.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.CulturalOfferDTO;
import com.example.demo.model.CulturalOffer;
import com.example.demo.repository.CommentRepository;
import com.example.demo.service.UserService;

@Component
public class CulturalOfferMapper {

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private UserService userService;
	
	public List<CulturalOfferDTO> map(List<CulturalOffer> culturalOffers){
		return culturalOffers.stream().map(culturalOffer -> {
			CulturalOfferDTO culturalOfferDTO = new CulturalOfferDTO(culturalOffer);
			culturalOfferDTO.setFollowed(this.userService.userIsFollowing(culturalOffer));
			double totalRate = 0.0;
			int counter = 0;
			for (int rate: this.commentRepository.getRates(culturalOffer.getId())) {
				totalRate += rate;
				counter += 1;
			}
			totalRate = counter > 0 ? totalRate / counter : totalRate;
			culturalOfferDTO.setTotalRate(totalRate);
			return culturalOfferDTO;
		}).collect(Collectors.toList());
	}

	
}
