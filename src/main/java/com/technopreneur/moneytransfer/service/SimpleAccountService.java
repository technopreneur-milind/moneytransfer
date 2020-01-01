package com.technopreneur.moneytransfer.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.technopreneur.moneytransfer.dao.AccountDao;

@Singleton
public class SimpleAccountService implements AccountService {

	@Inject
	private AccountDao accountDao;

	@Override
	public boolean debitAccount(Long accountId, Long amount) {
		return accountDao.debitAccount(accountId, amount);
	}

	@Override
	public boolean creditAccount(Long accountId, Long amount) {
		return accountDao.creditAccount(accountId, amount);

	}

}
