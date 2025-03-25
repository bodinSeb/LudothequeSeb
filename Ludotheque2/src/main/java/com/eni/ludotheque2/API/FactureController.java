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
//    @Autowired
//    private ILocationRepository locRepo;
//    @Autowired
//    private IClientRepository clientRepository;
//    @Autowired
//    private IExemplaireRepository exempRepository;

    @PostMapping("/facture")
    //@ResponseStatus(code= HttpStatus.OK)
    public ResponseEntity<?> createFacture(@RequestBody CreateFacture cf) {
        factService.createFacture(cf.getIdsLoc(), cf.getIdClient());

        return ResponseEntity.status(HttpStatus.OK).body("La facture a été créée avec succès");
    }

//    @PatchMapping("/location/{id}/retour")
//    //@ResponseStatus(code= HttpStatus.OK)
//    public ResponseEntity<?> retour(@PathVariable Integer id) {
//        Location loc = locRepo.findById(id).get();
//        if(loc == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } else {
//            locService.retourLocation(loc);
//            return ResponseEntity.status(HttpStatus.OK).body("Le retour a été validé avec succès");
//        }
//    }
}
