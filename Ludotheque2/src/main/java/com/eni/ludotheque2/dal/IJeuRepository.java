package com.eni.ludotheque2.dal;

import com.eni.ludotheque2.bo.Jeu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IJeuRepository extends JpaRepository<Jeu, Integer> {
}
