package com.cesi.spring.controller;

import com.cesi.spring.Exception.BadRequest;
import com.cesi.spring.Exception.Conflict;
import com.cesi.spring.Exception.NotFound;
import com.cesi.spring.model.Client;
import com.cesi.spring.model.CompteCourant;
import com.cesi.spring.model.CompteEpargne;
import com.cesi.spring.model.Retour;
import com.cesi.spring.model.Solde;
import com.cesi.spring.repository.ClientRepository;
import com.cesi.spring.repository.CompteCourantRepository;
import com.cesi.spring.repository.CompteEpargneRepository;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/1")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private CompteCourantRepository compteCourantRepository;
    
    @Autowired
    private CompteEpargneRepository compteEpargneRepository;
    
    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getClients() {
        return new ResponseEntity(clientRepository.findAll(),HttpStatus.OK);
    }
    
    @GetMapping("/clients/{clientId}")
    public ResponseEntity<Client> getOneClient(@PathVariable int clientId) {
        Client client = new Client();
        try {
            client = clientRepository.findById(clientId).get();
        } catch (NoSuchElementException e) {
            throw new NotFound("Ce client n'existe pas");
        } 
        return new ResponseEntity(client,HttpStatus.OK);
    }
    
    @GetMapping("/clients/{clientId}/comptes/courants")
    public ResponseEntity<List<CompteCourant>> getAllComptesCourants(@PathVariable int clientId) {  
        try {
            Client client = clientRepository.findById(clientId).get();
        } catch (NoSuchElementException e) {
            throw new NotFound("Ce client n'existe pas");
        } 
        return new ResponseEntity(compteCourantRepository.getComptesCourants(clientId),HttpStatus.OK);
    }
    
    @GetMapping("/clients/{clientId}/comptes/epargnes")
    public ResponseEntity<List<CompteEpargne>> getAllComptesEpargnes(@PathVariable int clientId) {
        try {
            Client client = clientRepository.findById(clientId).get();
        } catch (NoSuchElementException e) {
            throw new NotFound("Ce client n'existe pas");
        } 
        return new ResponseEntity(compteEpargneRepository.getComptesEpargnes(clientId),HttpStatus.OK);
    }
    
    @GetMapping("/clients/{clientId}/solde")
    public ResponseEntity<Solde> getSoldeClient(@PathVariable int clientId) {
        Client client = new Client();
        Solde solde = new Solde();
        try {
            client = clientRepository.findById(clientId).get();
            solde.setClient(client);
            try {
                solde.setSolde(compteCourantRepository.getComptesSolde(clientId)+compteEpargneRepository.getComptesSolde(clientId));
            } catch(AopInvocationException e) {
                solde.setSolde(0);
            }
        } catch (NoSuchElementException e) {
            throw new NotFound("Ce client n'existe pas");
        }
        return new ResponseEntity(solde,HttpStatus.OK);
    }
    
    @PutMapping("/clients/{clientId}")
    public ResponseEntity<Retour> updateClient(@PathVariable int clientId, @RequestBody Client clientUpdate) {
        Client client = new Client();
        if(null != clientUpdate) {
            if(!clientUpdate.getIdentifiant().isEmpty() && !clientUpdate.getNom().isEmpty() && !clientUpdate.getPrenom().isEmpty()) {
            } else {
                throw new BadRequest("Merci de spécifier les informations du client");
            }
        } else {
            throw new BadRequest("Merci de spécifier les informations du client");
        } 
        try {
            client = clientRepository.findById(clientId).get();
            client.setIdentifiant(clientUpdate.getIdentifiant());
            client.setNom(clientUpdate.getNom());
            client.setPrenom(clientUpdate.getPrenom());
            clientRepository.save(client);
        } catch (NoSuchElementException e) {
            throw new NotFound("Ce client n'existe pas");
        } 
        return new ResponseEntity(new Retour("Le client a bien été modifié"),HttpStatus.OK);
    }
        
    @PostMapping("/clients")
    public ResponseEntity<Retour> insertClient(@RequestBody Client client) {
        if(null != client) {
            if(!client.getIdentifiant().isEmpty() && !client.getNom().isEmpty() && !client.getPrenom().isEmpty()) {
                clientRepository.save(client);
            } else {
                throw new BadRequest("Merci de spécifier les informations du client");
            }
        } else {
            throw new BadRequest("Merci de spécifier les informations du client");
        } 
        return new ResponseEntity(new Retour("Le client a bien été crée"),HttpStatus.OK);
    }
    
    @DeleteMapping("/clients/{clientId}")
    public ResponseEntity<Retour> deleteClient(@PathVariable int clientId) {
        Client client = new Client();
        try {
            client = clientRepository.findById(clientId).get();
        } catch (NoSuchElementException e) {
            throw new NotFound("Ce client n'existe pas");
        } 
        try {
            clientRepository.delete(client);
        } catch (Exception e) {
            if(e.getCause() != null && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                throw new Conflict("Merci de supprimer les comptes courants et/ou épargnes de ce client avant de le supprimer");
            }
        }
        return new ResponseEntity(new Retour("Le client a bien été supprimé"),HttpStatus.OK);
    } 
}
