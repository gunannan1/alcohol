package com.app.alcohol.utils;


import com.app.alcohol.config.JwtProperties;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * The tool class for generate jwt and read username from jwt
 */
@Component
public class JwtTokenUtil {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * get username from token
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token).getSubject();
    }

    /**
     * get jwt create time
     */
    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token).getIssuedAt();
    }

    /**
     * get jwt expire time
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token).getExpiration();
    }

    /**
     * get audience
     */
    public String getAudienceFromToken(String token) {
        return getClaimFromToken(token).getAudience();
    }

    /**
     * get private claim
     */
    public String getPrivateClaimFromToken(String token, String key) {
        return getClaimFromToken(token).get(key).toString();
    }

    /**
     * get md5 key
     */
    public String getMd5KeyFromToken(String token) {
        return getPrivateClaimFromToken(token, jwtProperties.getMd5Key());
    }

    /**
     *  get playload part
     */
    public Claims getClaimFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * parse token
     */
    public void parseToken(String token) throws JwtException {
        Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(token).getBody();
    }

    /**
     * check if token is expired,true if expired
     */
    public Boolean isTokenExpired(String token) {
        try {
            final Date expiration = getExpirationDateFromToken(token);
            return expiration.before(new Date());
        } catch (ExpiredJwtException expiredJwtException) {
            return true;
        }
    }

    /**
     * generate token,default expire time
     */
    public String generateToken(String userName, String randomKey) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(jwtProperties.getMd5Key(), randomKey);
        return doGenerateToken(claims, userName,jwtProperties.getExpiration() * 1000);
    }

    /**
     * generate token,customize expire time
     */
    public String generateToken(String userName, String randomKey,long expiration) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(jwtProperties.getMd5Key(), randomKey);
        return doGenerateToken(claims, userName,expiration);
    }

    /**
     * generate token
     */
    private String doGenerateToken(Map<String, Object> claims, String subject,long expiration ) {
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expiration);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
    }

    /**
     * get random key
     */
    public String getRandomKey() {
        return getRandomString(6);
    }


    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
    
}