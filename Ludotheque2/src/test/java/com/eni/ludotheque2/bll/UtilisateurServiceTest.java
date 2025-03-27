package com.eni.ludotheque2.bll;

import com.eni.ludotheque2.bo.Adresse;
import com.eni.ludotheque2.bo.Client;
import com.eni.ludotheque2.bo.Utilisateur;
import com.eni.ludotheque2.dal.IClientRepository;
import com.eni.ludotheque2.dal.IUtilisateurRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UtilisateurServiceTest {

    @Autowired
    private IUtilisateurService utilisateurService;

    @Autowired
    private IUtilisateurRepository utilisateurRepository;

//    @MockitoBean
//    private IClientRepository clientRepository;


    @Test
    @DisplayName("Retrouver un util par login")
    public void testfindUtilByLogin(){
        //Act
        Utilisateur util = utilisateurService.findByLogin("admin");
        //Assert
        assertNotNull(util);
    }

    @Test
    @DisplayName("Retrouver les utils")
    public void testListUtils(){
        //Act
        List<Utilisateur> utils = utilisateurService.findAllUtilisateurs();
        //Assert
        assertNotNull(utils);
    }


}
