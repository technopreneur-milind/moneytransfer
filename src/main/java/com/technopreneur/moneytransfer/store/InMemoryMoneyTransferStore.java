package com.technopreneur.moneytransfer.store;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.technopreneur.moneytransfer.model.MoneyTransferTask;
import com.technopreneur.moneytransfer.processor.AccountProcessor;
import com.technopreneur.moneytransfer.processor.SimpleAccountProcessor;
import com.technopreneur.moneytransfer.service.AccountService;

@Singleton
public class InMemoryMoneyTransferStore implements MoneyTransferAccountStore {
	
	@Inject
	private AccountService accountService;

	private Map<Long, AccountProcessor> storage = new ConcurrentHashMap<>();

	@Override
	public AccountProcessor getAccountProcessor(MoneyTransferTask moneyTransferTask) {
		Long accountId = moneyTransferTask.getTransferDetail().getFromAccount();
		return storage.computeIfAbsent(accountId, k -> new SimpleAccountProcessor(accountId,accountService));
	}
	
}
