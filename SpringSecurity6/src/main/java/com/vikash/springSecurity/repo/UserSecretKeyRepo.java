package com.vikash.springSecurity.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vikash.springSecurity.model.UserSecretKey;

@Repository
public interface UserSecretKeyRepo extends JpaRepository<UserSecretKey, Integer> {
	
	Optional<UserSecretKey> findByUsername(String username);

}
