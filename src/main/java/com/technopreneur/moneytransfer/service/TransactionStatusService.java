package com.technopreneur.moneytransfer.service;

import com.technopreneur.moneytransfer.util.TransactionStatus;

public interface TransactionStatusService {

    String getTransactionStatus(String transactionId);
}
