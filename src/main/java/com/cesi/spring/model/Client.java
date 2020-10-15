package com.cesi.spring.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    private int idClient;
    
    private String identifiant;
    
    private String nom;
    
    private String prenom;
    
    @OneToMany(mappedBy="client")
    private List<CompteCourant> comptesCourants;
    
    @OneToMany(mappedBy="client")
    private List<CompteEpargne> comptesEpargnes;
}
