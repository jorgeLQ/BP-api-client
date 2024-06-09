package com.client.rest.controller;
import com.client.rest.dto.AccountDto;
import com.client.rest.dto.ClientDto;
import com.client.rest.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("create")
    public ResponseEntity<AccountDto> create(@RequestBody AccountDto accountDto) {
        try {
            AccountDto accountDto1 = accountService.create(accountDto);
            return new ResponseEntity<>(accountDto1, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("all")
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        try {
            List<AccountDto> accounts = accountService.listAll();
            return new ResponseEntity<>(accounts, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("update/{accountNumber}")
    public ResponseEntity<AccountDto> update(@RequestBody AccountDto accountDto, @PathVariable String accountNumber) {
        try {
            AccountDto accountDto1 = accountService.update(accountDto,accountNumber);
            return new ResponseEntity<>(accountDto1, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{accountNumber}")
    public ResponseEntity<String> delete(@PathVariable String accountNumber) {
        try {
            accountService.delete(accountNumber);
            return new ResponseEntity<>("Cuenta eliminada exitosamente", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
