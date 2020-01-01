package com.technopreneur.moneytransfer;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.technopreneur.moneytransfer.config.MoneyTransferConfig;
import com.technopreneur.moneytransfer.queue.SimpleMoneyTransferQueueProcessor;
import com.technopreneur.moneytransfer.web.BaseController;
import com.technopreneur.moneytransfer.web.MoneyTransferController;

/**
 * Money Transfer App
 *
 */
public class MoneyTransferApp {

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new MoneyTransferConfig());
		BaseController moneyTransferController = injector.getInstance(MoneyTransferController.class);
		moneyTransferController.addEndPoints();
		SimpleMoneyTransferQueueProcessor moneyTransferQueueProcessor = injector.getInstance(SimpleMoneyTransferQueueProcessor.class);
		new Thread(moneyTransferQueueProcessor).start();
	}
}
