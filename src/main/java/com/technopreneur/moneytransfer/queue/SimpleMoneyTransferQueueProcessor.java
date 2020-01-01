package com.technopreneur.moneytransfer.queue;

import javax.inject.Inject;

import com.technopreneur.moneytransfer.model.MoneyTransferTask;
import com.technopreneur.moneytransfer.processor.AccountProcessor;
import com.technopreneur.moneytransfer.store.MoneyTransferStore;

public class SimpleMoneyTransferQueueProcessor implements MoneyTransferQueueProcessor, Runnable {
	
	@Inject
	private MoneyTransferQueue moneyTransferQueue;
	
	@Inject
	private MoneyTransferStore moneyTransferStore;
	
	@Override
	public void process() {
		while (true) {
			try {
				MoneyTransferTask moneyTransferTask = moneyTransferQueue.getNext();
				AccountProcessor accountProcessor =  moneyTransferStore.getAccountProcessor(moneyTransferTask);
				accountProcessor.addToProcessingQueue(moneyTransferTask);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		process();
	}

}
