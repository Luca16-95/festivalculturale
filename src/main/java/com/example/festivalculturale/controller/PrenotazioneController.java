package com.example.festivalculturale.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.festivalculturale.model.Prenotazioni;
import com.example.festivalculturale.model.Utente;
import com.example.festivalculturale.service.PrenotazioneService;
import com.example.festivalculturale.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user/prenotazione")
public class PrenotazioneController {

    private final PrenotazioneService prenotazioneService;
    private final UserService utenteService;

    @GetMapping
    public String listaPrenotazioni(Model model) {
        Utente userDetails = (Utente) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getNome();
        Utente utente = utenteService.findByEmail(email);

        List<Prenotazioni> prenotazioni = prenotazioneService.getPrenotazioniByUtente(utente);
        model.addAttribute("prenotazioni", prenotazioni);
        return "user/prenotazioni"; // Template per visualizzare le prenotazioni
    }

    // Prenotazione di un evento
    @PostMapping("/{id}/prenota")
    public String prenotazioneEvento(@PathVariable Long id, @RequestParam Long numeroBiglietti) {
        // Recupera l'utente autenticato
        Utente userDetails = (Utente) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getNome();
        Utente utente = utenteService.findByEmail(email);

        // Prenota l'evento
        prenotazioneService.createPrenotazione(utente, id, numeroBiglietti);
        return "redirect:/user/prenotazioni";
    }

    // Annulla una prenotazione
    @PostMapping("/{id}/annulla")
    public String annullaPrenotazione(@PathVariable Long id) {
        // Recupera l'utente autenticato
        Utente userDetails = (Utente) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getNome();
        Utente utente = utenteService.findByEmail(email);

        // Annulla la prenotazione
        prenotazioneService.cancelPrenotazione(id, utente);
        return "redirect:/user/prenotazioni";
    }
}
