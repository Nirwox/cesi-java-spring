/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesi.spring.controller;

import com.cesi.spring.model.Client;
import com.cesi.spring.model.Compte;
import com.cesi.spring.model.Solde;
import com.cesi.spring.repository.ClientRepository;
import com.cesi.spring.repository.CompteRepository;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author Hugo-Louvet
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ClientControllerTest {
    @Autowired
    private ClientController clientController;
    
    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private CompteRepository compteRepository;
    
    @Test
    public void getOneClientTest() {
        int clientId = 21;
        Client clientFromRepo = clientRepository.findById(clientId).get();
        
        Client clientFromController = clientController.getOneClient(clientId).getBody();
        
        assertThat(clientFromRepo).isEqualTo(clientFromController);
    }
    
    @Test
    public void getAllClientsTest() {
        Iterable<Client> clientsFromRepoIt = clientRepository.findAll();
        List<Client> clientsFromRepoL = new ArrayList<>();
        for(Client client : clientsFromRepoIt) {
            clientsFromRepoL.add(client);
        }
        
        List<Client> clientsFromController = clientController.getClients().getBody();
        
        assertThat(clientsFromRepoL).isEqualTo(clientsFromController);
    }
    
    @Test
    public void getClientCompteCourantTest() {
        int clientId =11;
        List<Compte> comptesCC = new ArrayList<>();
        Iterable<Compte> comptesRepo = compteRepository.findAll();
        for(Compte cpt : comptesRepo) {
            if(cpt.getTypeCompte().equals("Courant") && cpt.getClient().equals(clientRepository.findById(clientId).get())) {
                comptesCC.add(cpt);
            }
        }
        assertThat(comptesCC).isEqualTo(clientController.getAllComptesCourants(clientId).getBody());
    }
    
    @Test
    public void getClientCompteEpargneTest() {
        int clientId =11;
        List<Compte> comptesEC = new ArrayList<>();
        Iterable<Compte> comptesRepo = compteRepository.findAll();
        for(Compte cpt : comptesRepo) {
            if(cpt.getTypeCompte().equals("Epargne") && cpt.getClient().equals(clientRepository.findById(clientId).get())) {
                comptesEC.add(cpt);
            }
        }
        assertThat(comptesEC).isEqualTo(clientController.getAllComptesEpargnes(clientId).getBody());
    }
    
    @Test
    public void getSoldeTest() {
        int clientId = 21;
        double solde = 0;
        Client client = clientRepository.findById(clientId).get();
        
        Iterable<Compte> comptesRepo = compteRepository.findAll();
        for(Compte cpt : comptesRepo) {
            if(cpt.getClient().equals(clientRepository.findById(clientId).get())) {
                solde += cpt.getSolde();
            }
        }
        Solde soldeRetour = new Solde(client,solde);
        assertThat(soldeRetour).isEqualTo(clientController.getSoldeClient(clientId).getBody());
    }
}
