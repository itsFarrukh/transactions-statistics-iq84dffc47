package com.n26.model;

import javax.validation.constraints.NotNull;
import java.time.Instant;

public class Transaction {
    Double amount;
    Instant timestamp;

    @NotNull
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @NotNull
    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
