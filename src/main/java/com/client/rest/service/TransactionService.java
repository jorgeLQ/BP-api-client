package com.client.rest.service;

import com.client.rest.dto.AccountDto;
import com.client.rest.dto.TransactionDto;

import java.util.List;

public interface TransactionService {
    TransactionDto create(TransactionDto transactionDto);
    List<TransactionDto> allTransaction(String accountNumber);
    TransactionDto update(TransactionDto transactionDto, Integer transactionId);
    void delete(Integer transactionId, String accountNumber);
}
