package com.n26.service;

import com.n26.model.Transaction;

public interface TransactionService {
    Boolean add(Transaction transaction);
    void delete();
}
