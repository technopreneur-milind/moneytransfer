package com.technopreneur.moneytransfer.dao;

import com.google.inject.Inject;
import com.technopreneur.moneytransfer.store.TransactionStatusStore;
import com.technopreneur.moneytransfer.util.TransactionStatus;

public class SimpleTransactionStatusDao implements TransactionStatusDao {

    @Inject
    private TransactionStatusStore transactionStatusStore;

    @Override
    public TransactionStatus getTransactionStatus(String transactionId) {
        return transactionStatusStore.getStatus(transactionId);
    }
}
