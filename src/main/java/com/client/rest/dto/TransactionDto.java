package com.client.rest.dto;

import com.client.rest.enums.TypeTransaction;
import com.client.rest.model.Account;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Data
public class TransactionDto implements Serializable {

    @NotBlank(message = "El campo date es requerido")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date date;
    @NotBlank(message = "El campo typeTransaction es requerido")
    private TypeTransaction typeTransaction;
    @NotBlank(message = "El campo value es requerido")
    private double initialBalance;
    private double value;
    private double balance;
    private Integer transactionId;
    @NotBlank(message = "El campo accountNumber es requerido")
    private String accountNumber;
}
