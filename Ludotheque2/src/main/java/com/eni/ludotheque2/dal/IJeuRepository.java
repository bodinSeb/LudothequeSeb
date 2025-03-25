package com.eni.ludotheque2.dal;

import com.eni.ludotheque2.bo.Jeu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IJeuRepository extends JpaRepository<Jeu, Integer> {
    List<Jeu> findJeuByTitre(String titre);
    // Recherche des jeux par titre ou par genre en utilisant @Query
//    @Query("SELECT j FROM Jeu j " +
//            "JOIN j.genres g " +
//            "WHERE j.titre = :titre OR g.id_genre = :idgenre")
//    List<Jeu> findJeuByTitreOrAndidGenre(@Param("titre") String titre, @Param("idgenre") int id);
    @Query("SELECT j FROM Jeu j " +
            "JOIN j.genres g " +
            "WHERE (:titre IS NULL OR j.titre = :titre) " +
            "AND (:idgenre IS NULL OR g.id_genre = :idgenre)")
    List<Jeu> findJeuByTitreOrAndIdGenre(@Param("titre") String titre, @Param("idgenre") Integer idgenre);
}
