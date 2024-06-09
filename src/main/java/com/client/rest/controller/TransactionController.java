package com.client.rest.controller;
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


}
