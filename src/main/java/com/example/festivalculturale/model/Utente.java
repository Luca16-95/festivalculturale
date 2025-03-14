package com.example.festivalculturale.model;

import java.util.List;

import com.example.festivalculturale.model.enums.Ruolo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    // CAMPO PER IL RUOLO
    private Ruolo ruolo; // enum per ADMIN, USER

    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Prenotazioni> prenotazioni;
}
