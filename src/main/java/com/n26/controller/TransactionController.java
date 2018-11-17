package com.n26.controller;

import com.n26.model.Transaction;
import com.n26.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    @PostMapping("/")
    ResponseEntity<String> add(@RequestBody Transaction transaction){
        if(transactionService.add(transaction)){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/testing")
    ResponseEntity<String> checking(@Valid @RequestBody Transaction transaction){
        Instant instant = transaction.getTimestamp();
        LocalDate localDate = instant.atZone(ZoneOffset.UTC).toLocalDate();
        return new ResponseEntity<String>(HttpStatus.ACCEPTED);
    }
}

