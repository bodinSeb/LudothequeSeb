package com.eni.ludotheque2.bll;

import com.eni.ludotheque2.bo.Adresse;
import com.eni.ludotheque2.bo.Client;

import java.util.List;

public interface IClientService {
    void ajouterClient(Client client);
    Client findClientById(int id);
    List<Client> findClientByNom(String nom);
    List<Client> findAllClient();
    void updateClient(Client client);
    void updateAdresseClient(int idClient, Adresse adresse);
    void deleteClient(int idClient);
}
