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
@DiscriminatorValue("Epargne")
public class CompteEpargne extends Compte {
    private double tauxInteret;

    @Override
    public String toString(){
        return("Le compte épargne numéro " + numero + " est possédé par le client "
                + client.getNom() + " " + client.getPrenom() + ". Son solde s'élève à "
                + solde + " et son taux intérêt est de " + tauxInteret + "."
        );
    }
}
