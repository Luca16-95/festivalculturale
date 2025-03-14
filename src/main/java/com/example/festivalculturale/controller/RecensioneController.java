package com.example.festivalculturale.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.festivalculturale.model.Evento;
import com.example.festivalculturale.model.Utente;
import com.example.festivalculturale.service.EventoService;
import com.example.festivalculturale.service.RecensioneService;
import com.example.festivalculturale.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user/recensioni")
public class RecensioneController {

    private final UserService utenteService;
    private final EventoService eventoService;
    private final RecensioneService recensioneService;

    @PostMapping("/evento/{eventoId}")
    public String aggiungiRecensione(@PathVariable Long eventoId, @RequestParam String commento,
            @RequestParam Long valutazione) {
        // Recupera l'utente autenticato
        Utente userDetails = (Utente) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getNome();
        Utente utente = utenteService.findByEmail(email);

        Evento evento = eventoService.getEventoById(eventoId);
        recensioneService.addRecensione(utente, evento, commento, valutazione);

        return "redirect:/user/eventi/" + eventoId; // Torna ai dettagli dell'evento
    }

    // Modifica una recensione esistente
    @PostMapping("/evento/{eventoId}/update")
    public String aggiornaRecensione(@PathVariable Long eventoId, @RequestParam String commento,
            @RequestParam Long valutazione) {
        // Recupera l'utente autenticato
        Utente userDetails = (Utente) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getNome();
        Utente utente = utenteService.findByEmail(email);

        Evento evento = eventoService.getEventoById(eventoId);
        recensioneService.updateRecensione(utente, evento, commento, valutazione);

        return "redirect:/user/eventi/" + eventoId; // Torna ai dettagli dell'evento
    }

    // Elimina una recensione
    @PostMapping("/evento/{eventoId}/delete")
    public String eliminaRecensione(@PathVariable Long eventoId) {
        // Recupera l'utente autenticato
        Utente userDetails = (Utente) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getNome();
        Utente utente = utenteService.findByEmail(email);

        Evento evento = eventoService.getEventoById(eventoId);
        recensioneService.deleteRecensione(utente, evento);

        return "redirect:/user/eventi/" + eventoId; // Torna ai dettagli dell'evento
    }
}
