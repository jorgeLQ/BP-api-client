package com.client.rest.service.mapper;


import com.client.rest.dto.AccountDto;
import com.client.rest.dto.ClientDto;
import com.client.rest.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public Account buildAccount(AccountDto accountDto, boolean status, Integer clientId){
        Account account = new Account();

        account.setAccountNumber(accountDto.getAccountNumber());
        account.setTypeAccount(accountDto.getTypeAccount());
        account.setInitialBalance(accountDto.getInitialBalance());
        account.setClientId(clientId);
        account.setStatus(status);

        return account;
    }

    public AccountDto buildAccountDto(Account account, ClientDto clientDto){
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setTypeAccount(account.getTypeAccount());
        accountDto.setInitialBalance(account.getInitialBalance());
        accountDto.setClientDto(clientDto);
        accountDto.setStatus(account.isStatus());

        return accountDto;
    }
}
