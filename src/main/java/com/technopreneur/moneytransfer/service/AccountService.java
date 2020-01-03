package com.technopreneur.moneytransfer.service;

import java.util.List;

import com.technopreneur.moneytransfer.dto.AccountDto;

public interface AccountService {

	public boolean debitAccount(Long accountId, Long amount);

	public boolean creditAccount(Long accountId, Long amount);
	
	public AccountDto createAccount(AccountDto accountDto);
	
	public boolean accountExist(Long accountId);
	
	public List<AccountDto> getAccounts();
	
	public AccountDto getAccount(Long accountId);

}
