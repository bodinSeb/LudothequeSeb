package com.eni.ludotheque2.dal;

import com.eni.ludotheque2.bo.Adresse;
import com.eni.ludotheque2.bo.Client;
import com.eni.ludotheque2.bo.Role;
import com.eni.ludotheque2.bo.Utilisateur;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class IUtilisateurRepositoryTest {

    @Autowired
    private IUtilisateurRepository _repoUtil;
    @Transactional
    @Test
    @DisplayName("TEST CT01-FEAT1 Client Repository")
    void AjoutUtilisateur() {

        // Créer deux utilisateurs
        List<Role> roles1 = new ArrayList<>();
        roles1.add(Role.ADMIN);
        roles1.add(Role.EMPLOYE);
        Utilisateur utilisateur1 = new Utilisateur("seb", "seb", roles1);
        List<Role> roles2 = new ArrayList<>();
        roles1.add(Role.EMPLOYE);
        Utilisateur utilisateur2 = new Utilisateur("admin", "admin", roles2);

        _repoUtil.save(utilisateur1);
        _repoUtil.save(utilisateur2);

        // Sauvegarder les utilisateurs dans la base de données
        _repoUtil.save(utilisateur1);
        _repoUtil.save(utilisateur2);
    }
}
