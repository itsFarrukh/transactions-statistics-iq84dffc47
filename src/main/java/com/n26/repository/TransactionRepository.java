package com.n26.repository;

import com.n26.model.Transaction;
import org.springframework.stereotype.Component;
import org.threeten.extra.Interval;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

import static java.time.Instant.now;
import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

@Component
public class TransactionRepository {

    private Long interval = 60L;

    private ConcurrentNavigableMap<Long, List<Transaction>> transactions = new ConcurrentSkipListMap<>();
    public boolean addTransaction(Transaction transaction){
      // return   isValid(transaction)? addTransactionsBySecond(transaction):false;
        if (isValid(transaction)) {
            addTransactionsBySecond(transaction);
            return true;
        }
        return false;
    }
    private boolean isValid(Transaction transaction){
        return Duration.between(transaction.getTimestamp(), now()).getSeconds() <= interval;

    }
    private boolean addTransactionsBySecond(Transaction transaction){
        final long transactionTime = transaction.getTimestamp().getEpochSecond();
        List<Transaction> transactionAtTime = transactions.get(transactionTime);
        if(isNull(transactionAtTime)){
            transactionAtTime = new ArrayList<>();
        }
        transactionAtTime.add(transaction);
        transactions.put(transactionTime,transactionAtTime);

        //transactions.put(transaction.getTimestamp().getEpochSecond(),new ArrayList<>());
        return true;
    }
     public List<Transaction> getTransactions(){
        return transactions
                .tailMap(now().minusSeconds(interval).getEpochSecond())
                .values()
                .parallelStream()
                .flatMap(Collection::parallelStream)
                .collect(Collectors.toList());
    }
    public void delete(){
         transactions.clear();
    }
}
