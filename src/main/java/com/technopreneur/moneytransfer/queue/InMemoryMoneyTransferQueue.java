package com.technopreneur.moneytransfer.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.google.inject.Singleton;
import com.technopreneur.moneytransfer.model.MoneyTransferTask;

@Singleton
public class InMemoryMoneyTransferQueue implements MoneyTransferQueue {

	private BlockingQueue<MoneyTransferTask> queue = new LinkedBlockingQueue<>();

	@Override
	public void add(MoneyTransferTask moneyTransferTask) {
		queue.add(moneyTransferTask);
	}

	@Override
	public MoneyTransferTask getNext() throws InterruptedException {
		return queue.take();
	}

}
