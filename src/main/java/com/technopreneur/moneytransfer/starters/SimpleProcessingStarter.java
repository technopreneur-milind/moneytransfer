package com.technopreneur.moneytransfer.starters;

import com.google.inject.Inject;
import com.technopreneur.moneytransfer.queue.SimpleMoneyTransferQueueProcessor;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleProcessingStarter implements ProcessingStarter {
	
	@Inject
	SimpleMoneyTransferQueueProcessor moneyTransferQueueProcessor;

	@Override
	public void startProcessing() {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.submit(moneyTransferQueueProcessor);
	}

}
