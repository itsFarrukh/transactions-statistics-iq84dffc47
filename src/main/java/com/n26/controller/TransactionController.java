package com.n26.controller;

import com.n26.model.Transaction;
import com.n26.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<String> add(@RequestBody Transaction transaction){
        if(transactionService.add(transaction)){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @RequestMapping(method = RequestMethod.DELETE)
    ResponseEntity<String> delete(){
        transactionService.delete();
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/tt")
    ResponseEntity<String> checking(@RequestBody Transaction transaction){

/*
        Instant abhi = Instant.now();
        Instant abhiSephelay = Instant.now().minusSeconds(60);
        System.out.println(abhi.toEpochMilli());
        System.out.println(abhiSephelay.toEpochMilli());
*/
        System.out.println(Instant.now());
        System.out.println(transaction.getTimestamp());
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }
}

