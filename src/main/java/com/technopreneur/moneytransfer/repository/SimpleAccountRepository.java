package com.technopreneur.moneytransfer.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.technopreneur.moneytransfer.factory.AccountLockFactory;
import com.technopreneur.moneytransfer.model.Account;

@Singleton
public class SimpleAccountRepository implements AccountRepository{

	private Map<Long, Account> accounts = new ConcurrentHashMap<>();

	@Inject
	private AccountLockFactory accountLockFactory;

	public SimpleAccountRepository() {
		init();
	}

	public void init() {
		Account account1 = new Account(1L, 100L);
		Account account2 = new Account(2L, 200L);
		accounts.put(account1.getAccountId(), account1);
		accounts.put(account2.getAccountId(), account2);
	}

	@Override
	public boolean debitAccount(Long accountId, Long amount) {
		Account account = accounts.get(accountId);
		Lock accountLock = accountLockFactory.getAccountLock(accountId);
		try {
			accountLock.lock();
			account.debit(amount);
		} finally {
			if (accountLock != null) {
				accountLock.unlock();
			}
		}
		return true;
	}
	
	
	@Override
	public boolean creditAccount(Long accountId, Long amount) {
		Account account = accounts.get(accountId);
		Lock accountLock = accountLockFactory.getAccountLock(accountId);
		try {
			accountLock.lock();
			account.credit(amount);
		} finally {
			if (accountLock != null) {
				accountLock.unlock();
			}
		}
		return true;
	}

}
