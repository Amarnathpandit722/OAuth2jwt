package com.example.jwtoauth2.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jwtoauth2.entites.UserInfo;

public interface UserRepo  extends JpaRepository<UserInfo, Long>{
	Optional<UserInfo> findByEmailId(String emailId);

}
