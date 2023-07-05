package com.webproject.QuestionMark.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

	@Value("${questionmarkapp.app.secret}")
	private String APP_SECRET;

	@Value("${questionmarkapp.expires.in}")
	private long EXPIRES_IN;
	
	public String generateJwtToken(Authentication auth) {
		JwtUserDetails userDetails = (JwtUserDetails) auth.getPrincipal();
		Date expireDate = new Date(new Date().getTime() + EXPIRES_IN);
		return Jwts.builder().setSubject(Long.toString(userDetails.getId()))
				.setIssuedAt(new Date()).setExpiration(expireDate)
				.signWith(SignatureAlgorithm.HS512, APP_SECRET).compact();
	}
	
	Long getUserIdFromJwt(String token) {
		Claims claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token)
				.getBody();
		return Long.parseLong(claims.getSubject());
	}
	
	boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
			return !isTokenExpired(token);
		}
		catch(SignatureException e) {
			return false;
		}
		catch(MalformedJwtException e) {
			return false;
		}
		catch(ExpiredJwtException e) {
			return false;
		}
		catch(UnsupportedJwtException e) {
			return false;
		}
		catch(IllegalArgumentException e) {
			return false;
		}
	}

	private boolean isTokenExpired(String token) {
	    try {
	        Claims claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody();
	        Date expiration = claims.getExpiration();
	        Date currentDate = new Date();
	        long tokenExpirationMillis = expiration.getTime();
	        long currentMillis = currentDate.getTime();
	        long tokenValidityPeriod = 10 * 60 * 60 * 1000; // 10 saat -> 10 saat * 60 dakika * 60 saniye * 1000 milisaniye

	        return (currentMillis - tokenExpirationMillis) > tokenValidityPeriod;
	    } catch (JwtException | IllegalArgumentException e) {
	        return false;
	    }
	}
}
