package com.eni.ludotheque2.bll;

import com.eni.ludotheque2.bo.Adresse;
import com.eni.ludotheque2.bo.Client;
import com.eni.ludotheque2.dal.IClientRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClientService implements IClientService{

    @NonNull
    private IClientRepository clientRepository;

    @Override
    public void ajouterClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public Client findClientById(int id) {
        return clientRepository.findById(id).get();
    }
    @Override
    public List<Client> findClientByNom(String nom) {
        return clientRepository.findClientByNom(nom);
    }

    @Override
    public void updateClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public void updateAdresseClient(int idClient, Adresse adresse) {
        clientRepository.findById(idClient).get().setAdresse(adresse);
    }


}
