package com.client.rest.model;

import com.client.rest.enums.TypeTransaction;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Enumerated(EnumType.STRING)
    private TypeTransaction typeTransaction;
    @Builder.Default
    private double value=0.00;
    @Builder.Default
    private double balance=0.00;
    //private Integer accountId;
    @ManyToOne
    private Account account;

}
