package com.eni.ludotheque2.dal;

import com.eni.ludotheque2.bo.Exemplaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExemplaireRepository extends JpaRepository<Exemplaire, Integer> {
}
