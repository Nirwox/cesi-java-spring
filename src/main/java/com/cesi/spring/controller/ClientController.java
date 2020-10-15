/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesi.spring.controller;

import com.cesi.spring.model.Client;
import com.cesi.spring.repository.ClientRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Hugo-Louvet
 */
@RestController
@RequestMapping("/rest/1")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;
    
    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getClients() {
        return new ResponseEntity(clientRepository.findAll(),HttpStatus.OK);
    }
}
