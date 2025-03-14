package com.example.festivalculturale.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.festivalculturale.model.Evento;
import com.example.festivalculturale.model.Sede;
import com.example.festivalculturale.service.EventoService;
import com.example.festivalculturale.service.SedeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final EventoService eventoService;
    private final SedeService sedeService;

    // Gestione eventi
    @GetMapping("/eventi")
    public String listaEventi(Model model) {
        List<Evento> eventi = eventoService.getAllEventi();
        model.addAttribute("eventi", eventi);
        return "admin/eventi"; // Template per visualizzare gli eventi
    }

    @GetMapping("/evento/create")
    public String createEventoForm(Model model) {
        model.addAttribute("evento", new Evento());
        return "admin/createEvento"; // Template per creare un nuovo evento
    }

    @PostMapping("/evento/create")
    public String saveEvento(@ModelAttribute Evento evento, @RequestParam Long sedeId) {
        eventoService.saveEvento(evento, sedeId);
        return "redirect:/admin/eventi";
    }

    @GetMapping("/evento/{id}/delete")
    public String deleteEvento(@PathVariable Long id) {
        eventoService.deleteEvento(id);
        return "redirect:/admin/eventi";
    }

    // Gestione sedi
    @GetMapping("/sedi")
    public String listaSedi(Model model) {
        List<Sede> sedi = sedeService.getAllSedi();
        model.addAttribute("sedi", sedi);
        return "admin/sedi"; // Template per visualizzare le sedi
    }

    @GetMapping("/sede/create")
    public String createSedeForm(Model model) {
        model.addAttribute("sede", new Sede());
        return "admin/createSede"; // Template per creare una nuova sede
    }

    @PostMapping("/sede/create")
    public String saveSede(@ModelAttribute Sede sede) {
        sedeService.saveSede(sede);
        return "redirect:/admin/sedi";
    }

    @GetMapping("/sede/{id}/delete")
    public String deleteSede(@PathVariable Long id) {
        sedeService.deleteSede(id);
        return "redirect:/admin/sedi";
    }
}