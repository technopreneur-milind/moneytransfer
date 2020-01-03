package com.technopreneur.moneytransfer.dto;

import com.technopreneur.moneytransfer.domain.Account;

import java.util.Objects;

public class AccountDto {
	private Long accountId;

	private String name;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AccountDto that = (AccountDto) o;
		return accountId.equals(that.accountId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountId);
	}

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
