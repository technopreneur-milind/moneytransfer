package com.technopreneur.moneytransfer.repository;

public interface AccountRepository {

	public boolean debitAccount(Long accountId, Long amount);

	public boolean creditAccount(Long accountId, Long amount);

}
