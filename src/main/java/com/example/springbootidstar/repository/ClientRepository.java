package com.example.springbootidstar.repository;


import com.example.springbootidstar.model.oauth.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {
    Client findOneByClientId(String clientId);
}
