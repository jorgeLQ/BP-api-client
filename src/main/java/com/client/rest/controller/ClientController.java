package com.client.rest.controller;

import com.client.rest.dto.ClientDto;
import com.client.rest.service.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("all")
    public ResponseEntity<List<ClientDto>> getAllClient() {
        try {
            List<ClientDto> clients = clientService.listAll();
            return new ResponseEntity<>(clients, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("create")
    public ResponseEntity<ClientDto> create(@RequestBody ClientDto clientDto) {
        try {
            ClientDto clientDto1 = clientService.create(clientDto);
            return new ResponseEntity<>(clientDto1, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("update/{identification}")
    public ResponseEntity<ClientDto> update(@RequestBody ClientDto clientDto, @PathVariable String identification) {
        //return clientService.update(clientDto,identification);
        try {
            ClientDto clientDto1 = clientService.update(clientDto,identification);
            return new ResponseEntity<>(clientDto1, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{identification}")
    public ResponseEntity<Void> delete(@PathVariable String identification) {
        try {
            clientService.delete(identification);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/find/{identification}")
    public ResponseEntity<ClientDto> find(@PathVariable String identification) {
        try {
            ClientDto clientDto = clientService.findClient(identification);
            return  new ResponseEntity<>(clientDto, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<ClientDto> findClientById(@PathVariable Integer id) {
        try {
            ClientDto clientDto = clientService.findClientById(id);
            return  new ResponseEntity<>(clientDto, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
