package com.cesi.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    int idClient;
    
    String identifiant;
    
    String nom;
    
    String prenom;
}
