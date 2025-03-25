package com.eni.ludotheque2.bo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Table(name="Exemplaires")
public class Exemplaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_exemplaire;

    @Column(nullable = false, length = 20)
    @NonNull
    private String code_barre;

    @Column(nullable = false)
    @NonNull
    private boolean louable;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "id_jeu", referencedColumnName = "id_jeu")
    private Jeu jeu;


}
