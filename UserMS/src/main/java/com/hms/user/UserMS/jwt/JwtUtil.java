package com.hms.user.UserMS.jwt;


import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
    private static final Long JWT_TOKEN_VALIDITY = 5*60*60L; 

    public static final String SECRET="deac50fc5b0d49204fa5ed757df86f7322548994248ad9cc93174ae59f92e86f4140b1136fd215b0afbc16a3db8b5ed181be8a0ddfe0878b39b2b7343ef51a62";

    public String generateToken(UserDetails userdetails){
        Map<String, Object> claims=new HashMap<>();
        CustomerUserDetails user=(CustomerUserDetails) userdetails;
        claims.put("id", user.getId());
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole());
        claims.put("name", user.getName());
        claims.put("profileId", user.getProfileId()); 
        return doGenerateToken(claims, user.getUsername());



    }
    public String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }


                
    }


