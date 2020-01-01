package com.technopreneur.moneytransfer.model;

import java.util.UUID;

public class MoneyTransferTask {

	private MoneyTransferDetail transferDetail;

	private Long transactionId;

	public MoneyTransferTask(MoneyTransferDetail transferDetail, long transactionId) {
		this.transferDetail = transferDetail;
		this.transactionId = transactionId;
	}

	public MoneyTransferDetail getTransferDetail() {
		return transferDetail;
	}

	public void setTransferDetail(MoneyTransferDetail transferDetail) {
		this.transferDetail = transferDetail;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public static MoneyTransferTask toMoneyTransferTask(MoneyTransferDetail transferDetail) {
		return new MoneyTransferTask(transferDetail, generateTransactionId());
	}

	private static Long generateTransactionId() {
		return UUID.randomUUID().getLeastSignificantBits();
	}

	@Override
	public String toString() {
		return "MoneyTransferTask [transferDetail=" + transferDetail + ", transactionId=" + transactionId + "]";
	}
	

}
