package com.technopreneur.moneytransfer.model;

public class Account {
	private Long accountId;

	private String name;

	private Long balance = 0L;

	private Boolean isActive = true;
	
	

	public Account(Long accountId, Long balance) {
		this.accountId = accountId;
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
	

}
