package com.eni.ludotheque2.bo;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Jeux")
public class Jeu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_jeu;

    @Column(nullable = false, length = 50)
    @NonNull
    private String titre;

    @Column(nullable = false, length = 13, unique = true)
    @NonNull
    private String reference;

    @Column(nullable = true)
    private int age_min;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private int duree;

    @Column(nullable = false)
    @NonNull
    private float tarif_jour;

    @ManyToMany
    @JoinTable(name = "Jeu_Genre",
            joinColumns = {@JoinColumn(name = "id_jeu")},
            inverseJoinColumns = {@JoinColumn(name = "id_genre")}
    )
    private List<Genre> genres =  new ArrayList<>();

    public  void addGenre(Genre genre) {
        genres.add(genre);
    }
}
