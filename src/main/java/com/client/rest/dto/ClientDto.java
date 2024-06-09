package com.client.rest.dto;

import com.client.rest.enums.Generic;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Data
public class ClientDto implements Serializable {

    @NotBlank(message = "El campo name es requerido")
    private String name;
    @NotBlank(message = "El campo generic es requerido")
    private Generic generic;
    @NotBlank(message = "El campo age es requerido")
    private Integer age;
    @NotBlank(message = "El campo identification es requerido")
    private String identification;
    @NotBlank(message = "El campo address es requerido")
    private String address;
    @NotBlank(message = "El campo phone es requerido")
    private String phone;
    @NotBlank(message = "El campo password es requerido")
    private String password;
    private boolean status;
    private Integer clientId;

}
