package com.example.festivalculturale.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.festivalculturale.model.Evento;
import com.example.festivalculturale.model.Recensione;
import com.example.festivalculturale.model.Utente;

public interface RecensioneRepository extends JpaRepository<Recensione, Long> {
    List<Recensione> findByEventoId(Long eventoId);

    Optional<Recensione> findByUtenteAndEvento(Utente utente, Evento evento);
}
