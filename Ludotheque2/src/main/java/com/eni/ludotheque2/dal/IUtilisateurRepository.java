package com.eni.ludotheque2.dal;

import com.eni.ludotheque2.bo.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUtilisateurRepository extends JpaRepository<Utilisateur, Long> {
}
