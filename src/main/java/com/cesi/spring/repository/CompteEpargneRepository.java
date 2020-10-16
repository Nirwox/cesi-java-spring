package com.cesi.spring.repository;

import com.cesi.spring.model.CompteCourant;
import com.cesi.spring.model.CompteEpargne;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteEpargneRepository extends CrudRepository<CompteEpargne, Integer> {
    @Query(value = "SELECT * FROM compte WHERE type_compte = 'Epargne' AND id_client = ?1", nativeQuery = true)
    List<CompteEpargne> getComptesEpargnes(int clientId);
    
    @Query(value = "SELECT SUM(solde) FROM compte WHERE type_compte = 'Epargne' AND id_client = ?1", nativeQuery = true)
    int getComptesSolde(int clientId);
}

