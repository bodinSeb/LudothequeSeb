package com.eni.ludotheque2.bll;


import com.eni.ludotheque2.bo.*;
import com.eni.ludotheque2.dal.IClientRepository;
import com.eni.ludotheque2.dal.IExemplaireRepository;
import com.eni.ludotheque2.dal.ILocationRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LocationServiceTest {

    @Autowired
    private ILocationService locationService;

    @Autowired
    private ILocationRepository locationRepository;

    @Autowired
    private IClientRepository clientRepository;

    @Autowired
    private IExemplaireRepository exemplaireRepository;

    //@Transactional
    @Test
    @DisplayName("Ajout loc cas positif")
    public void testAjouterLocCasPositif(){

        //Arrange
        long nbLoc = locationRepository.count();
        Client client = clientRepository.findById(3).get();
        Exemplaire exemplaire = exemplaireRepository.findById(5).get();
        //System.err.println("CLI : " +client);
        //System.err.println("EX :" +exemplaire);


        // Act : Créer une location de l'exemp 5 pour le client 3
        locationService.creationLocation(client, exemplaire);
        long nbLoc2 = locationRepository.count();

        //Assert
        assertEquals(nbLoc +1, nbLoc2);
    }

//    @Test
//    @DisplayName("Retrouver un jeu par son libelle")
//    public void testfindClientByNom(){
//        //Act
//        List<Jeu> jeux = jeuService.findJeuByTitre("Skyjo");
//        //Assert
//        assertThat(jeux.size()).isEqualTo(1);
//    }
//
//    @Transactional
//    @Test
//    @DisplayName("Filtrer jeu avec titre et genre")
//    public void testFiltrerJeuAvecTitreAndGenre(){
//
//        //Arrange
//        FiltreJeu filtreJeu = new FiltreJeu();
//        filtreJeu.setTitre("Skyjo");
//        filtreJeu.setIdGenre(2);
//
//        //Recherche jeu avec filtre
//        List<Jeu> jeuxFiltres = jeuService.findJeuByTitreOrAndidGenre(filtreJeu);
//        System.out.println(jeuxFiltres);
//        //Assert
//        assertThat(jeuxFiltres.size()).isEqualTo(1);
//    }

//    @Transactional
//    @Test
//    @DisplayName("Filtrer jeu avec titre et genre")
//    public void testFiltrerJeuAvecTitre(){
//        //Arrange
//        FiltreJeu filtreJeu = new FiltreJeu();
//        filtreJeu.setTitre("Skyjo");
//        System.err.println("RRRR" + filtreJeu);
//
//        //Recherche jeu avec filtre
//        List<Jeu> jeuxFiltres = jeuService.findJeuByTitreOrAndidGenre(filtreJeu);
//        System.err.println(jeuxFiltres);
//        //Assert
//        assertThat(jeuxFiltres.size()).isEqualTo(1);
//    }
}
