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
@Table(name="Clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_client;

    @Column(nullable = false, length = 50)
    @NonNull
    private String nom;

    @Column(nullable = false, length = 50)
    @NonNull
    private String prenom;

    @Column(nullable = false, length = 50, unique = true)
    @NonNull
    private String email;

    @Column(nullable = false, length = 10)
    @NonNull
    private String no_telephone;

    @OneToOne(cascade = CascadeType.ALL
            //,fetch = FetchType.LAZY
            ,orphanRemoval = true
    )
    private Adresse adresse;
}
