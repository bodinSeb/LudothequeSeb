package com.eni.ludotheque2.bo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @Column(nullable = false, length = 100)
    @NonNull
    private String password;

    @ElementCollection
    @CollectionTable(name = "utilisateur_roles", joinColumns = @JoinColumn(name = "utilisateur_id"))
    @Column(name = "role", nullable = false)
    private List<Role> roles = new ArrayList<>();
}
