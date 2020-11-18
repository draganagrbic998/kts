package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);
	
	@Query("select u from User u where (u.id != :id or :id is null) and u.email=:email")
	public User hasEmail(Long id, String email);
	
}
