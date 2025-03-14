package com.example.festivalculturale.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.festivalculturale.model.Sede;

public interface SedeRepository extends JpaRepository<Sede, Long> {

    boolean existsByNome(String nome);
}
