package ru.igap.cophis.identityservice.service;

import ru.igap.cophis.identityservice.model.UserCredential;

public interface AuthService {

    UserCredential saveUser(UserCredential credential);

    public String generateToken(String userName);

    public void validateToken(String token);
}
