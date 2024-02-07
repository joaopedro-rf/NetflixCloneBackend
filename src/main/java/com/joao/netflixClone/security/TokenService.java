package com.joao.netflixClone.security;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.joao.netflixClone.model.NetflixUser;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    private String secret = "segredinho";

    public TokenService() {
    }

    public String generateToken(NetflixUser netflixUser) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            return JWT.create().withIssuer("auth").withSubject(netflixUser.getEmail()).withExpiresAt(this.getExpirationDate()).sign(algorithm);
        } catch (JWTCreationException var3) {
            throw new RuntimeException("Error generating the token", var3);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            return JWT.require(algorithm).withIssuer("auth").build().verify(token).getSubject();
        } catch (JWTVerificationException var3) {
            return "";
        }
    }

    private Instant getExpirationDate() {
        return LocalDateTime.now().plusHours(2L).toInstant(ZoneOffset.of("-03:00"));
    }
}
