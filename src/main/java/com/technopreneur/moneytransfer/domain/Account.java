package com.technopreneur.moneytransfer.domain;

import com.technopreneur.moneytransfer.dto.AccountDto;

import java.util.Objects;

public class Account {
	private Long accountId;

	private String name;

	private Long balance = 0L;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Account account = (Account) o;
		return accountId.equals(account.accountId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountId);
	}

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
