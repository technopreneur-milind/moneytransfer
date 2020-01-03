package com.technopreneur.moneytransfer.store;

import com.google.inject.Singleton;
import com.technopreneur.moneytransfer.util.TransactionStatus;

import java.util.HashMap;
import java.util.Map;

public interface TransactionStatusStore {

    void queueTransaction(String transactionId);

    void startTransaction(String transactionId);

    void startDebit(String transactionId);

    void debitSuccessful(String transactionId);

    void debitFailed(String transactionId);

    void startCredit(String transactionId);

    void creditSuccessful(String transactionId);

    void markComplete(String transactionId);

    void creditFailed(String transactionId);

    void failed(String transactionId);

    TransactionStatus getStatus(String transactionId);


}
