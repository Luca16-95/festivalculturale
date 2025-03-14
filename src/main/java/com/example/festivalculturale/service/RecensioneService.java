package com.example.festivalculturale.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.festivalculturale.model.Evento;
import com.example.festivalculturale.model.Recensione;
import com.example.festivalculturale.model.Utente;
import com.example.festivalculturale.repository.RecensioneRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecensioneService {

    private final RecensioneRepository recensioneRepository;

    // aggiunge una recensione ad un evento.
    public void addRecensione(Utente utente, Evento evento, String commento, Long valutazione) {
        Recensione recensione = new Recensione();
        recensione.setUtente(utente);
        recensione.setEvento(evento);
        recensione.setCommento(commento);
        recensione.setValutazione(valutazione);

        recensioneRepository.save(recensione); // Salva la recensione nel database
    }

    // elimina la recensione
    public void deleteRecensione(Utente utente, Evento evento) {
        Recensione recensione = recensioneRepository.findByUtenteAndEvento(utente, evento)
                .orElseThrow(() -> new RuntimeException("Recensione non trovata"));

        recensioneRepository.delete(recensione); // Elimina la recensione
    }

    // aggiorna la recensione
    public void updateRecensione(Utente utente, Evento evento, String commento, Long valutazione) {
        Recensione recensione = recensioneRepository.findByUtenteAndEvento(utente, evento)
                .orElseThrow(() -> new RuntimeException("Recensione non trovata"));

        recensione.setCommento(commento);
        recensione.setValutazione(valutazione);

        recensioneRepository.save(recensione); // Salva la recensione aggiornata
    }

    // visualizza tutte le recensioni.
    public List<Recensione> findByEventoId(Long eventoId) {
        return recensioneRepository.findByEventoId(eventoId);

    }
}
