package com.client.rest.model;

import com.client.rest.enums.TypeAccount;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;
    private String accountNumber;
    @Enumerated(EnumType.STRING)
    private TypeAccount typeAccount;
    private double initialBalance;
    private boolean status;
    private Integer clientId;
    @Builder.Default
    @OneToMany(mappedBy = "account")
    List<Transaction> transactionList = new ArrayList<>();

}