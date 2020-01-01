package com.technopreneur.moneytransfer.dao;

import com.google.inject.Inject;
import com.technopreneur.moneytransfer.repository.AccountRepository;

public class SimpleAccountDao implements AccountDao {

	@Inject
	private AccountRepository accountRepository;

	@Override
	public boolean debitAccount(Long accountId, Long amount) {
		return accountRepository.debitAccount(accountId, amount);
	}

	@Override
	public boolean creditAccount(Long accountId, Long amount) {
		return accountRepository.creditAccount(accountId, amount);

	}

}
