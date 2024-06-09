package com.client.rest.service.impl;
import com.client.rest.dto.AccountDto;
import com.client.rest.dto.ClientDto;


import com.client.rest.exception.AccountCustomerException;
import com.client.rest.exception.ResourceNotFoundException;
import com.client.rest.model.Account;
import com.client.rest.repository.AccountRepository;
import com.client.rest.repository.ClientRepository;
import com.client.rest.service.AccountService;
import com.client.rest.service.ClientService;
import com.client.rest.service.mapper.AccountMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;

    @Override
    @Transactional
    public AccountDto create(AccountDto accountDto) {
        Optional<Account> accountDB= accountRepository.findByAccountNumber(accountDto.getAccountNumber());
        if(accountDB.isPresent()){
            throw new AccountCustomerException("La cuenta ya existe");
        }
        ClientDto clientDto = clientService.findClient(accountDto.getClientDto().getIdentification());

        if(clientDto == null){
            clientDto = clientService.create(accountDto.getClientDto());
        }
        /*if(clientDto == null){
            throw new AccountCustomerException("No se pudo crear la cuenta");
        }*/

        Account account = accountMapper.buildAccount(accountDto,true, clientDto.getClientId().intValue());
        account=accountRepository.save(account);

        AccountDto accountDto1= accountMapper.buildAccountDto(account,clientDto);

        return accountDto1;
    }


    @Override
    @Transactional
    public AccountDto update(AccountDto accountDto, String accountNumber) {

        Optional<Account> accountDB= accountRepository.findByAccountNumber(accountDto.getAccountNumber());
        if(!accountDB.isPresent()){
            throw new AccountCustomerException("La cuenta no existe");
        }

        Account existingAccount = accountDB.get();
        existingAccount.setAccountNumber(accountDto.getAccountNumber());
        existingAccount.setTypeAccount(accountDto.getTypeAccount());
        existingAccount.setInitialBalance(accountDto.getInitialBalance());

        Account updateAccount = accountRepository.save(existingAccount);

        AccountDto accountDto1 = accountMapper.buildAccountDto(updateAccount,accountDto.getClientDto());

        return accountDto1;
    }

    @Override
    @Transactional
    public void delete(String accountNumber) {
        Optional<Account> accountDB = accountRepository.findByAccountNumber(accountNumber);
        if (!accountDB.isPresent()) {
            throw new ResourceNotFoundException("Cliente no encontrado ");
        }
        // Eliminar la entidad
        accountRepository.delete(accountDB.get());

    }

    @Override
    public List<AccountDto> listAll() {
        try {
            List<Account> listDB= accountRepository.findAll();
            List<AccountDto> listDto = new ArrayList<>();

            for (Account account : listDB) {
                ClientDto clientDto= clientService.findClientById(account.getClientId());
                if (clientDto != null) {
                    // Crear el AccountDto usando el mapper
                    AccountDto accountDto = accountMapper.buildAccountDto(account, clientDto);
                    // Agregar el AccountDto a la lista
                    listDto.add(accountDto);
                } else {
                    log.warn("Cliente no encontrado");
                }
            }
            return listDto;
        }catch (Exception e){
            throw new AccountCustomerException("No se pudo crear la cuenta");
        }

    }

}
