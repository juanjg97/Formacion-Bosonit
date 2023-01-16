package com.bosonit.block15security.security;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.*;

public class TokenUtils {

    private final static String ACCESS_TOKEN_SECRET = "4qhq8LrEBfYcaRHxhdb9ZURb2f8e7Ud";
    private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_00L;

    public static String createToken(String name, String email){
        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1_000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        Map<String, Object> extra = new HashMap<>();
        extra.put("name",name);

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(SignatureAlgorithm.HS256,ACCESS_TOKEN_SECRET.getBytes())
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try{
            Jws<Claims> jws= Jwts.parser().setSigningKey(ACCESS_TOKEN_SECRET.getBytes()).parseClaimsJws(token);

            String username = jws.getBody().getSubject();
            return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());

        } catch (JwtException e ) {
            throw new RuntimeException(e);
        }
    }
}
