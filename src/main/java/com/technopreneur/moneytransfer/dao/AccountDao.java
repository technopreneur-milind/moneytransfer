package com.technopreneur.moneytransfer.dao;

public interface AccountDao {
	public boolean debitAccount(Long accountId, Long amount);

	public boolean creditAccount(Long accountId, Long amount);

}
