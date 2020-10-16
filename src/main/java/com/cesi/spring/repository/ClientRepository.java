package com.cesi.spring.repository;

import com.cesi.spring.model.Client;
import com.cesi.spring.model.Compte;
import com.cesi.spring.model.CompteCourant;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {
}

