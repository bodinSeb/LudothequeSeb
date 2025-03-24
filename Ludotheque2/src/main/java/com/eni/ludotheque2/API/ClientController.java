package com.eni.ludotheque2.API;

import com.eni.ludotheque2.bll.IClientService;
import com.eni.ludotheque2.bll.dto.FiltreJeu;
import com.eni.ludotheque2.bo.Adresse;
import com.eni.ludotheque2.bo.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @GetMapping("/clients")
    //@ResponseStatus(code= HttpStatus.OK)
    public ResponseEntity<List<Client>> getClients() {
        List<Client> clients = clientService.findAllClient();
        if(clients == null || clients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/client/{id}")
    //@ResponseStatus(code= HttpStatus.OK)
    public ResponseEntity<Client> getClientById(@PathVariable Integer id) {
        Client client = clientService.findClientById(id);
        if(client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(client);
    }

    @PostMapping ("/client")
    //@ResponseStatus(code= HttpStatus.OK)
    public ResponseEntity<?> create(@RequestBody  Client client) {
        System.err.println("CREATE : " + client);
        clientService.ajouterClient(client);
        System.err.println("NOUVEAU CLIENT : " + client);
        if(client.getId_client() == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur lors de la création du client");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Le client a été créé avec succès");
    }

    @PutMapping("/client/{id}")
    //@ResponseStatus(code= HttpStatus.OK)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody  Client client) {
        System.err.println("UPDATE : " + client);
        Client clientBase = clientService.findClientById(id);
        if(clientBase == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Client clientUpdate = MapClientToClient(clientBase, client);
        System.err.println("NOUVEAU CLIENT : " + clientUpdate);
        try {
            clientService.updateClient(clientUpdate);
            return ResponseEntity.status(HttpStatus.OK).body("modification réussie");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur lors de la modification du client");
        }
    }

    @PatchMapping("/client/{id}/adresse")
    //@ResponseStatus(code= HttpStatus.OK)
    public ResponseEntity<?> updateAdresse(@PathVariable Integer id, @RequestBody Adresse adresse) {
        System.err.println("UPDATE AAAAA : " + adresse);
        Client clientBase = clientService.findClientById(id);
        System.err.println("CLIENT AAAAA : " + clientBase);
        if(clientBase == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            clientService.updateAdresseClient(id, adresse);
            return ResponseEntity.status(HttpStatus.OK).body("l'adresse du client " + clientBase.getNom() +
                    " " + clientBase.getPrenom() + " a été modifiée avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur lors de la modification de l'adresse du client : " +
                    clientBase.getNom() + " " + clientBase.getPrenom());
        }
    }

    @DeleteMapping("/client/{id}")
    //@ResponseStatus(code= HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Client clientBase = clientService.findClientById(id);
        if(clientBase == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            clientService.deleteClient(id);
            return ResponseEntity.status(HttpStatus.OK).body("Suppression du client : " + clientBase.getNom());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur lors de la suppression du client");
        }
    }


    private Client MapClientToClient(Client clientBase, Client client) {
        clientBase.setNom(client.getNom());
        clientBase.setPrenom(client.getPrenom());
        clientBase.setEmail(client.getEmail());
        clientBase.setNo_telephone(client.getNo_telephone());
        clientBase.setAdresse(client.getAdresse());
        return clientBase;
    }

}
