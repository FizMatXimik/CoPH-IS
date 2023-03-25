package ru.igap.cophis.identityservice.service;

public interface JwtService {

    void validateToken(final String token);
    String generateToken(String userName);
}
