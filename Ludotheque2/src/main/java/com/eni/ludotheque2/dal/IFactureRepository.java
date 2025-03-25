package com.eni.ludotheque2.dal;

import com.eni.ludotheque2.bo.Facture;
import com.eni.ludotheque2.bo.Jeu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFactureRepository extends JpaRepository<Facture, Integer> {
    @Query("SELECT f FROM Facture f " +
            "JOIN f.locations l " +
            "WHERE l.client.id_client = :idClient")
    List<Facture> findFactureByIdClient(@Param("idClient") int idClient);
}
