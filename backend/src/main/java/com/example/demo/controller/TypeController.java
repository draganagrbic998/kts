package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TypeDTO;
import com.example.demo.mapper.TypeMapper;
import com.example.demo.model.Type;
import com.example.demo.repository.TypeRepository;
import com.example.demo.service.TypeService;

@RestController
@RequestMapping(value = "/api/types", produces = MediaType.APPLICATION_JSON_VALUE)
public class TypeController {

	@Autowired
	private TypeService typesService;
	
	@Autowired
	private TypeRepository typesRepository;
	
	@Autowired
	private TypeMapper typesMapper;
	
	@GetMapping(value = "")
	public ResponseEntity<List<TypeDTO>> getTypes(){
		List<Type> sviTipovi = (List<Type>) typesService.findAll();
		List<TypeDTO> sviTipoviDTO = typesMapper.map(sviTipovi); 
		return new ResponseEntity<List<TypeDTO>>(sviTipoviDTO,HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable long id) {
		this.typesService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
