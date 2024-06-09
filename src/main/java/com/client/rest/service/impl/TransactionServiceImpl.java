package com.client.rest.service.impl;

import com.client.rest.dto.AccountDto;
import com.client.rest.dto.ClientDto;
import com.client.rest.dto.TransactionDto;
import com.client.rest.enums.TypeTransaction;
import com.client.rest.exception.AccountCustomerException;
import com.client.rest.exception.TransactionCustomerException;
import com.client.rest.model.Account;
import com.client.rest.model.Transaction;
import com.client.rest.repository.AccountRepository;
import com.client.rest.repository.TransactionRepository;
import com.client.rest.service.AccountService;
import com.client.rest.service.TransactionService;
import com.client.rest.service.mapper.TransactionMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Transactional
    public TransactionDto create(TransactionDto transactionDto) {
        Optional<Account> accountDB= accountRepository.findByAccountNumber(transactionDto.getAccountNumber());
        if(!accountDB.isPresent()){
            throw new AccountCustomerException("La cuenta no existe");
        }
        Account existingAccount = accountDB.get();
        double initialBalance = existingAccount.getInitialBalance();
        if (transactionDto.getTypeTransaction().equals(TypeTransaction.DEPOSITO)){
            double nuevoSaldo= existingAccount.getInitialBalance() + transactionDto.getValue();

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
            TransactionDto transactionDto1 = transactionMapper.buildTransactionDto(transactionNew,existingAccount.getAccountNumber(),initialBalance);

            return transactionDto1;
        }

        double nuevoSaldo= existingAccount.getInitialBalance() - transactionDto.getValue();
        if(nuevoSaldo <0) {
            throw new TransactionCustomerException("Saldo insuficiente");
        }
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
        TransactionDto transactionDto1 = transactionMapper.buildTransactionDto(transactionNew,existingAccount.getAccountNumber(),initialBalance);

        return transactionDto1;

    }

    @Override
    public List<TransactionDto> allTransaction(String accountNumber) {
        Optional<Account> accountDB= accountRepository.findByAccountNumber(accountNumber);
        if(!accountDB.isPresent()){
            throw new AccountCustomerException("La cuenta no existe");
        }
        Account account = accountDB.get();
        try {
            List<Transaction> transactionDB = transactionRepository.transactionByAccountNumber(account.getAccountId());
            List<TransactionDto> listDto = new ArrayList<>();

            transactionDB.stream().forEach(transaction -> {
                listDto.add(transactionMapper.buildTransactionDto1(transaction,accountNumber));
            });
            return listDto;
        }catch (Exception e){
            throw new TransactionCustomerException("No se pudo obtener los registros");
        }

    }

    @Override
    public TransactionDto update(TransactionDto transactionDto,Integer transactionId) {
        Optional<Account> accountDB= accountRepository.findByAccountNumber(transactionDto.getAccountNumber());
        if(!accountDB.isPresent()){
            throw new AccountCustomerException("La cuenta no existe");
        }
        Optional<Transaction> transactionDB= transactionRepository.findById(transactionId);
        if(!transactionDB.isPresent()){
            throw new AccountCustomerException("La transacción no existe");
        }

        Account existingAccount = accountDB.get();
        Transaction existTransaction = transactionDB.get();
        if (existTransaction.getTypeTransaction().equals(TypeTransaction.DEPOSITO)){
            double nuevoSaldo= existingAccount.getInitialBalance() - existTransaction.getValue();
            existingAccount.setInitialBalance(nuevoSaldo);
            accountRepository.updateSaldo(existingAccount.getAccountNumber(),nuevoSaldo);
        }
        if (existTransaction.getTypeTransaction().equals(TypeTransaction.RETIRO)){
            double nuevoSaldo= existingAccount.getInitialBalance() + existTransaction.getValue();
            existingAccount.setInitialBalance(nuevoSaldo);
            accountRepository.updateSaldo(existingAccount.getAccountNumber(),nuevoSaldo);
        }
        double initialBalance = existingAccount.getInitialBalance();
        if (transactionDto.getTypeTransaction().equals(TypeTransaction.DEPOSITO)){
            double nuevoSaldo= existingAccount.getInitialBalance() + transactionDto.getValue();

            existingAccount.setInitialBalance(nuevoSaldo);
            accountRepository.updateSaldo(existingAccount.getAccountNumber(),nuevoSaldo);
            Date todayDate = new Date();
            //Transaction  transactionNew = new Transaction();
            //transactionNew.setTransactionId(transactionId);
            existTransaction.setDate(todayDate);
            existTransaction.setTypeTransaction(transactionDto.getTypeTransaction());
            existTransaction.setValue(transactionDto.getValue());
            existTransaction.setBalance(nuevoSaldo);
            //existTransaction.setAccountId(existingAccount.getAccountId());
            transactionRepository.save(existTransaction);
            TransactionDto transactionDto1 = transactionMapper.buildTransactionDto(existTransaction,existingAccount.getAccountNumber(),initialBalance);

            return transactionDto1;
        }

        double nuevoSaldo= existingAccount.getInitialBalance() - transactionDto.getValue();
        if(nuevoSaldo <0) {
            throw new TransactionCustomerException("Saldo insuficiente");
        }

        existingAccount.setInitialBalance(nuevoSaldo);
        accountRepository.updateSaldo(existingAccount.getAccountNumber(),nuevoSaldo);
        Date todayDate = new Date();
        //Transaction  transactionNew = new Transaction();
        //transactionNew.setTransactionId(transactionId);
        existTransaction.setDate(todayDate);
        existTransaction.setTypeTransaction(transactionDto.getTypeTransaction());
        existTransaction.setValue(transactionDto.getValue());
        existTransaction.setBalance(nuevoSaldo);
        //existTransaction.setAccountId(existingAccount.getAccountId());
        transactionRepository.save(existTransaction);
        TransactionDto transactionDto1 = transactionMapper.buildTransactionDto(existTransaction,existingAccount.getAccountNumber(),initialBalance);

        return transactionDto1;
    }

    @Override
    public void delete(Integer transactionId,String accountNumber) {
        Optional<Account> accountDB= accountRepository.findByAccountNumber(accountNumber);
        if(!accountDB.isPresent()){
            throw new AccountCustomerException("La cuenta no existe");
        }
        Optional<Transaction> transactionDB= transactionRepository.findById(transactionId);
        if(!transactionDB.isPresent()){
            throw new AccountCustomerException("La transacción no existe");
        }

        Account existingAccount = accountDB.get();
        Transaction existTransaction = transactionDB.get();

        if (existTransaction.getTypeTransaction().equals(TypeTransaction.DEPOSITO)){
            double nuevoSaldo= existingAccount.getInitialBalance() - existTransaction.getValue();
            existingAccount.setInitialBalance(nuevoSaldo);
            accountRepository.updateSaldo(existingAccount.getAccountNumber(),nuevoSaldo);
        }
        if (existTransaction.getTypeTransaction().equals(TypeTransaction.RETIRO)){
            double nuevoSaldo= existingAccount.getInitialBalance() + existTransaction.getValue();
            existingAccount.setInitialBalance(nuevoSaldo);
            accountRepository.updateSaldo(existingAccount.getAccountNumber(),nuevoSaldo);
        }
        transactionRepository.delete(existTransaction);

    }
}
