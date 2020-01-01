package com.technopreneur.moneytransfer.starters;

import com.google.inject.Inject;
import com.technopreneur.moneytransfer.queue.SimpleMoneyTransferQueueProcessor;

public class SimpleProcessingStarter implements ProcessingStarter {
	
	@Inject
	SimpleMoneyTransferQueueProcessor moneyTransferQueueProcessor;

	@Override
	public void startProcessing() {
		new Thread(moneyTransferQueueProcessor).start();
	}

}
