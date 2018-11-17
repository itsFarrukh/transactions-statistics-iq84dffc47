package com.n26.service.impl;

import com.n26.common.Utils;
import com.n26.exception.UnprocessableException;
import com.n26.model.Transaction;
import com.n26.repository.TransactionRepository;
import com.n26.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Override
    public Boolean add(Transaction transaction) {
       if(Utils.isValidDate(transaction.getTimestamp())){
           throw new UnprocessableException();
       }
       return transactionRepository.addTransaction(transaction);

    }

}
