package com.example.festivalculturale.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.festivalculturale.model.Evento;
import com.example.festivalculturale.model.Prenotazioni;
import com.example.festivalculturale.model.Utente;
import com.example.festivalculturale.repository.EventoRepository;
import com.example.festivalculturale.repository.PrenotazioneRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrenotazioneService {

    private PrenotazioneRepository prenotazioneRepository;
    private EventoRepository eventoRepository;

    // crea una nuova prenotazione ad un evento
    public void createPrenotazione(Utente utente, Long eventoId, Long numeroBiglietti) {
        // per creare una prenotazione
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new RuntimeException("Evento non trovato"));

        Prenotazioni prenotazione = new Prenotazioni();
        prenotazione.setUtente(utente);
        prenotazione.setEvento(evento);
        prenotazione.setNumeroBiglietti(numeroBiglietti);

        prenotazioneRepository.save(prenotazione);
    }

    // cancella la prenotazione di un utente ad un dato evento
    public void cancelPrenotazione(Long prenotazioneId, Utente utente) {
        Prenotazioni prenotazione = prenotazioneRepository.findById(prenotazioneId)
                .orElseThrow(() -> new RuntimeException("Prenotazione non trovata"));

        if (!prenotazione.getUtente().equals(utente)) {
            throw new RuntimeException("Non puoi annullare la prenotazione di un altro utente");
        }

        prenotazioneRepository.delete(prenotazione);
    }

    // riceve la lista delle prenotazioni effettuate da un utente
    public List<Prenotazioni> getPrenotazioniByUtente(Utente utente) {
        return prenotazioneRepository.findByUtente(utente);
    }

    // prenotazioni degli utenti ad un evento
    public List<Prenotazioni> getPrenotazioniByEvento(Long eventoId) {
        return prenotazioneRepository.findByEventoId(eventoId);
    }
}
