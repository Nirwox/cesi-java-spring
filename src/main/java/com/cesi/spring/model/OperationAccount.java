/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesi.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OperationAccount {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idOperation;
        
    @OneToOne
    private CompteCourant compteFrom;
    
    @OneToOne
    private CompteCourant compteTo;
    
    private float montant;
    
    public OperationAccount(CompteCourant compteFrom, CompteCourant compteTo, float montant) {
        this.compteFrom = compteFrom;
        this.compteTo = compteTo;
        this.montant = montant;
    }
    
}
