package com.n26.repository;

import com.n26.model.Transaction;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

import static java.time.Instant.now;
import static java.util.Objects.isNull;

@Component
public class TransactionRepository {

    private Long interval = 60L;

    private ConcurrentNavigableMap<Long, List<Transaction>> transactions = new ConcurrentSkipListMap<>();

    public boolean addTransaction(Transaction transaction) {
        return isValid(transaction) ? addTransactionsBySecond(transaction) : false;
    }

    private boolean isValid(Transaction transaction) {
        return Duration.between(transaction.getTimestamp(), now()).getSeconds() <= interval;

    }

    private boolean addTransactionsBySecond(Transaction transaction) {
        final long transactionTime = transaction.getTimestamp().getEpochSecond();
        List<Transaction> transactionAtTime = transactions.get(transactionTime);
        if (isNull(transactionAtTime)) {
            transactionAtTime = new ArrayList<>();
        }
        transactionAtTime.add(transaction);
        transactions.put(transactionTime, transactionAtTime);
        return true;
    }

    public List<Transaction> getTransactions() {
        return transactions
                .tailMap(now().minusSeconds(interval).getEpochSecond())
                .values()
                .parallelStream()
                .flatMap(Collection::parallelStream)
                .collect(Collectors.toList());
    }

    public Integer getSizeOfHashMap() {
        return transactions.size();
    }

    public void delete() {
        transactions.clear();
    }

    @Scheduled(fixedDelay = 10 * 1000)
    void deleteOldEntries() {

        final int currentSize = transactions.size();
        if (currentSize > 0) {
            final ConcurrentNavigableMap<Long, List<Transaction>> expiredEntries = transactions
                    .headMap(now().minusSeconds(interval).getEpochSecond());
            if (expiredEntries.size() > 0) {
                expiredEntries.clear();
            }
        }
    }
}
