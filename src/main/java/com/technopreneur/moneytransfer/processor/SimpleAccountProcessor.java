package com.technopreneur.moneytransfer.processor;

import com.technopreneur.moneytransfer.event.EventType;
import com.technopreneur.moneytransfer.event.TransactionEvent;
import com.technopreneur.moneytransfer.event.TransactionEventPublisher;
import com.technopreneur.moneytransfer.model.MoneyTransferTask;
import com.technopreneur.moneytransfer.service.AccountService;

import javax.inject.Inject;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleAccountProcessor implements AccountProcessor {

	private Long accountId;

	private AtomicBoolean process = new AtomicBoolean(true);

	private BlockingQueue<MoneyTransferTask> moneyTransferTasks = new LinkedBlockingQueue<>();

	private TransactionEventPublisher eventPublisher;

	private AccountService accountService;

	private AccountProcessingStrategy accountProcessingStrategy;

	public SimpleAccountProcessor() {}
	
	public SimpleAccountProcessor(Long accountId, AccountService accountService,
								  AccountProcessingStrategy accountProcessingStrategy, TransactionEventPublisher eventPublisher) {
		this.accountId = accountId;
		this.accountService = accountService;
		this.eventPublisher = eventPublisher;
		this.accountProcessingStrategy = accountProcessingStrategy;
		process();
	}

	public int getTasksSize()
	{
		return moneyTransferTasks.size();
	}

	@Override
	public void addToProcessingQueue(MoneyTransferTask moneyTransferTask) {
		moneyTransferTasks.add(moneyTransferTask);
	}
	
	private void process() {
		ExecutorService accountProcessingService = accountProcessingStrategy.getExecutorService();
		accountProcessingService.submit(new TaskExecutor());
	}

	private void publishEvent(String transactionId, EventType eventType)
	{
		eventPublisher.publishEvent(new TransactionEvent(transactionId, eventType));
	}

	class TaskExecutor implements Runnable {

		public void processTasks() {
			while (process.get()) {
				MoneyTransferTask moneyTransferTask = null;
				try {
					moneyTransferTask = moneyTransferTasks.take();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
				}
				if (moneyTransferTask != null) {
					debit(moneyTransferTask);
					credit(moneyTransferTask);
				}
			}
		}

		private void credit(MoneyTransferTask moneyTransferTask) {
			final String transactionId = moneyTransferTask.getTransactionId();
			Long toAccountId = moneyTransferTask.getTransferDetail().getToAccount();
			ExecutorService executorService = Executors.newSingleThreadExecutor();
			executorService.submit(new CreditAccountExecutor(toAccountId, moneyTransferTask.getTransferDetail().getAmount(), transactionId));
		}

		private void debit(MoneyTransferTask moneyTransferTask) {
			final String transactionId = moneyTransferTask.getTransactionId();
			//publishEvent(transactionId, EventType.TRANSACTION_STARTED_EVENT);

			publishEvent(transactionId, EventType.TRANSACTION_DEBIT_STARTED_EVENT);
			accountService.debitAccount(accountId, moneyTransferTask.getTransferDetail().getAmount());
			publishEvent(transactionId, EventType.TRANSACTION_DEBIT_COMPLETED_EVENT);
		}

		@Override
		public void run() {
			processTasks();

		}
	}

	class CreditAccountExecutor implements Runnable {

		private Long accountId;
		private Long amount;
		private String transactionId;

		public CreditAccountExecutor(Long accountId, Long amount, String transactionId) {
			this.accountId = accountId;
			this.amount = amount;
			this.transactionId = transactionId;
		}

		@Override
		public void run() {
			credit();
		}

		private void credit() {
			publishEvent(transactionId, EventType.TRANSACTION_CREDIT_STARTED_EVENT);
			accountService.creditAccount(accountId, amount);
			publishEvent(transactionId, EventType.TRANSACTION_COMPLETED_EVENT);
		}

	}

}
