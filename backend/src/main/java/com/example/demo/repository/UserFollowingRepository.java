package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.UserFollowing;

public interface UserFollowingRepository extends JpaRepository<UserFollowing, Long> {

}
