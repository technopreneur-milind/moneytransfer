package com.technopreneur.moneytransfer.event;

public interface TransactionEventPublisher {
    public void publishEvent(TransactionEvent event);
}
