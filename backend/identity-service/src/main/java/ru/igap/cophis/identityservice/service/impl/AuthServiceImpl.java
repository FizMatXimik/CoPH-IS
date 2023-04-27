package ru.igap.cophis.identityservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.igap.cophis.identityservice.model.UserCredential;
import ru.igap.cophis.identityservice.repository.UserCredentialRepository;
import ru.igap.cophis.identityservice.service.AuthService;
import ru.igap.cophis.identityservice.service.JwtService;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserCredentialRepository userCredentialRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    @Autowired
    public AuthServiceImpl(UserCredentialRepository userCredentialRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userCredentialRepository = userCredentialRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public UserCredential saveUser(UserCredential credential) {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        return userCredentialRepository.save(credential);
    }

    public String generateToken(String userName) {
        Optional<UserCredential> credential_op = userCredentialRepository.findByName(userName);
        if (credential_op.isPresent()) {
            UserCredential credential = credential_op.get();
            return jwtService.generateToken(credential);
        }
        throw new RuntimeException("invalid access");
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}
