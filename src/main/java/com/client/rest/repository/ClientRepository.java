package com.client.rest.repository;


import com.client.rest.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ClientRepository extends JpaRepository<Client, Integer> {

    Optional<Client> findByIdentification(String identification);
}
