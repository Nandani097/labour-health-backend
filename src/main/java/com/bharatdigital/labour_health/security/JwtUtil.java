package com.bharatdigital.labour_health.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    // keep secret short for demo; in production keep it in env var and long.
    private final Key key = Keys.hmacShaKeyFor("replace_this_with_a_very_long_secret_for_prod_please".getBytes());
    private final long validity = 1000L * 60 * 60 * 6; // 6 hours

    public String generateToken(String username) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + validity);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String validateAndGetUsername(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return claims.getBody().getSubject();
        } catch (JwtException | IllegalArgumentException ex) {
            return null;
        }
    }
}
