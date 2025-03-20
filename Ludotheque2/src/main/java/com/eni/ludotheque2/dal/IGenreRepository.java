package com.eni.ludotheque2.dal;

import com.eni.ludotheque2.bo.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGenreRepository extends JpaRepository<Genre, Integer> {
}
