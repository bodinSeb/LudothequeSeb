package com.eni.ludotheque2.dal;

import com.eni.ludotheque2.bo.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdresseRepository extends JpaRepository<Adresse, Integer> {
}
