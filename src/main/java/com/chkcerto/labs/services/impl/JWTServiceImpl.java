package com.chkcerto.labs.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.chkcerto.labs.services.JWTService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Map;

@Service
public class JWTServiceImpl implements JWTService {

    private final String secret = "MY-JWT-SECRET";

    public String generateToken(UserDetails userDetails) {
        Algorithm algorithm = Algorithm.HMAC256(secret); // Pode colocar a Secret em Variaveis do Ambiente

        try {
            return JWT.create()
                    .withIssuer("examples-api") // Nome do Sistema
                    .withAudience("AutoSCORE.API")
                    .withSubject(userDetails.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) // expiration in 1 day = 24 hours
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        Algorithm algorithm = Algorithm.HMAC256(secret); // Pode colocar a Secret em Variaveis do Ambiente

        try {
            return JWT.create()
                    .withIssuer("examples-api") // Nome do Sistema
                    .withAudience("AutoSCORE.API")
                    .withSubject(userDetails.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 168)) // expiration in 1 day = 24 hours
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    public String extractUsername(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        try {
            return JWT.require(algorithm)
                    .withIssuer("examples-api")
                    .withAudience("AutoSCORE.API")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return JWT.decode(token).getExpiresAt().before(new Date());
    }

}
