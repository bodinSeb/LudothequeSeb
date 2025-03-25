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
import java.util.Optional;

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
        try{
            locService.creationLocation(client, exemplaire);
            return ResponseEntity.status(HttpStatus.OK).body("La location a été créée avec succès");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/location/{id}/retour")
    //@ResponseStatus(code= HttpStatus.OK)
    public ResponseEntity<?> retour(@PathVariable Integer id) {
        Optional<Location> loc = locRepo.findById(id);
        if(loc.isEmpty() ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (loc.get().getDate_retour() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La location a déjà été retournée");
        }
        locService.retourLocation(loc.get());
        return ResponseEntity.status(HttpStatus.OK).body("Le retour a été validé avec succès");
    }
}
