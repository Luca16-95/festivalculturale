package com.example.festivalculturale.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.festivalculturale.model.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Long> {

    Utente findByEmail(String email);

}
