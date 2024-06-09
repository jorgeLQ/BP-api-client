package com.client.rest.service.mapper;

import com.client.rest.dto.AccountDto;
import com.client.rest.dto.TransactionDto;
import com.client.rest.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public Transaction buildTransaction(TransactionDto transactionDto, Integer accountId){
        Transaction transaction = new Transaction();

        transaction.setDate(transactionDto.getDate());
        transaction.setTypeTransaction(transactionDto.getTypeTransaction());
        transaction.setValue(transactionDto.getValue());
        transaction.setBalance(transactionDto.getBalance());
        //revisar
        //transaction.setAccount(accountId);

        return transaction;
    }
    public TransactionDto buildTransactionDto(Transaction transaction, String accountNumber, Double initialBalance){
        TransactionDto transactionDto = new TransactionDto();

        transactionDto.setDate(transaction.getDate());
        transactionDto.setTypeTransaction(transaction.getTypeTransaction());
        transactionDto.setInitialBalance(initialBalance);
        transactionDto.setValue(transaction.getValue());
        transactionDto.setBalance(transaction.getBalance());
        transactionDto.setAccountNumber(accountNumber);

        return transactionDto;
    }

    public TransactionDto buildTransactionDto1(Transaction transaction, String accountNumber){
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setTransactionId(transaction.getTransactionId());
        transactionDto.setDate(transaction.getDate());
        transactionDto.setTypeTransaction(transaction.getTypeTransaction());
        transactionDto.setValue(transaction.getValue());
        transactionDto.setBalance(transaction.getBalance());
        transactionDto.setAccountNumber(accountNumber);

        return transactionDto;
    }
}
