package com.oauth2.ClientDB.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

import com.oauth2.ClientDB.repositories.ClientRepo;

public class JpaClientDetailService implements ClientDetailsService {

	@Autowired
	private ClientRepo clientRepo;

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

		return clientRepo.findClientByClientId(clientId).map(c -> new SecurityClient(c))
				.orElseThrow(() -> new ClientRegistrationException(":("));

	}

}
