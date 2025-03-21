package com.eni.ludotheque2.dal;

import com.eni.ludotheque2.bo.Jeu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IJeuRepository extends JpaRepository<Jeu, Integer> {
    List<Jeu> findJeuByTitre(String titre);
}
