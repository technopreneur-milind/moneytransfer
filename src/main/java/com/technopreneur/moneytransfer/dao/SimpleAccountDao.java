package com.technopreneur.moneytransfer.dao;

import java.util.List;

import com.google.inject.Inject;
import com.technopreneur.moneytransfer.domain.Account;
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

	@Override
	public Account createAccount(Account account) {
		return accountRepository.createAccount(account);
	}

	@Override
	public boolean accountExist(Long accountId) {
		return accountRepository.accountExist(accountId);
	}

	@Override
	public List<Account> getAccounts() {
		return accountRepository.getAccounts();
	}

	@Override
	public Account getAccount(Long accountId) {
		return accountRepository.getAccount(accountId);
	}

}
