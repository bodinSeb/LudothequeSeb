package com.eni.ludotheque2.API;

import com.eni.ludotheque2.bll.IUtilisateurService;
import com.eni.ludotheque2.bo.Client;
import com.eni.ludotheque2.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private IUtilisateurService utilisateurService;

    @GetMapping
    public ResponseEntity<?> liste() {
        List<Utilisateur> utilisateurs = utilisateurService.findAllUtilisateurs();
        Utilisateur utilisateur = utilisateurService.findByLogin("admin");
        if(utilisateurs == null || utilisateurs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(utilisateurs);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Utilisateur utilisateur) {
        utilisateurService.ajouterUtilisateur(utilisateur);
        if(utilisateur.getId_utilisateur() == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur lors de la création de l'utilisateur");
        }
        return ResponseEntity.status(HttpStatus.OK).body("L'utilisateur a été créé avec succès");
    }
}
