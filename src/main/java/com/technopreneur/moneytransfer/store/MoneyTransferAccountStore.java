package com.technopreneur.moneytransfer.store;

import com.technopreneur.moneytransfer.model.MoneyTransferTask;
import com.technopreneur.moneytransfer.processor.AccountProcessor;

public interface MoneyTransferAccountStore {
	public AccountProcessor getAccountProcessor(MoneyTransferTask moneyTransferTask);

}
