package com.example.festivalculturale.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.festivalculturale.model.Sede;
import com.example.festivalculturale.service.SedeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/sedi")
@RequiredArgsConstructor
public class SedeController {
    private final SedeService sedeService;

    @GetMapping("/sedi-List")
    public String getAllSedi(Model model) {
        List<Sede> sedi = sedeService.getAllSedi();
        model.addAttribute("sedi", sedi);
        return "/sedi";
    }
}
