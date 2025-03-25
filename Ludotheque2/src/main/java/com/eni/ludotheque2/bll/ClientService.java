package com.eni.ludotheque2.bll;

import com.eni.ludotheque2.bo.Adresse;
import com.eni.ludotheque2.bo.Client;
import com.eni.ludotheque2.dal.IAdresseRepository;
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
    @NonNull
    private IAdresseRepository adresseRepository;


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
    public List<Client> findAllClient() {
        return clientRepository.findAll();
    }

    @Override
    public void updateClient(Client client) {
        //TODO vérifier que client a un id
        clientRepository.save(client);
    }

    @Override
    public void updateAdresseClient(int idClient, Adresse adresse) {
        Client clientBase =  clientRepository.findById(idClient).get();
        int idAdresse = clientBase.getAdresse().getId();
        Adresse adresseBase = adresseRepository.findById(idAdresse).get();
        System.err.println("Adresse" + adresseBase );
        adresseBase.setNum_rue(adresse.getNum_rue());
        adresseBase.setRue(adresse.getRue());
        adresseBase.setCode_postal(adresse.getCode_postal());
        adresseBase.setVille(adresse.getVille());
        adresseRepository.save(adresseBase);
    }

    @Override
    public void deleteClient(int idClient) {
        clientRepository.deleteById(idClient);
    }
}
