package com.technopreneur.moneytransfer.config;

import com.google.inject.AbstractModule;
import com.technopreneur.moneytransfer.dao.AccountDao;
import com.technopreneur.moneytransfer.dao.SimpleAccountDao;
import com.technopreneur.moneytransfer.dao.SimpleTransactionStatusDao;
import com.technopreneur.moneytransfer.dao.TransactionStatusDao;
import com.technopreneur.moneytransfer.event.EventPublisher;
import com.technopreneur.moneytransfer.event.SimpleEventPublisher;
import com.technopreneur.moneytransfer.event.SimpleTransactionEventPublisher;
import com.technopreneur.moneytransfer.event.TransactionEventPublisher;
import com.technopreneur.moneytransfer.factory.AccountLockFactory;
import com.technopreneur.moneytransfer.factory.SimpleAccountLockFactory;
import com.technopreneur.moneytransfer.processor.AccountProcessingStrategy;
import com.technopreneur.moneytransfer.processor.OrderedAccountProcessingStrategy;
import com.technopreneur.moneytransfer.queue.InMemoryMoneyTransferQueue;
import com.technopreneur.moneytransfer.queue.MoneyTransferQueue;
import com.technopreneur.moneytransfer.queue.MoneyTransferQueueProcessor;
import com.technopreneur.moneytransfer.queue.SimpleMoneyTransferQueueProcessor;
import com.technopreneur.moneytransfer.repository.AccountRepository;
import com.technopreneur.moneytransfer.repository.SimpleAccountRepository;
import com.technopreneur.moneytransfer.service.*;
import com.technopreneur.moneytransfer.starters.ProcessingStarter;
import com.technopreneur.moneytransfer.starters.SimpleProcessingStarter;
import com.technopreneur.moneytransfer.starters.SimpleWebStarter;
import com.technopreneur.moneytransfer.starters.WebStarter;
import com.technopreneur.moneytransfer.store.InMemoryMoneyTransferStore;
import com.technopreneur.moneytransfer.store.InMemoryTransactionStatusStore;
import com.technopreneur.moneytransfer.store.MoneyTransferAccountStore;
import com.technopreneur.moneytransfer.store.TransactionStatusStore;
import com.technopreneur.moneytransfer.validate.MoneyAppValidator;
import com.technopreneur.moneytransfer.validate.SimpleMoneyAppValidator;
import com.technopreneur.moneytransfer.web.BaseController;
import com.technopreneur.moneytransfer.web.MoneyTransferController;

public class MoneyTransferConfig extends AbstractModule {

	@Override
	protected void configure() {
		bind(AccountDao.class).to(SimpleAccountDao.class);
		bind(AccountLockFactory.class).to(SimpleAccountLockFactory.class);
		bind(AccountRepository.class).to(SimpleAccountRepository.class);
		bind(AccountService.class).to(SimpleAccountService.class);
		bind(BaseController.class).to(MoneyTransferController.class);
		bind(MoneyTransferQueue.class).to(InMemoryMoneyTransferQueue.class);
		bind(MoneyTransferQueueProcessor.class).to(SimpleMoneyTransferQueueProcessor.class);
		bind(MoneyTransferService.class).to(SimpleMoneyTransferService.class);
		bind(MoneyTransferAccountStore.class).to(InMemoryMoneyTransferStore.class);
		bind(ProcessingStarter.class).to(SimpleProcessingStarter.class);
		bind(WebStarter.class).to(SimpleWebStarter.class);
		bind(MoneyAppValidator.class).to(SimpleMoneyAppValidator.class);
		bind(TransactionStatusStore.class).to(InMemoryTransactionStatusStore.class);
		bind(EventPublisher.class).to(SimpleEventPublisher.class);
		bind(TransactionEventPublisher.class).to(SimpleTransactionEventPublisher.class);
		bind(TransactionStatusDao.class).to(SimpleTransactionStatusDao.class);
		bind(TransactionStatusService.class).to(SimpleTransactionStatusService.class);
		bind(AccountProcessingStrategy.class).to(OrderedAccountProcessingStrategy.class);
	}

}
