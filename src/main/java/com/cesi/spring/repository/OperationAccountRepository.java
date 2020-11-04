package com.cesi.spring.repository;

import com.cesi.spring.model.CompteCourant;
import com.cesi.spring.model.OperationAccount;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationAccountRepository extends CrudRepository<OperationAccount, Integer> {
    @Query(value = "SELECT id_operation,montant,compte_from_id_compte,compte_to_id_compte FROM operation_account\n" +
    "JOIN compte_courant ON compte_from_id_compte = compte_courant.id_compte OR compte_to_id_compte = compte_courant.id_compte\n" +
    "JOIN client ON compte_courant.id_client = client.id_client\n" +
    "WHERE client.id_client = ?1", nativeQuery = true)
    List<OperationAccount> getOperations(int clientId);
}

