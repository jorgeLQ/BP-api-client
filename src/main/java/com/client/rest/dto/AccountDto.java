package com.client.rest.dto;

import com.client.rest.enums.TypeAccount;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class AccountDto implements Serializable {

    @NotBlank(message = "El campo accountNumber es requerido")
    private String accountNumber;
    @NotBlank(message = "El campo typeAccount es requerido")
    private TypeAccount typeAccount;
    @NotBlank(message = "El campo initialBalance es requerido")
    private double initialBalance;
    private ClientDto clientDto;
    private boolean status;
}