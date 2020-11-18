package com.example.demo.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class AuthToken extends AbstractAuthenticationToken{

	private UserDetails user;
	private String token;
	
	public AuthToken(UserDetails user, String token) {
		super(user.getAuthorities());
		this.user = user;
		this.token = token;
	}

	@Override
	public Object getCredentials() {
		return this.token;
	}

	@Override
	public Object getPrincipal() {
		return this.user;
	}
	
	@Override
	public boolean isAuthenticated() {
		return true;
	}

	public UserDetails getUser() {
		return user;
	}

	public void setUser(UserDetails user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
