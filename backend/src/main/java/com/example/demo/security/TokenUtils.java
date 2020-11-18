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
	
	@Value("web")
	private String AUDIENCE;
	
	@Value("Authorization")
	private String AUTH_HEADER;
	
	@Value("900000000")
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
		String username = this.getUsernameFromToken(token);
		return username != null && username.equals(user.getUsername());
	}
	
	public String getToken(HttpServletRequest request) {
		return request.getHeader(this.AUTH_HEADER);
	}
	
	public String getUsernameFromToken(String token) {
		String username;
		try {
			Claims claims = this.getClaimsFromToken(token);
			username = claims.getSubject();
		}
		catch(Exception e) {
			username = null;
		}
		return username;
	}
	
	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser()
					.setSigningKey(this.APP_SECRET)
					.parseClaimsJws(token)
					.getBody();
		}
		catch(Exception e) {
			claims = null;
		}
		return claims;
	}

	public long getExpiresIn() {
		return this.EXPIRES_IN;
	}
	
}
