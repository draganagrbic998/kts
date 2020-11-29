package com.example.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.CulturalOfferDTO;
import com.example.demo.dto.CulturalOfferUploadDTO;
import com.example.demo.model.CulturalOffer;
import com.example.demo.model.Type;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.TypeRepository;
import com.example.demo.service.UserService;

@Component
public class CulturalOfferMapper {

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TypeRepository typeRepository;
	
	@Transactional(readOnly = true)
	public List<CulturalOfferDTO> map(List<CulturalOffer> culturalOffers){
		return culturalOffers.stream().map(culturalOffer -> {
			CulturalOfferDTO culturalOfferDTO = new CulturalOfferDTO(culturalOffer);
			culturalOfferDTO.setFollowed(this.userService.userIsFollowing(culturalOffer));
			double totalRate = 0.0;
			int counter = 0;
			for (int rate: this.commentRepository.rates(culturalOffer.getId())) {
				totalRate += rate;
				counter += 1;
			}
			totalRate = counter > 0 ? totalRate / counter : totalRate;
			culturalOfferDTO.setTotalRate(totalRate);
			return culturalOfferDTO;
		}).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public CulturalOffer map(CulturalOfferUploadDTO culturalOfferDTO) {
		Type type = this.typeRepository.findByName(culturalOfferDTO.getType());
		CulturalOffer culturalOffer = new CulturalOffer();
		culturalOffer.setId(culturalOfferDTO.getId());
		culturalOffer.setName(culturalOfferDTO.getName());
		culturalOffer.setDescription(culturalOfferDTO.getName());
		culturalOffer.setLocation(culturalOfferDTO.getLocation());
		culturalOffer.setLat(culturalOfferDTO.getLat());
		culturalOffer.setLng(culturalOfferDTO.getLng());
		culturalOffer.setImage(culturalOfferDTO.getImagePath());
		culturalOffer.setType(type);
		return culturalOffer;
	}

}
