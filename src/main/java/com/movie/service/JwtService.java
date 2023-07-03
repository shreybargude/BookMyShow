package com.movie.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private static final String SECRET = "38792F423F4528482B4D6251655468566D597133743677397A24432646294A40";
	
	public String extractUsername(String token) {
		System.out.println("extractUsername");
		return extractClaim(token, Claims::getSubject);
	}
	
	public Date extractExpiration(String token) {
		System.out.println("extractExpiration");
		return extractClaim(token, Claims::getExpiration);
	}
	
	private <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
		System.out.println("extractClaim");
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		System.out.println("extractAllClaims");
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	private boolean isTokenExpired(String token) {
		System.out.println("isTokenExpired");
		return extractExpiration(token).before(new Date());
	}
	
	public boolean validateToken(String token, UserDetails userDetails) {
		System.out.println("validateToken");
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	public String generateToken(String email) {
		System.out.println("generateToken");
		Map<String, Object> claims = new HashMap<>();
		
		return createToken(claims, email);
	}
	
	private String createToken(Map<String, Object> claims, String email) {
		System.out.println("createToken");
		return Jwts
				.builder()
				.setClaims(claims)
				.setSubject(email)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}
 
	private Key getSignKey() {
		System.out.println("getSignKey");
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}