package com.eni.ludotheque2.bll;


import com.eni.ludotheque2.dto.FiltreJeu;
import com.eni.ludotheque2.bo.Jeu;

import java.util.List;

public interface IJeuService {
    void ajouterJeu(Jeu jeu);
    Jeu findJeuById(int id);
    List<Jeu> findJeuByTitre(String libelle);
    void updateJeu(Jeu jeu);
    List<Jeu> findJeuByTitreOrAndidGenre(FiltreJeu filtre);
}
