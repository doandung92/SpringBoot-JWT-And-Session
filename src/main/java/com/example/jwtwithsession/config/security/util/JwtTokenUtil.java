package com.example.jwtwithsession.config.security.util;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.function.*;

@Component
public class JwtTokenUtil {

    public static final int MILLISECONDS_IN_A_SECOND = 1000;

    @Value("${jwt.access-token.period-in-seconds}")
    private long accessTokenPeriodInSeconds;

    @Value("${jwt.secret}")
    private String secret;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private  <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        Date now = new Date();
        Date expiredDate = new Date(System.currentTimeMillis() + accessTokenPeriodInSeconds * MILLISECONDS_IN_A_SECOND);
        return Jwts.builder().setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
            return true;
        } catch (Exception ignored) { }
        return false;
    }
}

