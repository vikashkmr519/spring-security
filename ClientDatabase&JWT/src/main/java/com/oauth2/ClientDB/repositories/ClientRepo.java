package com.oauth2.ClientDB.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oauth2.ClientDB.entity.Client;


public interface ClientRepo extends JpaRepository<Client, Integer> {

	
	Optional<Client> findClientByClientId(String clientId);
}
