package com.eni.ludotheque2.dal;

import com.eni.ludotheque2.bo.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFactureRepository extends JpaRepository<Facture, Integer> {
}
