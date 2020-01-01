package com.technopreneur.moneytransfer.service;

import javax.inject.Inject;

import com.technopreneur.moneytransfer.model.MoneyTransferDetail;
import com.technopreneur.moneytransfer.model.MoneyTransferTask;
import com.technopreneur.moneytransfer.queue.MoneyTransferQueue;

public class SimpleMoneyTransferService implements MoneyTransferService {

	@Inject
	private MoneyTransferQueue moneyTransferQueue;

	@Override
	public void transfer(MoneyTransferDetail transferDetail) {
		System.out.println("Inside " + this.getClass().getName());
		System.out.println(transferDetail.toString());
		// validate transferdetail
		MoneyTransferTask moneyTransferTask = MoneyTransferTask.toMoneyTransferTask(transferDetail);
		this.moneyTransferQueue.add(moneyTransferTask);
	}
}
