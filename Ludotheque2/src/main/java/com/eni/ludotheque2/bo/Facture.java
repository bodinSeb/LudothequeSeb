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
@Table(name="Factures")
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_facture;

    @Column(nullable = false)
    @NonNull
    private Date date_paiement;

    @Column(nullable = false)
    @NonNull
    private Float montantFacture;

    @OneToMany
    private List<Location> locations;
}
