package com.client.rest.service;


import com.client.rest.dto.AccountDto;

import java.util.List;


public interface AccountService {

    AccountDto create(AccountDto accountDto);
    AccountDto update(AccountDto accountDto, String accountNumber);
    void delete(String accountNumber);
    List<AccountDto> listAll();

}
