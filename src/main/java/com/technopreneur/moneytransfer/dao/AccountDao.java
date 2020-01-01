package com.technopreneur.moneytransfer.dao;

import java.util.List;

import com.technopreneur.moneytransfer.domain.Account;

public interface AccountDao {
	public boolean debitAccount(Long accountId, Long amount);

	public boolean creditAccount(Long accountId, Long amount);

	public Account createAccount(Account account);

	public boolean accountExist(Long accountId);

	public List<Account> getAccounts();

	public Account getAccount(Long accountId);

}
