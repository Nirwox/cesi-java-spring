package com.cesi.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    private int idCompteEpargne;
    
    private String numero;
    
    private String intitule;
    
    private double solde;
    
    private double tauxInteret;
    
    @ManyToOne
    @JoinColumn(name="id_client", nullable=false)
    private Client client;
}
