package com.eni.ludotheque2.API;

import com.eni.ludotheque2.bll.IFactureService;
import com.eni.ludotheque2.bll.ILocationService;
import com.eni.ludotheque2.bo.Client;
import com.eni.ludotheque2.bo.Exemplaire;
import com.eni.ludotheque2.bo.Facture;
import com.eni.ludotheque2.bo.Location;
import com.eni.ludotheque2.dal.IClientRepository;
import com.eni.ludotheque2.dal.IExemplaireRepository;
import com.eni.ludotheque2.dal.ILocationRepository;
import com.eni.ludotheque2.dto.CreateFacture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FactureController {

    @Autowired
    private IFactureService factService;


    @GetMapping("factures/{idClient}")
    public ResponseEntity<List<Facture>> findFactureByIdClient(@PathVariable int idClient) {
        return ResponseEntity.ok(factService.findFactureByIdClient(idClient));
    }

    @PostMapping("/facture")
    public ResponseEntity<?> createFacture(@RequestBody CreateFacture cf) {
        try {
            Facture facture = factService.createFacture(cf.getIdsLoc(), cf.getIdClient());
            return ResponseEntity.status(HttpStatus.OK).body("La facture a été créée avec succès");
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
