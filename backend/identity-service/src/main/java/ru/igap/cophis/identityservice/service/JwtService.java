package ru.igap.cophis.identityservice.service;

import ru.igap.cophis.identityservice.model.UserCredential;

public interface JwtService {

    void validateToken(final String token);
    String generateToken(UserCredential credential);
}
