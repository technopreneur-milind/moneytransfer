package com.technopreneur.moneytransfer.service;

import com.technopreneur.moneytransfer.event.EventType;
import com.technopreneur.moneytransfer.event.TransactionEvent;
import com.technopreneur.moneytransfer.event.TransactionEventPublisher;
import com.technopreneur.moneytransfer.model.MoneyTransferBatchDetail;
import com.technopreneur.moneytransfer.model.MoneyTransferDetail;
import com.technopreneur.moneytransfer.model.MoneyTransferTask;
import com.technopreneur.moneytransfer.queue.MoneyTransferQueue;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleMoneyTransferService implements MoneyTransferService {

	@Inject
	private MoneyTransferQueue moneyTransferQueue;

	@Inject
	private TransactionEventPublisher eventPublisher;

	@Override
	public String transfer(MoneyTransferDetail transferDetail) {
		MoneyTransferTask moneyTransferTask = MoneyTransferTask.toMoneyTransferTask(transferDetail);
		String transactionId = moneyTransferTask.getTransactionId();
		this.eventPublisher.publishEvent(new TransactionEvent(transactionId, EventType.TRANSACTION_QUEUED_EVENT));
		this.moneyTransferQueue.add(moneyTransferTask);
		return transactionId;
	}

	@Override
	public List<String> batchTransfer(MoneyTransferBatchDetail transferBatchDetail) {
		return transferBatchDetail.getMoneyTransferDetails().stream().map(this::transfer).collect(Collectors.toList());
	}
}
