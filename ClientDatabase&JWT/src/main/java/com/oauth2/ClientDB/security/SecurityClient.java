package com.oauth2.ClientDB.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import com.oauth2.ClientDB.entity.Client;

public class SecurityClient  implements ClientDetails{
	
	private final Client client;
	
	public SecurityClient(Client client) {
		this.client= client;
	}

	@Override
	public String getClientId() {
		
		return this.client.getClientId();
	}

	@Override
	public Set<String> getResourceIds() {
		
		return null;
	}

	@Override
	public boolean isSecretRequired() {
		
		return true;
	}

	@Override
	public String getClientSecret() {
		
		return this.client.getSecret();
	}

	@Override
	public boolean isScoped() {
		return true;
	}

	@Override
	public Set<String> getScope() {
		return Set.of(this.client.getScope());
	}

	@Override
	public Set<String> getAuthorizedGrantTypes() {
		Set<String> grantType = new HashSet<>();
		grantType.add(this.client.getGrantType());

		return grantType;
	}

	@Override
	public Set<String> getRegisteredRedirectUri() {
	
		return null;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		
		return List.of(() -> this.client.getScope());
	}

	@Override
	public Integer getAccessTokenValiditySeconds() {
		return null;
	}

	@Override
	public Integer getRefreshTokenValiditySeconds() {
		return null;
	}

	@Override
	public boolean isAutoApprove(String scope) {
		return false;
	}

	@Override
	public Map<String, Object> getAdditionalInformation() {
		return null;
	}

}
