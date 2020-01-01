package com.technopreneur.moneytransfer.queue;

import com.technopreneur.moneytransfer.model.MoneyTransferTask;

public interface MoneyTransferQueue {

	public void add(MoneyTransferTask moneyTransferTask);

	public MoneyTransferTask getNext() throws InterruptedException;

}
