package com.client.rest.service;

import com.client.rest.dto.ClientDto;
import com.client.rest.enums.Generic;
import com.client.rest.exception.ClientCustomerException;
import com.client.rest.model.Client;
import com.client.rest.repository.ClientRepository;
import com.client.rest.service.impl.ClientServiceImpl;
import com.client.rest.service.mapper.ClientMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientServiceImpl clientService;  // Suponiendo que la implementación del servicio se llama ClientServiceImpl

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListAll_Success() {
        // Preparar datos de prueba
        //Client client1 = new Client(1, "Cliente 1", true);
        //Client client2 = new Client(2L, "Cliente 2", "cliente2@example.com");
        //List<Client> clientList = Arrays.asList(client1, client2);

        ClientDto clientDto1 = ClientDto.builder()
                .name("Mario Cardenas")
                .generic(Generic.MASCULINO)
                .age(30)
                .identification("1525172652")
                .address("Quitumbe")
                .phone("0979378293")
                .password("mario123")
                .status(true)
                .build();
        ClientDto clientDto2 = ClientDto.builder()
                .name("Sofia Arpi")
                .generic(Generic.FEMENINO)
                .age(18)
                .identification("1725171619")
                .address("Mercado Mayorista")
                .phone("0985490223")
                .password("sofia123")
                .status(true)
                .build();
        List<ClientDto> clientDtoList = Arrays.asList(clientDto1, clientDto2);

        // Configurar el comportamiento del mock
        when(clientService.listAll()).thenReturn(clientDtoList);


        // Llamar al método a probar
        List<ClientDto> result = clientService.listAll();

        // Verificar el resultado
        assertNotNull(result);
        //assertEquals(clientDtoList.size(), result.size());
        assertEquals(clientDtoList, result);

        // Verificar que los métodos mockeados se llamaron correctamente
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    public void testListAll_Exception() {
        // Configurar el mock para lanzar una excepción
        when(clientRepository.findAll()).thenThrow(new RuntimeException("Error de base de datos"));

        // Verificar que se lanza la excepción correcta
        ClientCustomerException exception = assertThrows(ClientCustomerException.class, () -> {
            clientService.listAll();
        });

        assertEquals("No se pudo obtener los registros de Clientes", exception.getMessage());

        // Verificar que el método mockeado se llamó
        verify(clientRepository, times(1)).findAll();
    }
}
