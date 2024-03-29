package ru.igap.cophis.identityservice.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import ru.igap.cophis.identityservice.model.UserCredential;
import ru.igap.cophis.identityservice.service.JwtService;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {

    public static final String SECRET = "78214125442A472D4B6150645367566B59703373357638792F423F4528482B4D";

    public void validateToken(final String token) {
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }

    public String generateToken(UserCredential credential) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", credential.getRoles());
        return createToken(claims, credential.getName());
    }

    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBites = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBites);
    }
}
