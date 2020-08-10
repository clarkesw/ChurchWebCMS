/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.util;

import java.security.Key;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

/**
 *
 * @author clarke
 */

@Service
public class JWTUtil {
    private static final String secretKey= "4C8kupp4meOO32M78sKdX83l45LLd32X";

    public static String createToken(String user, long ttlMillis) {
            if (ttlMillis <= 0) {
                    throw new RuntimeException("Expiry time must be greater than Zero :["+ttlMillis+"] ");
            }

            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
            long nowMillis = System.currentTimeMillis();
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

            JwtBuilder builder = Jwts.builder()
                            .setSubject(user)				
                            .signWith(signatureAlgorithm, signingKey);

            builder.setExpiration(new Date(nowMillis + ttlMillis));		
            return builder.compact();
    }

    public static String getUser(String token) {		
            Claims claims = Jwts.parser()         
                           .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                           .parseClaimsJws(token).getBody();

            return claims.getSubject();
    }
    
    public static void checkJWT(String jwt) {
 
        Claims claims = Jwts.parser()         
           .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
           .parseClaimsJws(jwt).getBody();
    }
}
