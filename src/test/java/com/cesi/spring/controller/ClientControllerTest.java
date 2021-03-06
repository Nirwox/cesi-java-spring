package com.cesi.spring.controller;

import com.cesi.spring.model.Client;
import com.cesi.spring.model.Retour;
import com.cesi.spring.model.Solde;
import com.cesi.spring.repository.ClientRepository;
import com.cesi.spring.repository.CompteCourantRepository;
import com.cesi.spring.repository.CompteEpargneRepository;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ClientControllerTest {
    @Autowired
    private ClientController clientController;
    
    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private CompteCourantRepository compteCourantRepository;
    
    @Autowired
    private CompteEpargneRepository compteEpargneRepository;
    
    @Test
    public void getOneClientTest() {
        int clientId = 21;
        Client clientFromRepo = clientRepository.findById(clientId).get();
        
        Client clientFromController = clientController.getOneClient(clientId).getBody();
        
        assertThat(clientFromRepo).isEqualTo(clientFromController);
    }
    
    @Test
    public void deleteOneClientTest() {
        int clientId = 21;
        ResponseEntity<Retour> retour = clientController.deleteClient(clientId);
        
        Client clientFromRepo = clientRepository.findById(clientId).get();
        
        assertThat(retour.getBody().getMessage()).isEqualTo("Merci de supprimer les comptes courants et/ou épargnes de ce client avant de le supprimer");
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
        assertThat(compteCourantRepository.getComptesCourants(clientId)).isEqualTo(clientController.getAllComptesCourants(clientId).getBody());
    }
    
    @Test
    public void getClientCompteEpargneTest() {
        int clientId =11;
        assertThat(compteEpargneRepository.getComptesEpargnes(clientId)).isEqualTo(clientController.getAllComptesEpargnes(clientId).getBody());
    }
    
    @Test
    public void getSoldeTest() {
        int clientId = 21;
        Solde solde = new Solde();
        Client client = clientRepository.findById(clientId).get();
        solde.setClient(client);
        try {
            solde.setSolde(compteCourantRepository.getComptesSolde(clientId)+compteEpargneRepository.getComptesSolde(clientId));
        } catch(AopInvocationException e) {
            solde.setSolde(0);
        }
        assertThat(solde).isEqualTo(clientController.getSoldeClient(clientId).getBody());
    }
}
