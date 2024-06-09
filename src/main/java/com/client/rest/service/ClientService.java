package com.client.rest.service;

import com.client.rest.dto.ClientDto;

import java.util.List;

public interface ClientService {

    ClientDto create(ClientDto clientDto);
    ClientDto update(ClientDto clientDto, String identification);
    void delete(String identification);
    List<ClientDto> listAll();
    ClientDto findClient(String identification);
    ClientDto findClientById(Integer id);
    //Integer findClientId(String identification);
    //Integer createClient(ClientDto clientDto);
}
