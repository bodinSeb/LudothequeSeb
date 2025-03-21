package com.eni.ludotheque2.dal;

import com.eni.ludotheque2.bo.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findClientByNom(String nom);
}
