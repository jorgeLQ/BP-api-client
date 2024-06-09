package com.client.rest.repository;

import com.client.rest.model.Account;
import com.client.rest.model.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM transaction WHERE account_account_id = :account_id", nativeQuery = true)
    List<Transaction>  transactionByAccountNumber(@Param("account_id") Integer account_id);

}
