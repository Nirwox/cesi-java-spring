package com.cesi.spring.repository;

import com.cesi.spring.model.Compte;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteRepository extends CrudRepository<Compte, Integer> {
    @Query(value = "SELECT * FROM compte WHERE type_compte = 'Courant' AND id_client = ?1", nativeQuery = true)
    List<Compte> getComptesCourants(int clientId);
    
    @Query(value = "SELECT * FROM compte WHERE type_compte = 'Epargne' AND id_client = ?1", nativeQuery = true)
    List<Compte> getComptesEpargnes(int clientId);
    
    @Query(value = "SELECT SUM(solde) FROM compte WHERE id_client = ?1", nativeQuery = true)
    int getComptesSolde(int clientId);
}

