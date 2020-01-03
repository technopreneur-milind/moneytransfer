package com.technopreneur.moneytransfer.dao;

import com.technopreneur.moneytransfer.util.TransactionStatus;

public interface TransactionStatusDao {

    TransactionStatus getTransactionStatus(String transactionId);
}
