package com.client.rest.service.impl;

import com.client.rest.dto.TransactionDto;
import com.client.rest.exception.AccountCustomerException;
import com.client.rest.exception.TransactionCustomerException;
import com.client.rest.model.Account;
import com.client.rest.model.Transaction;
import com.client.rest.repository.AccountRepository;
import com.client.rest.repository.TransactionRepository;
import com.client.rest.service.AccountService;
import com.client.rest.service.TransactionService;
import com.client.rest.service.mapper.TransactionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Override
    public TransactionDto create(TransactionDto transactionDto) {
        Optional<Account> accountDB= accountRepository.findByAccountNumber(transactionDto.getAccountNumber());
        if(!accountDB.isPresent()){
            throw new AccountCustomerException("La cuenta no existe");
        }
        Account existingAccount = accountDB.get();
        double nuevoSaldo= existingAccount.getInitialBalance() + transactionDto.getValue();
       /* if(nuevoSaldo <0){
            throw new TransactionCustomerException("Saldo insuficiente");
        }
        */
        existingAccount.setInitialBalance(nuevoSaldo);
        accountRepository.updateSaldo(existingAccount.getAccountNumber(),nuevoSaldo);

        Date todayDate = new Date();
        Transaction  transactionNew = new Transaction();
        transactionNew.setDate(todayDate);
        transactionNew.setTypeTransaction(transactionDto.getTypeTransaction());
        transactionNew.setValue(transactionDto.getValue());
        transactionNew.setBalance(nuevoSaldo);
        transactionNew.setAccountId(existingAccount.getAccountId());
        transactionRepository.save(transactionNew);

        TransactionDto transactionDto1 = transactionMapper.buildTransactionDto(transactionNew,existingAccount.getAccountNumber());


        return transactionDto1;
    }

    @Override
    public List<TransactionDto> allTransaction(String accountNumber) {

        return List.of();
    }
}
