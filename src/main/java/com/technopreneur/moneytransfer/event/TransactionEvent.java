package com.technopreneur.moneytransfer.event;

public class TransactionEvent extends Event {
    private String transactionId;

    public TransactionEvent(String transactionId, EventType eventType) {
        super(eventType);
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        return "TransactionEvent{" +
                "transactionId='" + transactionId + '\'' +
                ", id='" + id + '\'' +
                ", data=" + data +
                ", eventType=" + eventType +
                '}';
    }
}
