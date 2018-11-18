package com.n26.repository;


import com.n26.model.Transaction;
import org.junit.Test;

import java.time.Instant;

import static org.junit.Assert.assertEquals;

public class TransactionRepositoryTest {

    TransactionRepository transactionRepository = new TransactionRepository();
    @Test
    public void  saveValidTransaction() {

        Transaction transaction = new Transaction(5.3, Instant.now());
        assertEquals("This is not a valid transaction",true, transactionRepository.addTransaction(transaction));
    }
    @Test
    public void saveInvalidTransaction(){
        Transaction transaction = new Transaction(10.2,Instant.now().minusSeconds(61));
        assertEquals("This is not a valid transaction",false,transactionRepository.addTransaction(transaction));
    }
    @Test
    public void  checkDeleteIsWorking(){
        Transaction t1 = new Transaction(6.2,Instant.now());
        transactionRepository.addTransaction(t1);
        transactionRepository.delete();
        assertEquals("Delete operation is not working as expected",0,(Object)transactionRepository.getSizeOfHashMap());

    }
}
