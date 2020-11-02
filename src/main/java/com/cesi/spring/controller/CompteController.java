/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesi.spring.controller;

import com.cesi.spring.exception.BadRequest;
import com.cesi.spring.exception.Conflict;
import com.cesi.spring.exception.NotFound;
import com.cesi.spring.model.Client;
import com.cesi.spring.model.CompteCourant;
import com.cesi.spring.model.CompteEpargne;
import com.cesi.spring.model.Retour;
import com.cesi.spring.repository.CompteCourantRepository;
import com.cesi.spring.repository.CompteEpargneRepository;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/1")
public class CompteController {
    @Autowired
    private CompteCourantRepository compteCourantRepository;
    private CompteEpargneRepository compteEpargneRepository;

    @PutMapping("/comptes/courant/{compteId}/update")
    public ResponseEntity<String> updateCompteCourant(@PathVariable int compteId, @RequestBody CompteCourant compteUpdate) {
        CompteCourant compte = new CompteCourant();

        if (null != compteUpdate){
            if(!compteUpdate.getNumero().isEmpty()) {
            }else {
                throw new BadRequest("Merci de spécifier les informations du compte.");
            }
        } else{
            throw new BadRequest("Merci de spécifier les informations du compte.");
        }
        try {
            compte = compteCourantRepository.findById(compteId).get();
            compte.setNumero(compteUpdate.getNumero());
            compte.setSolde(compteUpdate.getSolde());
            compte.setClient(compteUpdate.getClient());
        } catch (NoSuchElementException e){
            throw new NotFound("Ce compte n'existe pas.");
        }
        return new ResponseEntity("Le compte a bien été modifié",HttpStatus.OK);
    }

    @DeleteMapping("/comptes/courant/{idCompte}/delete")
    public ResponseEntity<String> deleteCompteCourant(@PathVariable int idCompte) {
        CompteCourant compteCourant = new CompteCourant();
        try {
            compteCourant = compteCourantRepository.findById(idCompte).get();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Ce compte courant n'existe pas");
        }
        try {
            compteCourantRepository.delete(compteCourant);
        }
        catch (Exception e){
            throw new BadRequest("Ce compte épargne ne peut pas être supprimer");
        }
        return new ResponseEntity("Le compte épargne " + compteCourant.getNumero() + "a bien été supprimé.", HttpStatus.OK);
    }

    @PutMapping("/comptes/epargne/{compteId}/update")
    public ResponseEntity<String> updateCompteEpargne(@PathVariable int compteId, @RequestBody CompteEpargne compteUpdate) {
        CompteEpargne compte = new CompteEpargne();

        if (null != compteUpdate){
            if(!compteUpdate.getNumero().isEmpty()) {
            }else {
                throw new BadRequest("Merci de spécifier les informations du compte.");
            }
        } else{
            throw new BadRequest("Merci de spécifier les informations du compte.");
        }
        try {
            compte = compteEpargneRepository.findById(compteId).get();
            compte.setNumero(compteUpdate.getNumero());
            compte.setSolde(compteUpdate.getSolde());
            compte.setClient(compteUpdate.getClient());
        } catch (NoSuchElementException e){
            throw new NotFound("Ce compte n'existe pas.");
        }
        return new ResponseEntity("Le compte a bien été modifié",HttpStatus.OK);
    }

    @DeleteMapping("/comptes/epargne/{idCompte}/delete")
    public ResponseEntity<String> deleteCompteEpargne(@PathVariable int idCompte) {
        CompteEpargne compteEpargne = new CompteEpargne();
        try {
            compteEpargne = compteEpargneRepository.findById(idCompte).get();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Ce compte epargne n'existe pas");
        }
        try {
            compteEpargneRepository.delete(compteEpargne);
        }
        catch (Exception e){
            throw new BadRequest("Ce compte épargne ne peut pas être supprimer");
        }
        return new ResponseEntity("Le compte épargne " + compteEpargne.getNumero() + "a bien été supprimé.", HttpStatus.OK);
    }

    @PostMapping("/comptes/epargne")
    public ResponseEntity<Retour> insertCompteEpargne(@RequestBody CompteEpargne compteEpargne){
        if(null != compteEpargne){
            if(!compteEpargne.getNumero().isEmpty() && compteEpargne.getSolde() > 0.0 && compteEpargne.getClient() != null && compteEpargne.getTauxInteret() > 0 && compteEpargne.getTauxInteret() < 5){
                compteEpargneRepository.save(compteEpargne);
            }
            else {
                throw new BadRequest("Merci de spécifier les informations du compte épargne !");
            }
        }
        else{
            throw new BadRequest("Merci de spécifier les informations du compte épargne !");
        }
        return new ResponseEntity(new Retour("Le compte épargne a bien été crée !"), HttpStatus.OK);
    }

    @PostMapping("/comptes/courant")
    public ResponseEntity<Retour> insertCompteCourant(@RequestBody CompteCourant compteCourant){
        if(null != compteCourant){
            if(!compteCourant.getNumero().isEmpty() && compteCourant.getSolde() > 0.0 && compteCourant.getClient() != null && compteCourant.getMontantDecouvertAutorise() > 0 && compteCourant.getMontantDecouvertAutorise() < 1000){
                compteCourantRepository.save(compteCourant);
            }
            else {
                throw new BadRequest("Merci de spécifier les informations du compte courant !");
            }
        }
        else{
            throw new BadRequest("Merci de spécifier les informations du compte courant !");
        }
        return new ResponseEntity(new Retour("Le compte courant a bien été crée !"), HttpStatus.OK);
    }

    @PutMapping("/comptes/epargne/{idCompte}/crediter/{montantACrediter}")
    public ResponseEntity<Retour> crediterCompteEpargne(@PathVariable int idCompte, @PathVariable int montantACrediter){
        CompteEpargne compteEpargne = new CompteEpargne();
        if(montantACrediter <= 0){
            throw new BadRequest("Merci d'indiquer une valeur à créditer positive.");
        }
        try{
            compteEpargne = compteEpargneRepository.findById(idCompte).get();
            compteEpargne.setSolde(compteEpargne.getSolde() + montantACrediter);
            compteEpargneRepository.save(compteEpargne);
        } catch (NoSuchElementException ex) {
            throw new NotFound("Ce compte épargne n'existe pas !");
        }
        return new ResponseEntity(new Retour("Le compte épargne a bien été crédité."), HttpStatus.OK);
    }

    @PutMapping("/comptes/courant/{idCompte}/crediter/{montantACrediter}")
    public ResponseEntity<Retour> crediterCompteCourant(@PathVariable int idCompte, @PathVariable int montantACrediter){
        CompteCourant compteCourant = new CompteCourant();
        if(montantACrediter <= 0){
            throw new BadRequest("Merci d'indiquer une valeur à créditer positive.");
        }
        try{
            compteCourant = compteCourantRepository.findById(idCompte).get();
            compteCourant.setSolde(compteCourant.getSolde() + montantACrediter);
            compteCourantRepository.save(compteCourant);
        } catch (NoSuchElementException ex) {
            throw new NotFound("Ce compte courant n'existe pas !");
        }
        return new ResponseEntity(new Retour("Le compte courant a bien été crédité."), HttpStatus.OK);
    }

    @PutMapping("/comptes/epargne/{idCompte}/debiter/{montantADebiter}")
    public ResponseEntity<Retour> debiterCompteEpargne(@PathVariable int idCompte, @PathVariable int montantADebiter){
        CompteEpargne compteEpargne = new CompteEpargne();
        if(montantADebiter <= 0){
            throw new BadRequest("Merci d'indiquer une valeur à créditer positive.");
        }
        try{
            compteEpargne = compteEpargneRepository.findById(idCompte).get();
            compteEpargne.setSolde(compteEpargne.getSolde() - montantADebiter);
            compteEpargneRepository.save(compteEpargne);
        } catch (NoSuchElementException ex) {
            throw new NotFound("Ce compte épargne n'existe pas !");
        }
        return new ResponseEntity(new Retour("Le compte épargne a bien été débité."), HttpStatus.OK);
    }

    @PutMapping("/comptes/courant/{idCompte}/debiter/{montantACrediter}")
    public ResponseEntity<Retour> debiterCompteCourant(@PathVariable int idCompte, @PathVariable int montantADebiter){
        CompteCourant compteCourant = new CompteCourant();
        if(montantADebiter <= 0){
            throw new BadRequest("Merci d'indiquer une valeur à créditer positive.");
        }
        try{
            compteCourant = compteCourantRepository.findById(idCompte).get();
            compteCourant.setSolde(compteCourant.getSolde() - montantADebiter);
            compteCourantRepository.save(compteCourant);
        } catch (NoSuchElementException ex) {
            throw new NotFound("Ce compte courant n'existe pas !");
        }
        return new ResponseEntity(new Retour("Le compte courant a bien été débité."), HttpStatus.OK);
    }
}
