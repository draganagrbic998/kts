package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Type;

public interface TypeRepository extends JpaRepository<Type, Long> {
	public Type findByName(String name);

	@Query("select distinct t.name from Type t where lower(t.name) like lower(concat('%',:filter,'%'))")
	public List<String> filterNames(String filter);
	
}
