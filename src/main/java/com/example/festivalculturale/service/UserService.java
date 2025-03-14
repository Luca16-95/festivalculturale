package com.example.festivalculturale.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.festivalculturale.model.Utente;
import com.example.festivalculturale.model.enums.Ruolo;
import com.example.festivalculturale.repository.UtenteRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UtenteRepository utenteRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Utente register(Utente utente) {
        if (utente.getRuolo() == null || utente.getRuolo().name() == null) {
            utente.setRuolo(Ruolo.ROLE_USER);
        }
        utente.setPassword(passwordEncoder.encode(utente.getPassword()));
        return utenteRepository.save(utente);
    }

    public Utente findByEmail(String email) {
        return utenteRepository.findByEmail(email);
    }
}
