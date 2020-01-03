package com.technopreneur.moneytransfer.event;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.technopreneur.moneytransfer.store.TransactionStatusStore;

@Singleton
public class SimpleTransactionEventPublisher implements TransactionEventPublisher {

    @Inject
    private EventPublisher eventPublisher;

    @Inject
    private TransactionStatusStore transactionStatusStore;

    @Override
    public void publishEvent(TransactionEvent event) {
        updateTransactionStatus(event);
        eventPublisher.publishEvent(event);
    }

    private void updateTransactionStatus(TransactionEvent event) {

        switch (event.getEventType()) {
            case TRANSACTION_QUEUED_EVENT: {
                transactionStatusStore.queueTransaction(event.getTransactionId());
                break;
            }
            case TRANSACTION_STARTED_EVENT: {
                transactionStatusStore.startTransaction(event.getTransactionId());
                break;
            }
            case TRANSACTION_COMPLETED_EVENT: {
                transactionStatusStore.markComplete(event.getTransactionId());
                break;
            }
            case TRANSACTION_DEBIT_STARTED_EVENT: {
                transactionStatusStore.startDebit(event.getTransactionId());
                break;
            }
            case TRANSACTION_DEBIT_COMPLETED_EVENT: {
                transactionStatusStore.debitSuccessful(event.getTransactionId());
                break;
            }
            case TRANSACTION_CREDIT_STARTED_EVENT: {
                transactionStatusStore.startCredit(event.getTransactionId());
                break;
            }
            case TRANSACTION_CREDIT_COMPLETED_EVENT: {
                transactionStatusStore.creditSuccessful(event.getTransactionId());
                break;
            }
            case TRANSACTION_FAILED_EVENT: {
                transactionStatusStore.failed(event.getTransactionId());
                break;
            }
            case TRANSACTION_DEBIT_FAILED_EVENT: {
                transactionStatusStore.debitFailed(event.getTransactionId());
                break;
            }
            case TRANSACTION_CREDIT_FAILED_EVENT: {
                transactionStatusStore.creditFailed(event.getTransactionId());
                break;
            }
        }
    }
}
