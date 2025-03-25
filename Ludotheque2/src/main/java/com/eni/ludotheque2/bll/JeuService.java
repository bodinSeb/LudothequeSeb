package com.eni.ludotheque2.bll;

import com.eni.ludotheque2.dto.FiltreJeu;
import com.eni.ludotheque2.bo.Jeu;
import com.eni.ludotheque2.dal.IJeuRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class JeuService implements IJeuService{

    @NonNull
    private IJeuRepository jeuRepository;


    @Override
    public void ajouterJeu(Jeu jeu) {
        jeuRepository.save(jeu);
    }

    @Override
    public Jeu findJeuById(int id) {
        return jeuRepository.findById(id).get();
    }

    @Override
    public List<Jeu> findJeuByTitre(String libelle) {
        return jeuRepository.findJeuByTitre(libelle) ;
    }

    @Override
    public void updateJeu(Jeu jeu) {
        jeuRepository.save(jeu);
    }

    @Override
    public List<Jeu> findJeuByTitreOrAndidGenre(FiltreJeu filtre) {
        List<Jeu> jeux = jeuRepository.findJeuByTitreOrAndIdGenre(filtre.getTitre(), filtre.getIdGenre());
        return jeux;
    }
}
