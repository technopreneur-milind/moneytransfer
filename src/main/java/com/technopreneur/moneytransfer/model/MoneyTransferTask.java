package com.technopreneur.moneytransfer.model;

import java.util.UUID;

public class MoneyTransferTask {

	private MoneyTransferDetail transferDetail;

	private String transactionId;

	public MoneyTransferTask(MoneyTransferDetail transferDetail, String transactionId) {
		this.transferDetail = transferDetail;
		this.transactionId = transactionId;
	}

	public MoneyTransferDetail getTransferDetail() {
		return transferDetail;
	}

	public void setTransferDetail(MoneyTransferDetail transferDetail) {
		this.transferDetail = transferDetail;
	}

	public String getTransactionId() {
		return transactionId;
	}


	public static MoneyTransferTask toMoneyTransferTask(MoneyTransferDetail transferDetail) {
		return new MoneyTransferTask(transferDetail, generateTransactionId());
	}

	private static String generateTransactionId() {
		return UUID.randomUUID().toString();
	}

	@Override
	public String toString() {
		return "MoneyTransferTask [transferDetail=" + transferDetail + ", transactionId=" + transactionId + "]";
	}
	

}
