package com.technopreneur.moneytransfer.service;

import java.util.List;
import java.util.stream.Collectors;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.technopreneur.moneytransfer.dao.AccountDao;
import com.technopreneur.moneytransfer.domain.Account;
import com.technopreneur.moneytransfer.dto.AccountDto;

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

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = Account.toAccount(accountDto);
		Account createdAccount = accountDao.createAccount(account);
		return AccountDto.toAccountDto(createdAccount);
	}

	@Override
	public boolean accountExist(Long accountId) {
		return accountDao.accountExist(accountId);
	}

	@Override
	public List<AccountDto> getAccounts() {
		return accountDao.getAccounts().stream()
				.map(AccountDto::toAccountDto)
				.collect(Collectors.toList());
	}

	@Override
	public AccountDto getAccount(Long accountId) {
		return AccountDto.toAccountDto(accountDao.getAccount(accountId));
	}

}
