package com.eni.ludotheque2.API;

import com.eni.ludotheque2.bll.IClientService;
import com.eni.ludotheque2.bll.ILocationService;
import com.eni.ludotheque2.bll.LocationService;
import com.eni.ludotheque2.bo.Adresse;
import com.eni.ludotheque2.bo.Client;
import com.eni.ludotheque2.bo.Exemplaire;
import com.eni.ludotheque2.bo.Location;
import com.eni.ludotheque2.dal.IClientRepository;
import com.eni.ludotheque2.dal.IExemplaireRepository;
import com.eni.ludotheque2.dal.ILocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LocationController {

    @Autowired
    private ILocationService locService;
    @Autowired
    private ILocationRepository locRepo;
    @Autowired
    private IClientRepository clientRepository;
    @Autowired
    private IExemplaireRepository exempRepository;

    @PostMapping("/location")
    //@ResponseStatus(code= HttpStatus.OK)
    public ResponseEntity<?> createlocation(@RequestParam int idClient, @RequestParam int idExemplaire) {
        Client client = clientRepository.findById(idClient).get();
        Exemplaire exemplaire = exempRepository.findById(idExemplaire).get();
        locService.creationLocation(client, exemplaire);
        return ResponseEntity.status(HttpStatus.OK).body("La location a été créée avec succès");
    }

    @PatchMapping("/location/{id}/retour")
    //@ResponseStatus(code= HttpStatus.OK)
    public ResponseEntity<?> retour(@PathVariable Integer id) {
        Location loc = locRepo.findById(id).get();
        if(loc == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            locService.retourLocation(loc);
            return ResponseEntity.status(HttpStatus.OK).body("Le retour a été validé avec succès");
        }
    }
}
