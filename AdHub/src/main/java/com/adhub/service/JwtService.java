package com.adhub.service;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

	public String createToken() {
		
		List<String> roles = new ArrayList<String>();
		roles.add("ADMIN");

		Long tokenExpiryTime = (long) (60000 * 60);

		String secretKey = "4a43a6b74b094a8ff9f984c71435d7479e335ecd26bc575d3eea244fc9756df8";
		byte[] keyByte = Decoders.BASE64.decode(secretKey);
		Key key = Keys.hmacShaKeyFor(keyByte);

		String token = Jwts.builder().claim("Id", "389666").claim("roles", roles).subject("saad_pucit24@yahoo.com")
				.issuedAt(new Date()).signWith(key).expiration(new Date(System.currentTimeMillis() + tokenExpiryTime))
				.compact();

		return token;

	}

	public boolean validateToken(String token) {
		try {

			Claims claims = getClaims(token);

			System.out.println(claims.getSubject());

			return claims.getExpiration().after(new Date());

		} catch (JwtException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public Claims getClaims(String token) {
		String secretKey = "4a43a6b74b094a8ff9f984c71435d7479e335ecd26bc575d3eea244fc9756df8";
		byte[] keyByte = Decoders.BASE64.decode(secretKey);

		Claims claims = Jwts.parser().verifyWith(Keys.hmacShaKeyFor(keyByte)).build().parseSignedClaims(token)
				.getPayload();
		return claims;
	}
}
