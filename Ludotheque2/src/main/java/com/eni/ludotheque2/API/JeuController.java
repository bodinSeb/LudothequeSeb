package com.eni.ludotheque2.API;

import com.eni.ludotheque2.bll.IClientService;
import com.eni.ludotheque2.bll.IJeuService;
import com.eni.ludotheque2.bo.Adresse;
import com.eni.ludotheque2.bo.Client;
import com.eni.ludotheque2.bo.Jeu;
import com.eni.ludotheque2.dto.FiltreJeu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jeux")
public class JeuController {

    @Autowired
    private IJeuService jeuService;

    @GetMapping
    public ResponseEntity<List<Jeu>> liste(@RequestBody FiltreJeu filtreJeu) {
        List<Jeu> jeux = jeuService.findJeuByTitreOrAndidGenre(filtreJeu);
        if(jeux == null || jeux.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(jeux);
    }
}
