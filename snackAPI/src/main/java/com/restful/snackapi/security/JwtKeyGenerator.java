package com.restful.snackapi.security;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

public class JwtKeyGenerator {
    public static Key generateKey(){
        return Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

}
