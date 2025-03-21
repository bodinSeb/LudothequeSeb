package com.eni.ludotheque2.dal;

import com.eni.ludotheque2.bo.Exemplaire;
import com.eni.ludotheque2.bo.Genre;
import com.eni.ludotheque2.bo.Jeu;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class IExemplaireRepositoryTest {

    @Autowired
    private IJeuRepository _repoJeu;
    @Autowired
    private IExemplaireRepository _repoEx;

    @Transactional
    @Test
    @DisplayName("TEST CT02-FEAT1 Client Repository")
    void AjoutExemplaire() {

        //Arrange
        Jeu jeu1 = _repoJeu.findById(2).get();

        // Sauvegarder les jeux dans la base de données
        // Créer les exemplaires
        Exemplaire exemplaire1 = new Exemplaire("CODE12355", true);
        Exemplaire exemplaire2 = new Exemplaire("CODE67895", true);
        //exemplaire1.(jeu1);
        //.setJeux(jeu1);

        // Sauvegarder les exemplaires dans la base de données
        _repoEx.save(exemplaire1);
        _repoEx.save(exemplaire2);
    }
}
