package com.eni.ludotheque2.bll;


import com.eni.ludotheque2.bo.Client;
import com.eni.ludotheque2.bo.Exemplaire;
import com.eni.ludotheque2.bo.Facture;
import com.eni.ludotheque2.bo.Location;
import com.eni.ludotheque2.dal.IClientRepository;
import com.eni.ludotheque2.dal.IExemplaireRepository;
import com.eni.ludotheque2.dal.ILocationRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FactureServiceTest {

    @Autowired
    private IFactureService factureService;

//    @Autowired
//    private ILocationRepository locationRepository;
//
//    @Autowired
//    private IClientRepository clientRepository;
//
//    @Autowired
//    private IExemplaireRepository exemplaireRepository;

    //@Transactional
    @Test
    @DisplayName("Ajout facture cas positif")
    public void testAjouterFactureCasPositif(){

        //Arrange
        List<Integer> idsLoc = new ArrayList<Integer>();
        idsLoc.add(2);

        // Act : Créer une facture pour un retour d'une loc
        Facture facture = factureService.createFacture(idsLoc, 1);

        //Assert
        assertNotNull(facture);
    }

//    //@Transactional
//    @Test
//    @DisplayName("Ajout loc cas positif")
//    public void testRetourLocCasPositif(){
//
//        //Arrange
//        Location loc = locationRepository.findById(3).get();
//
//        // Act : Faire un retour de location
//        locationService.retourLocation(loc);
//        Exemplaire exemp = exemplaireRepository.findById(loc.getExemplaire().getId_exemplaire()).get();
//
//        //Assert
//        assertNotNull(loc.getDate_retour());
//        assertTrue(exemp.isLouable());
//    }
}
