package com.technopreneur.moneytransfer.dto;

import com.technopreneur.moneytransfer.domain.Account;

public class AccountDto {
	private Long accountId;

	private String name;

	private Long balance = 0L;
	

	public AccountDto(Long accountId, String name, Long balance) {
		super();
		this.accountId = accountId;
		this.name = name;
		this.balance = balance;
	}

	public Long getAccountId() {
		return accountId;
	}

	public String getName() {
		return name;
	}

	public Long getBalance() {
		return balance;
	}
	
	public static AccountDto toAccountDto(Account account)
	{
		return new AccountDto(account.getAccountId(), 
				account.getName(), account.getBalance());
	}

	

}
