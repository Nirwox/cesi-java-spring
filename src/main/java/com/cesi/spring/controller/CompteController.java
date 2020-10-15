/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesi.spring.controller;

import com.cesi.spring.model.Compte;
import com.cesi.spring.model.CompteCourant;
import com.cesi.spring.repository.CompteRepository;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/1")
public class CompteController {
    @Autowired
    private CompteRepository compteRepository;
    
    @PostMapping("/compteCourant")
    public void createCompteCourant(CompteCourant newCompte) {
        compteRepository.save(newCompte);
    }

    @DeleteMapping("/compteCourant/{idCompte}")
    public void deleteCompteCourant(@PathVariable int idCompte) {
        compteRepository.deleteById(idCompte);
        System.out.println("Le compte a bien été supprimé.");

        Compte compte = new Compte();
        try {
            compte = compteRepository.findById(idCompte).get();
            compteRepository.delete(compte);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Ce compte courant n'existe pas");
        }
    }

    @PostMapping("/compteCourant/{idCompte")
    public void updateCompteCourant(int idCompte){
        compteRepository.findById(idCompte);
    }
}
