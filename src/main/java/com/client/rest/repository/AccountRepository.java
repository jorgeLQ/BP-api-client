package com.client.rest.repository;



import com.client.rest.model.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByAccountNumber(String accountNumber);

    @Modifying
    @Transactional
    @Query(value = "UPDATE account SET initial_balance = :initial_balance WHERE account_number = :account_number", nativeQuery = true)
    void updateSaldo(@Param("account_number") String account_number, @Param("initial_balance") double initial_balance);

}
