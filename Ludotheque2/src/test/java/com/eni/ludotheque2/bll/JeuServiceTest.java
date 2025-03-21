package com.eni.ludotheque2.bll;


import com.eni.ludotheque2.bo.Genre;
import com.eni.ludotheque2.bo.Jeu;
import com.eni.ludotheque2.dal.IGenreRepository;
import com.eni.ludotheque2.dal.IJeuRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class JeuServiceTest {

    @Autowired
    private IJeuService jeuService;

    @Autowired
    private IJeuRepository jeuRepository;

    @Autowired
    private IGenreRepository genreRepository;

    @Transactional
    @Test
    @DisplayName("Ajout jeu cas positif")
    public void testAjouterJeuCasPositif(){

        // Créer un Jeu avec des genres associés
        List<Genre> genres = genreRepository.findAll();
        System.out.println("genres" + genres);
        Jeu jeu = new Jeu("Test jeu", "REF0010", 4.0f);
        jeu.setAge_min(12);
        jeu.setDescription("description test");
        jeu.setDuree(90);
        jeu.getGenres().add(genres.get(1));
        jeu.getGenres().add(genres.get(0));
        System.out.println("jeu" + jeu);
        jeuService.ajouterJeu(jeu);
        //Assert
        assertThat(genres.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("Retrouver un jeu par son libelle")
    public void testfindClientByNom(){
        //Act
        List<Jeu> jeux = jeuService.findJeuByTitre("Skyjo");
        //Assert
        assertThat(jeux.size()).isEqualTo(1);
    }
}
