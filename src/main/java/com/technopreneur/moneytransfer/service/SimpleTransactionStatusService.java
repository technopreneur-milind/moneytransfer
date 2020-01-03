package com.technopreneur.moneytransfer.service;

import com.google.inject.Inject;
import com.technopreneur.moneytransfer.dao.TransactionStatusDao;
import com.technopreneur.moneytransfer.util.TransactionStatus;

public class SimpleTransactionStatusService implements TransactionStatusService {

    @Inject
    private TransactionStatusDao transactionStatusDao;
    @Override
    public String getTransactionStatus(String transactionId) {
        return transactionStatusDao.getTransactionStatus(transactionId).getStatus();
    }
}
