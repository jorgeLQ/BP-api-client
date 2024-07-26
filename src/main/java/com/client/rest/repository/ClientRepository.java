package com.client.rest.repository;


import com.client.rest.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

                                                        //entidad , id
//antes se llamab DAO o mejor dicho se llama DAO en java y repository en spring
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Optional<Client> findByIdentification(String identification);
}
