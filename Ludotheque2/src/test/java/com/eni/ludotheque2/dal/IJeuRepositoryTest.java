package com.eni.ludotheque2.dal;

import com.eni.ludotheque2.bo.Genre;
import com.eni.ludotheque2.bo.Jeu;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class IJeuRepositoryTest {
    @Autowired
    private IGenreRepository _repoGenre;
    @Autowired
    private IJeuRepository _repoJeu;

    @Transactional
    @Test
    @DisplayName("TEST CT01-FEAT2 Genre Repository")
    void AjoutGenre() {

        //Arrange
        // Créer plusieurs objets Jeu avec des genres associés
        Genre genre1 = _repoGenre.save(new Genre("Aventure"));
        Genre genre2 = _repoGenre.save(new Genre("Stratégie"));
        Genre genre3 = _repoGenre.save(new Genre("Famille"));
        Genre genre4 = _repoGenre.save(new Genre("Action"));

        long nbJeu = _repoJeu.count();

        //Assert
        assertTrue(nbJeu>0);
    }

    @Transactional
    @Test
    @DisplayName("TEST CT02-FEAT1 Client Repository")
    void AjoutJeu() {

        //Arrange
        List<Genre> genres = _repoGenre.findAll();
        int idFirst = genres.stream().findFirst().get().getId_genre();

        List<Genre> genresJeu1 = new ArrayList<>();
        genresJeu1.add(_repoGenre.findById(idFirst+1).get());
        genresJeu1.add(_repoGenre.findById(idFirst+2).get());

        // Créer un Jeu avec des genres associés
        Jeu jeu1 = new Jeu("Stratégie suprême", "REF002", 3.0f);
        jeu1.setAge_min(12);
        jeu1.setDescription("Un jeu de stratégie où vous menez des armées à la guerre.");
        jeu1.setDuree(90);
        jeu1.setGenres(genresJeu1);

        // Sauvegarder les jeux dans la base de données
        _repoJeu.save(jeu1);
        long nbJeu = _repoJeu.count();

        //Assert
        assertTrue(nbJeu>0);
    }

    @Test
    @DisplayName("TEST Recup jeu")
    void RecupJeu() {

        // Sauvegarder les jeux dans la base de données
        Jeu jeu = _repoJeu.findById(1).get();

        //Assert
        assertNotNull(jeu);
    }
}
