package com.example.festivalculturale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.festivalculturale.model.Evento;
import com.example.festivalculturale.model.Sede;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findBySede(Sede sede);
}
