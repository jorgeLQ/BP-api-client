package com.client.rest.service.mapper;

import com.client.rest.dto.ClientDto;
import com.client.rest.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Client buildClient(ClientDto clientDto, boolean status){
        Client client = new Client();
        client.setName(clientDto.getName());
        client.setAddress(clientDto.getAddress());
        client.setAge(clientDto.getAge());
        client.setGeneric(clientDto.getGeneric());
        client.setIdentification(clientDto.getIdentification());
        client.setPhone(clientDto.getPhone());
        client.setPassword(clientDto.getPassword());
        client.setStatus(status);

        return client;
    }

    public ClientDto buildClientDto(Client client){
        ClientDto clientDto = new ClientDto();
        clientDto.setClientId(client.getClientId());
        clientDto.setName(client.getName());
        clientDto.setAddress(client.getAddress());
        clientDto.setAge(client.getAge());
        clientDto.setGeneric(client.getGeneric());
        clientDto.setIdentification(client.getIdentification());
        clientDto.setPhone(client.getPhone());
        clientDto.setPassword(client.getPassword());
        clientDto.setStatus(client.isStatus());

        return clientDto;
    }
}
