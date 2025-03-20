package com.eni.ludotheque2.bo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Table(name="Utilisateurs")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_utilisateur;

    @Column(nullable = false, length = 50)
    @NonNull
    private String login;

    @Column(nullable = false, length = 50)
    @NonNull
    private String password;

    @Column(name = "isAdmin", nullable = false)
    @NonNull
    private Boolean isAdmin = false;
}
