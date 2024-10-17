package com.restful.snackapi.security;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.security.Key;
import java.util.Date;

public class JWTUtil {
    private static final Key SECRET_KEY = JwtKeyGenerator.generateKey();
    private static final Algorithm ALGORITHM = Algorithm.HMAC512(SECRET_KEY.getEncoded());
    private static final long EXPIRATION_TIME = 86400000;

    public static String generateToken(String email){
        return JWT.create()
                .withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(ALGORITHM);
    }

    public static String validateToken(String token){
        JWTVerifier verifier = JWT.require(ALGORITHM).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }

}
