package com.technopreneur.moneytransfer.store;

import com.technopreneur.moneytransfer.model.MoneyTransferTask;
import com.technopreneur.moneytransfer.processor.AccountProcessor;

public interface MoneyTransferStore {
	public AccountProcessor getAccountProcessor(MoneyTransferTask moneyTransferTask);

}
