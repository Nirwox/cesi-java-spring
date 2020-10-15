package com.cesi.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompteEpargne {
    String numero;
    String intitule;
    double solde;
    double tauxInteret;
}
