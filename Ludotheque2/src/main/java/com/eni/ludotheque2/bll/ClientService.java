package com.eni.ludotheque2.bll;

import com.eni.ludotheque2.bo.Client;
import com.eni.ludotheque2.dal.IClientRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClientService implements IClientService{

    @NonNull
    private IClientRepository clientRepository;

    @Override
    public void ajouterClient(Client client) {
        clientRepository.save(client);
    }
}
