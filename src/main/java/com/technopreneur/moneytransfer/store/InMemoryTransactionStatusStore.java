package com.technopreneur.moneytransfer.store;

import com.google.inject.Singleton;
import com.technopreneur.moneytransfer.util.TransactionStatus;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class InMemoryTransactionStatusStore implements TransactionStatusStore {

    private Map<String, TransactionStatus> transactionStatusStorage = new HashMap<>();

    public void queueTransaction(String transactionId) {
        synchronized (transactionId) {
            transactionStatusStorage.put(transactionId, TransactionStatus.QUEUED);
        }
    }
    public void startTransaction(String transactionId) {
        synchronized (transactionId) {
            transactionStatusStorage.put(transactionId, TransactionStatus.INPROCESS);
        }
    }

    public void startDebit(String transactionId) {
        synchronized (transactionId) {
            transactionStatusStorage.put(transactionId, TransactionStatus.DEBIT_STARTED);
        }
    }

    public void debitSuccessful(String transactionId) {
        synchronized (transactionId) {
            transactionStatusStorage.put(transactionId, TransactionStatus.DEBIT_COMPLETED);
        }
    }

    public void debitFailed(String transactionId) {
        synchronized (transactionId) {
            transactionStatusStorage.put(transactionId, TransactionStatus.DEBIT_FAILED);
        }

    }

    public void startCredit(String transactionId) {
        synchronized (transactionId) {
            transactionStatusStorage.put(transactionId, TransactionStatus.CREDIT_STARTED);
        }
    }

    public void creditSuccessful(String transactionId) {
        synchronized (transactionId) {
            transactionStatusStorage.put(transactionId, TransactionStatus.CREDIT_COMPLETED);
        }

    }

    public void markComplete(String transactionId) {
        synchronized (transactionId) {
            transactionStatusStorage.put(transactionId, TransactionStatus.COMPLETED);
        }

    }

    public void creditFailed(String transactionId) {

        synchronized (transactionId) {
            transactionStatusStorage.put(transactionId, TransactionStatus.CREDIT_FAILED);
        }

    }

    public void failed(String transactionId) {
        synchronized (transactionId) {
            transactionStatusStorage.put(transactionId, TransactionStatus.FAILED);
        }
    }

    public TransactionStatus getStatus(String transactionId){
        return this.transactionStatusStorage.get(transactionId);
    }


}
