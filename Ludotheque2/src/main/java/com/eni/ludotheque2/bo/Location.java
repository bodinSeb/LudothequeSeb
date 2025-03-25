package com.eni.ludotheque2.bo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Table(name="Locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_location;

    @Column(nullable = false)
    @NonNull
    private Date date_debut;

    @Column(nullable = true)
    private Date date_retour;

    @Column(nullable = true)
    @NonNull
    private float tarif_jour;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "id_exemplaire", referencedColumnName = "id_exemplaire")
    private Exemplaire exemplaire;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "id_client", referencedColumnName = "id_client")
    private Client client;
}
