package com.eni.ludotheque2.bll;

import com.eni.ludotheque2.bo.Adresse;
import com.eni.ludotheque2.bo.Client;
import com.eni.ludotheque2.dal.IClientRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ClientServiceTest {

    @Autowired
    private IClientService clientService;

//    @MockitoBean
//    private IClientRepository clientRepository;

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
}
