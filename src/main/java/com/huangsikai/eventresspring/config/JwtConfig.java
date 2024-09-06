package com.huangsikai.eventresspring.config;


import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;

@ConfigurationProperties(prefix = "config.jwt",ignoreInvalidFields = true )
@Component
@Data
public class JwtConfig {

    private String secret;
    private Long expire;
    private String header;

    public String createToken(String subject) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + expire*1000);
        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }
    public Claims getTokenClaim(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedJwtException e) {
            throw new RuntimeException(e);
        } catch (MalformedJwtException e) {
            throw new RuntimeException(e);
        } catch (SignatureException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
    public String getSubject(String token) {
        return  getTokenClaim(token).getSubject();
    }
}
