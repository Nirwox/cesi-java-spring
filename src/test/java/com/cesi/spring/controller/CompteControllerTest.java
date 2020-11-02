package com.cesi.spring.controller;

import com.cesi.spring.model.Client;
import com.cesi.spring.model.CompteCourant;
import com.cesi.spring.model.CompteEpargne;
import com.cesi.spring.model.Solde;
import com.cesi.spring.repository.ClientRepository;
import com.cesi.spring.repository.CompteCourantRepository;
import com.cesi.spring.repository.CompteEpargneRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CompteControllerTest {
    @Autowired
    private CompteController compteController;

    @Autowired
    private CompteCourantRepository compteCourantRepository;

    @Autowired
    private CompteEpargneRepository compteEpargneRepository;

    @Test
    public void crediterCompteEpargneTest(){
        int montantCredit = 100;
        int idCompte = 1;
        CompteEpargne compteEpargne = compteEpargneRepository.findById(idCompte).get();

        try{
            compteEpargne.setSolde(compteEpargne.getSolde() + montantCredit);
        } catch (NoSuchElementException ex) {
            compteEpargne.setSolde(compteEpargne.getSolde());
        }

        assertThat(compteEpargne).isEqualTo(compteController.crediterCompteEpargne(idCompte, montantCredit).getBody());
    }

    @Test
    public void crediterCompteCourantTest(){
        int montantCredit = 100;
        int idCompte = 1;
        CompteCourant compteCourant = compteCourantRepository.findById(idCompte).get();

        try{
            compteCourant.setSolde(compteCourant.getSolde() + montantCredit);
        } catch (NoSuchElementException ex) {
            compteCourant.setSolde(compteCourant.getSolde());
        }

        assertThat(compteCourant).isEqualTo(compteController.crediterCompteCourant(idCompte, montantCredit).getBody());
    }

    @Test
    public void debiterCompteEpargneTest(){
        int montantDebit = 100;
        int idCompte = 1;
        CompteEpargne compteEpargne = compteEpargneRepository.findById(idCompte).get();

        try{
            compteEpargne.setSolde(compteEpargne.getSolde() - montantDebit);
        } catch (NoSuchElementException ex) {
            compteEpargne.setSolde(compteEpargne.getSolde());
        }

        assertThat(compteEpargne).isEqualTo(compteController.debiterCompteEpargne(idCompte, montantDebit).getBody());
    }

    @Test
    public void debiterCompteCourantTest(){
        int montantDebit = 100;
        int idCompte = 1;
        CompteCourant compteCourant = compteCourantRepository.findById(idCompte).get();

        try{
            compteCourant.setSolde(compteCourant.getSolde() - montantDebit);
        } catch (NoSuchElementException ex) {
            compteCourant.setSolde(compteCourant.getSolde());
        }

        assertThat(compteCourant).isEqualTo(compteController.debiterCompteCourant(idCompte, montantDebit).getBody());
    }
}
