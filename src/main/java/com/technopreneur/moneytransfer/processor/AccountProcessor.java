package com.technopreneur.moneytransfer.processor;

import com.technopreneur.moneytransfer.model.MoneyTransferTask;
import com.technopreneur.moneytransfer.service.AccountService;

public interface AccountProcessor {

	public void addToProcessingQueue(MoneyTransferTask moneyTransferTask);
	
}
