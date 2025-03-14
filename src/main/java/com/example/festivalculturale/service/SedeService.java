package com.example.festivalculturale.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.festivalculturale.model.Sede;
import com.example.festivalculturale.repository.SedeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SedeService {

    private final SedeRepository sedeRepository;

    // questo metodo sarà disponibile solo all'admin.
    // permette l eliminazione di una sede
    public void deleteSede(Long sedeId) {
        // Verifica se la sede esiste
        if (!sedeRepository.existsById(sedeId)) {
            throw new RuntimeException("Sede non trovata!");
        }
        sedeRepository.deleteById(sedeId); // Elimina la sede
    }

    // questo metodo sarà disponibile solo all'admin
    // permette di creare una nuova sede
    public void saveSede(Sede sede) {
        // Verifica se la sede non esiste già, per esempio basandosi sul nome
        if (sedeRepository.existsByNome(sede.getNome())) {
            throw new RuntimeException("La sede con questo nome esiste già!");
        }

        // Salva la sede nel database
        sedeRepository.save(sede);
    }

    // metodo per visualizzare la lista delle sedi
    public List<Sede> getAllSedi() {
        return sedeRepository.findAll();
    }

}
