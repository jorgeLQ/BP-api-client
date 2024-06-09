package com.client.rest.service.impl;

import com.client.rest.dto.ClientDto;
import com.client.rest.exception.ClientCustomerException;
import com.client.rest.exception.ResourceNotFoundException;
import com.client.rest.model.Client;
import com.client.rest.repository.ClientRepository;
import com.client.rest.service.ClientService;
import com.client.rest.service.mapper.ClientMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Override
    @Transactional
    public ClientDto create(ClientDto clientDto) {
        //convertir del metodo a una entidad
        Client client = clientMapper.buildClient(clientDto,true);
        client=clientRepository.save(client);

        ClientDto clientDto1= clientMapper.buildClientDto(client);

        return clientDto1;
    }

    @Override
    @Transactional
    public ClientDto update(ClientDto clientDto, String identification) {

        // Buscar cliente
        Optional<Client> ClienDB = clientRepository.findByIdentification(identification);

        if (!ClienDB.isPresent()) {
            throw new ResourceNotFoundException("Cliente no existe" );
        }

        Client existingClient = ClienDB.get();

        // Actualizar los campos
        existingClient.setName(clientDto.getName());
        existingClient.setAddress(clientDto.getAddress());
        existingClient.setAge(clientDto.getAge());
        existingClient.setGeneric(clientDto.getGeneric());
        existingClient.setIdentification(clientDto.getIdentification());
        existingClient.setPhone(clientDto.getPhone());
        existingClient.setPassword(clientDto.getPassword());
        existingClient.setStatus(clientDto.isStatus());

        Client updatedClient = clientRepository.save(existingClient);

        ClientDto clientDto1= clientMapper.buildClientDto(updatedClient);
        return clientDto1;
    }

    @Override
    @Transactional
    public void delete(String identification) {
        // Buscar la entidad existente por identificación
        Optional<Client> ClienDB = clientRepository.findByIdentification(identification);
        if (!ClienDB.isPresent()) {
            throw new ResourceNotFoundException("Cliente no encontrado " + identification);
        }
        // Eliminar la entidad
        clientRepository.delete(ClienDB.get());
    }

    @Override
    public List<ClientDto> listAll() {

        try {
            List<Client> list= clientRepository.findAll();
            List<ClientDto> listDto = new ArrayList<>();
            list.stream().forEach(client -> {
                listDto.add(clientMapper.buildClientDto(client));
            });
            return listDto;
        }catch (Exception e){
            throw new ClientCustomerException("No se pudo obtener los registros de Clientes");
        }


    }

    @Override
    public ClientDto findClient(String identification) {

        Optional<Client> optionalClient = clientRepository.findByIdentification(identification);
        if (!optionalClient.isPresent()) {
            return null;
        }
        ClientDto clientDto1= clientMapper.buildClientDto(optionalClient.get());

        return clientDto1;
    }

    @Override
    public ClientDto findClientById(Integer id) {
        Client client = clientRepository.findById(id).get();
        ClientDto clientDto = clientMapper.buildClientDto(client);
        return clientDto;
    }

}
