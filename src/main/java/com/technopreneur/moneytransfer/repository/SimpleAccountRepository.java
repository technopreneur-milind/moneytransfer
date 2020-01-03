package com.technopreneur.moneytransfer.repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.stream.Collectors;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.technopreneur.moneytransfer.domain.Account;
import com.technopreneur.moneytransfer.factory.AccountLockFactory;

@Singleton
public class SimpleAccountRepository implements AccountRepository {

	private Map<Long, Account> accounts = new ConcurrentHashMap<>();

	@Inject
	private AccountLockFactory accountLockFactory;

	public SimpleAccountRepository() {
		init();
	}

	public void init() {
		/*Account account1 = new Account(1L, "Scott", 100L);
		Account account2 = new Account(2L, "Tiger", 200L);
		accounts.put(account1.getAccountId(), account1);
		accounts.put(account2.getAccountId(), account2);*/
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

	@Override
	public Account createAccount(Account account) {
		return accounts.compute(account.getAccountId(), (k, v) -> account);
	}

	@Override
	public boolean accountExist(Long accountId) {
		return accounts.containsKey(accountId);
	}

	@Override
	public List<Account> getAccounts() {
		return accounts.values().stream().collect(Collectors.toList());
	}

	@Override
	public Account getAccount(Long accountId) {
		return accounts.get(accountId);
	}

}
