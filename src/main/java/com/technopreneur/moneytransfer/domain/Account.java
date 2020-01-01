package com.technopreneur.moneytransfer.domain;

import com.technopreneur.moneytransfer.dto.AccountDto;

public class Account {
	private Long accountId;

	private String name;

	private Long balance = 0L;

	public Account(Long accountId, String name, Long balance) {
		super();
		this.accountId = accountId;
		this.name = name;
		this.balance = balance;
	}

	public void debit(Long amount) {
		balance -= amount;
	}

	public void credit(Long amount) {
		balance += amount;
	}

	public Long getAccountId() {
		return accountId;
	}

	public Long getBalance() {
		return balance;
	}
	

	public String getName() {
		return name;
	}

	public static Account toAccount(AccountDto accountDto) {
		return new Account(accountDto.getAccountId(), 
				accountDto.getName(), accountDto.getBalance());
	}
	
	

}
