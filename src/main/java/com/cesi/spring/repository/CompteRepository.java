package com.cesi.spring.repository;

import com.cesi.spring.model.Compte;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteRepository extends CrudRepository<Compte, Integer> {
    
}

