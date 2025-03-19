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
@Table(name="Adresses")
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NonNull
    private int num_rue;

    @Column(nullable = false)
    @NonNull
    private String rue;

    @Column(nullable = false, length = 10)
    @NonNull
    private String code_postal;

    @Column(nullable = false, length = 10)
    @NonNull
    private String ville;
}
