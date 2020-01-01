package com.technopreneur.moneytransfer.model;

public class MoneyTransferDetail {

	private Long fromAccount;
	
	private Long toAccount;
	
	public Long getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(Long fromAccount) {
		this.fromAccount = fromAccount;
	}

	public Long getToAccount() {
		return toAccount;
	}

	public void setToAccount(Long toAccount) {
		this.toAccount = toAccount;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	private Long amount;

	@Override
	public String toString() {
		return "TransferDetail [fromAccount=" + fromAccount + ", toAccount=" + toAccount + ", amount=" + amount + "]";
	}
}
