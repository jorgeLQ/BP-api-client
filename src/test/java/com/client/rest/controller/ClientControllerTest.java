package com.client.rest.controller;

import com.client.rest.dto.ClientDto;
import com.client.rest.enums.Generic;
import com.client.rest.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(excludeAutoConfiguration = SecurityAutoConfiguration.class)
@ContextConfiguration(classes = ClientController.class)
public class ClientControllerTest {
    @Autowired
     MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    //@InjectMocks
    //private ClientController clientController;
    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        //MockitoAnnotations.openMocks(this);
        //this.mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }

    @Test
    public void testGetAllClient() throws Exception {
        // Preparar datos de prueba

        //ClientDto client1 = new ClientDto("jorge", Generic.MASCULINO, 19,"123456789","Quitumbe","0979378293","pass123",true);
        ClientDto clientDto = ClientDto.builder()
                .name("Juan Perez")
                .generic(Generic.MASCULINO)
                .age(20)
                .identification("123456789")
                .address("Quitumbe")
                .phone("0979378293")
                .password("jperez123")
                .status(true)
                .build();
        ClientDto clientDto2 = ClientDto.builder()
                .name("Juan Lopez")
                .generic(Generic.MASCULINO)
                .age(45)
                .identification("1227263423")
                .address("La Armenia")
                .phone("0979478499")
                .password("juan123")
                .status(true)
                .build();
        //ClientDto client2 = new ClientDto(2L, "Cliente 2", "cliente2@example.com");
        List<ClientDto> clientList = Arrays.asList(clientDto, clientDto2);

        // Configurar el comportamiento del mock
        when(clientService.listAll()).thenReturn(clientList);

        // Realizar la llamada al endpoint y verificar el resultado
        mockMvc.perform(MockMvcRequestBuilders.get("/clientes/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                //.andExpect(content().json("[{\"name\":\"Cliente 1\",\"email\":\"cliente1@example.com\"}]"));
    }

    @Test
    public void testGetAllClient_InternalServerError() throws Exception {
        // Configurar el mock para lanzar una excepción
        when(clientService.listAll()).thenThrow(new RuntimeException("Error interno"));

        // Realizar la llamada al endpoint y verificar el resultado
        mockMvc.perform(MockMvcRequestBuilders.get("/clientes/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testCreateClient() throws Exception {
        ClientDto clientDto = ClientDto.builder()
                .name("Juan Perez")
                .generic(Generic.MASCULINO)
                .age(20)
                .identification("123456789")
                .address("Quitumbe")
                .phone("0979378293")
                .password("jperez123")
                .status(true)
                .build();

        String clientDtoJson = objectMapper.writeValueAsString(clientDto);
        when(clientService.create(any(ClientDto.class))).thenReturn(clientDto);
        //System.out.println(clientDtoJson);

        // Realizar la llamada al endpoint y verificar el resultado
        mockMvc.perform(post("/clientes/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clientDtoJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Juan Perez"))
                .andExpect(jsonPath("$.generic").value("MASCULINO"))
                .andExpect(jsonPath("$.age").value(20))
                .andExpect(jsonPath("$.identification").value("123456789"))
                .andExpect(jsonPath("$.address").value("Quitumbe"))
                .andExpect(jsonPath("$.phone").value("0979378293"))
                .andExpect(jsonPath("$.password").value("jperez123"))
                .andExpect(jsonPath("$.status").value(true));


    }

}
