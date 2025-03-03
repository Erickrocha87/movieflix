package com.movieflix.infra.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.movieflix.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class TokenService {

    @Value("${movieflix.security.secret}")
    private String secret;

    public String generateToken(User user){
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("userId", user.getId())
                .withClaim("username", user.getUsername())
                .withExpiresAt(Instant.now().plusSeconds(43200))
                .withIssuedAt(Instant.now())
                .withIssuer("API-Movieflix")
                .sign(algorithm);
    }

    public Optional<JWTUserData> verifyToken(String token){

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            DecodedJWT jwt = JWT.require(algorithm)
                    .build()
                    .verify(token);

            return Optional.of(JWTUserData
                            .builder()
                            .id(jwt.getClaim("userId").asLong())
                            .username(jwt.getClaim("username").asString())
                            .email(jwt.getSubject())
                            .build());

        }catch (JWTVerificationException ex){
            return Optional.empty();
        }
    }

}
