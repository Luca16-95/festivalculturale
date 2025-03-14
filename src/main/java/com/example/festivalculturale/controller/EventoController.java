package com.example.festivalculturale.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.festivalculturale.model.Evento;
import com.example.festivalculturale.service.EventoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user/eventi")
public class EventoController {

    private final EventoService eventoService;

    @GetMapping("/eventi")
    public String getAllEventi(Model model) {
        List<Evento> eventi = eventoService.getAllEventi();
        model.addAttribute("Eventi", eventi);
        return "/eventi-Lista"; //
    }

    @GetMapping("/{id}/recensioni")
    public String getEventoConRecensioni(@PathVariable Long id, Model model) {
        Evento evento = eventoService.getEventoConRecensioni(id);
        model.addAttribute("evento", evento);
        model.addAttribute("recensioni", evento.getRecensioni());
        return "evento/dettaglio";
    }
}
