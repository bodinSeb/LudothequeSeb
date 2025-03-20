package com.eni.ludotheque2.dal;

import com.eni.ludotheque2.bo.Adresse;
import com.eni.ludotheque2.bo.Client;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class IClientRepositoryTest {

    @Autowired
    private IClientRepository _repoClient;
    @Autowired
    private IAdresseRepository _repoAdresse;

    @Transactional
    @Test
    @DisplayName("TEST CT01-FEAT1 Client Repository")
    void AjoutClientEtAdresse() {

        //Arrange
        long nbClient = _repoClient.count();
        Client client = new Client("nom" + nbClient , "prenom" + nbClient, "emailTest" + nbClient + "@eni.fr", "0101010101" );
        client.setAdresse(new Adresse((int)nbClient, "rue de" +nbClient, "79300", "Bressuire"));

        //Act
        _repoClient.save(client);

        //Assert
        assertNotNull(_repoClient);
        assertNotNull(client.getId_client());
    }

    @Test
    @DisplayName("TEST CT02-FEAT1 Client Repository")
    void RecupClientEtAdresse() {

        //Arrange
        Optional<Client> client = _repoClient.findAll().stream().findFirst();
        long nbClient = client.get().getId_client();
        //Act
        System.out.println("client: "+ client);
        Adresse adresse = client.get().getAdresse();

        //Assert
        assertTrue(client.isPresent());
        assertTrue(adresse != null);
    }

    @Transactional
    @Test
    @DisplayName("TEST CT03-FEAT1 Client Repository")
    void DeleteClientEtAdresseEnCascade() {

        //Arrange
        Optional<Client> client = _repoClient.findAll().stream().findFirst();
        int idClient = client.get().getId_client();
        int idAdresse = client.get().getAdresse().getId();
        System.out.println("client: "+ client);
        //Act
        _repoClient.delete(client.get());

        //Assert
        assertTrue(_repoClient.findById(idClient).isEmpty());
        assertTrue(_repoAdresse.findById(idAdresse).isEmpty());
    }

    @Test
    @DisplayName("TEST CT04-FEAT1 Client Repository")
    void ChangementAdresseVerifSuppAncienneAdresse() {

        //Arrange
        Optional<Client> client = _repoClient.findAll().stream().findFirst();
        int idClient = client.get().getId_client();
        int idAncienAdresse = client.get().getAdresse().getId();
        client.get().setAdresse(new Adresse(200, "test", "79300", "TEST"));
        System.out.println("clientCT04: "+ client);

        //Act
        _repoClient.save(client.get());

        //Assert
        assertEquals(200, _repoClient.findById(idClient).get().getAdresse().getNum_rue());
        assertTrue(_repoAdresse.findById(idAncienAdresse).isEmpty());

    }
}
