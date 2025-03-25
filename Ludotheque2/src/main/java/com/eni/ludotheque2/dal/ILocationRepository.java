package com.eni.ludotheque2.dal;

import com.eni.ludotheque2.bo.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ILocationRepository extends JpaRepository<Location, Integer> {
    @Query("SELECT l FROM Location l " +
            "WHERE l.client.id_client = :idClient " +
            "AND l.date_retour = null")
    List<Location> findLocationByIdClientAndTerminee(@Param("idClient") Integer idClient);
}
