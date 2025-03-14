package com.example.festivalculturale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.festivalculturale.model.Prenotazioni;
import com.example.festivalculturale.model.Utente;

public interface PrenotazioneRepository extends JpaRepository<Prenotazioni, Long> {
    List<Prenotazioni> findByEventoId(Long eventoId);

    List<Prenotazioni> findByUtente(Utente utente);
}
