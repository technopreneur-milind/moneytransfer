package com.technopreneur.moneytransfer.processor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import com.google.inject.Inject;
import com.technopreneur.moneytransfer.model.MoneyTransferTask;
import com.technopreneur.moneytransfer.service.AccountService;

public class SimpleAccountProcessor implements AccountProcessor {

	private Long accountId;

	private AtomicBoolean process = new AtomicBoolean(true);

	private BlockingQueue<MoneyTransferTask> moneyTransferTasks = new LinkedBlockingQueue<>();

	public SimpleAccountProcessor() {}
	
	public SimpleAccountProcessor(Long accountId, AccountService accountService) {
		this.accountId = accountId;
		process(accountService);
		
	}

	@Override
	public void addToProcessingQueue(MoneyTransferTask moneyTransferTask) {
		moneyTransferTasks.add(moneyTransferTask);
	}
	
	@Override
	public void process(AccountService accountService) {
		new Thread(new TaskExecutor(accountService)).start();
	}

	class TaskExecutor implements Runnable {

		private AccountService accountService;

		public TaskExecutor(AccountService accountService) {
			this.accountService = accountService;
		}

		public void processTasks() {
			while (process.get()) {
				MoneyTransferTask moneyTransferTask = null;
				try {
					moneyTransferTask = moneyTransferTasks.take();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (moneyTransferTask != null) {
					accountService.debitAccount(accountId, moneyTransferTask.getTransferDetail().getAmount());
					Long toAccountId = moneyTransferTask.getTransferDetail().getToAccount();
					new Thread(new CreditAccountExecutor(toAccountId, moneyTransferTask.getTransferDetail().getAmount(),accountService))
							.start();
				}
			}
		}

		@Override
		public void run() {
			processTasks();

		}
	}

	class CreditAccountExecutor implements Runnable {

		private Long accountId;
		private Long amount;
		private AccountService accountService;

		public CreditAccountExecutor(Long accountId, Long amount, AccountService accountService) {
			this.accountId = accountId;
			this.amount = amount;
			this.accountService = accountService;
		}

		@Override
		public void run() {
			accountService.creditAccount(accountId, amount);

		}

	}

}
