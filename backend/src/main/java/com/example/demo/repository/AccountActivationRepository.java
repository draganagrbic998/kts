package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.AccountActivation;

public interface AccountActivationRepository extends JpaRepository<AccountActivation, Long> {

}
