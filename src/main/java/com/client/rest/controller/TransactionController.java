package com.client.rest.controller;
import com.client.rest.dto.AccountDto;
import com.client.rest.dto.TransactionDto;
import com.client.rest.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("create")
    public ResponseEntity<TransactionDto> create(@RequestBody TransactionDto transactionDto) {
        try {
            TransactionDto transactionDto1 = transactionService.create(transactionDto);
            return new ResponseEntity<>(transactionDto1, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("all/{accountNumber}")
    public ResponseEntity<List<TransactionDto>> getAllTransactions(@PathVariable String accountNumber) {
        try {
            List<TransactionDto> transactionDtos = transactionService.allTransaction(accountNumber);
            return new ResponseEntity<>(transactionDtos, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("update/{transactionId}")
    public ResponseEntity<TransactionDto> update(@RequestBody TransactionDto transactionDto, @PathVariable Integer transactionId) {
        try {
            TransactionDto transactionDto1 = transactionService.update(transactionDto,transactionId);
            return new ResponseEntity<>(transactionDto1, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{transactionId}/{accountNumber}")
    public ResponseEntity<String> delete(@PathVariable Integer transactionId,@PathVariable String accountNumber) {
        try {
            transactionService.delete(transactionId,accountNumber);
            return new ResponseEntity<>("Movimiento eliminado exitosamente", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
