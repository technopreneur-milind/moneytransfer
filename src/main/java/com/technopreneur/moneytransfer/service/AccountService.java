package com.technopreneur.moneytransfer.service;

public interface AccountService {

	public boolean debitAccount(Long accountId, Long amount);

	public boolean creditAccount(Long accountId, Long amount);

}
