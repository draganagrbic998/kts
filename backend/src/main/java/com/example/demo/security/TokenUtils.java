package com.example.demo.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtils {

	@Value("KTS-NVT-APP")
	private String APP_NAME;
	
	@Value("KTS-NVT-SECRET")
	private String APP_SECRET;
	
	@Value("Authorization")
	private String AUTH_HEADER;

	@Value("web")
	private String AUDIENCE;
		
	@Value("1000000000")
	private long EXPIRES_IN;
	
	private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
	
	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuer(this.APP_NAME)
				.setAudience(this.AUDIENCE)
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + this.EXPIRES_IN))
				.signWith(this.SIGNATURE_ALGORITHM, this.APP_SECRET).compact();
	}
	
	public boolean validateToken(String token, UserDetails user) {
		String username = this.getUsername(token);
		return username != null && username.equals(user.getUsername());
	}
	
	public String getToken(HttpServletRequest request) {
		return request.getHeader(this.AUTH_HEADER);
	}
	
	public String getUsername(String token) {
		try {
			return this.getClaims(token).getSubject();
		}
		catch(Exception e) {
			return null;
		}
	}
	
	private Claims getClaims(String token) {
		try {
			return Jwts.parser()
					.setSigningKey(this.APP_SECRET)
					.parseClaimsJws(token)
					.getBody();
		}
		catch(Exception e) {
			return null;
		}
	}

	public long getExpiresIn() {
		return this.EXPIRES_IN;
	}
	
}
