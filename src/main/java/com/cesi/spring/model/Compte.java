/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cesi.spring.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_compte", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("MERE")
public class Compte {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    protected int idCompte;
    
    protected String numero;
    
    @Column(name="type_compte", insertable = false, updatable = false)
    protected String typeCompte;
    
    protected double solde;
    
    @ManyToOne
    @JoinColumn(name="id_client", nullable=false)
    protected Client client;
}
