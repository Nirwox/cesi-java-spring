package com.cesi.spring.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("Courant")
public class CompteCourant extends Compte {    
    private double montantDecouvertAutorise;

    @Override
    public String toString(){
        return("Le compte épargne numéro " + numero + " est possédé par le client "
                + client.getNom() + " " + client.getPrenom() + ". Son solde s'élève à "
                + solde + " et son découvert est de " + montantDecouvertAutorise + "."
        );
    }

}
