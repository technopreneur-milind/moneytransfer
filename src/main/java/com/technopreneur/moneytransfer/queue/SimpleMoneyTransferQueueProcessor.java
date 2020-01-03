package com.technopreneur.moneytransfer.queue;

import javax.inject.Inject;

import com.technopreneur.moneytransfer.model.MoneyTransferTask;
import com.technopreneur.moneytransfer.processor.AccountProcessor;
import com.technopreneur.moneytransfer.store.MoneyTransferAccountStore;

public class SimpleMoneyTransferQueueProcessor implements MoneyTransferQueueProcessor, Runnable {
	
	@Inject
	private MoneyTransferQueue moneyTransferQueue;
	
	@Inject
	private MoneyTransferAccountStore moneyTransferAccountStore;
	
	@Override
	public void process() {
		while (true) {
			try {
				MoneyTransferTask moneyTransferTask = moneyTransferQueue.getNext();
				AccountProcessor accountProcessor =  moneyTransferAccountStore.getAccountProcessor(moneyTransferTask);
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
