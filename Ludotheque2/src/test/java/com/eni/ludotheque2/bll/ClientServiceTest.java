package com.eni.ludotheque2.bll;

import com.eni.ludotheque2.bo.Adresse;
import com.eni.ludotheque2.bo.Client;
import com.eni.ludotheque2.dal.IClientRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ClientServiceTest {

    @Autowired
    private IClientService clientService;

    @Autowired
    private IClientRepository clientRepository;

//    @MockitoBean
//    private IClientRepository clientRepository;

    @Transactional
    @Test
    @DisplayName("Ajout client cas positif")
    public void testAjouterClientCasPositif(){
        //Arrange
        Client client = new Client("nom" + 20 , "prenom" + 20, "emailTest" + 20 + "@eni.fr", "0601010101" );
        client.setAdresse(new Adresse(20, "rue de" +20, "79300", "Bressuire"));
        System.err.println("client : " + client);
//        doAnswer(invocation -> {
//            Client client2 = invocation.getArgument(0);  // Récupérer l'argument passé à la méthode save
//            client2.setId_client(1);  // Modifier l'ID du client
//            return client2;  // Retourner l'objet client modifié
//        }).when(clientRepository).save(client);
        //Act
        clientService.ajouterClient(client);
        System.err.println("client : " + client);
        //Assert
        assertThat(client.getId_client()).isNotNull();
    }

    @Test
    @DisplayName("Retrouver un client par son nom")
    public void testfindClientByNom(){
        //Act
        List<Client> clients = clientService.findClientByNom("nom1");
        //Assert
        assertThat(clients.size()).isEqualTo(1);
    }

    @Transactional
    @Test
    @DisplayName("Modifier un client")
    public void testUpdateClient(){
        //Act
        Client client = clientService.findClientById(1);
        client.setPrenom("prenomModif");
        clientRepository.save(client);

        //Assert
        assertEquals("prenomModif", client.getPrenom());
    }

    @Transactional
    @Test
    @DisplayName("Modifier l'adresse d'un client")
    public void testUpdateAdresseClient(){
        //Act
        Client client = clientService.findClientById(1);

        Adresse adresse = new Adresse(100, "rue de" +100, "79300", "TestModifie");
        clientService.updateAdresseClient(1,adresse);

        //Assert
        assertEquals("TestModifie", client.getAdresse().getVille());
    }
}
