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
public class CompteEpargne {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    int idCompteEpargne;
    
    String numero;
    
    String intitule;
    
    double solde;
    
    double tauxInteret;
}
