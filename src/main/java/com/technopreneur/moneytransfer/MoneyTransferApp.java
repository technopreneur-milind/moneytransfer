package com.technopreneur.moneytransfer;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.technopreneur.moneytransfer.config.MoneyTransferConfig;
import com.technopreneur.moneytransfer.starters.MoneyAppStarter;
import com.technopreneur.moneytransfer.starters.SimpleMoneyAppStarter;

/**
 * Money Transfer App
 *
 */
public class MoneyTransferApp {

	public static void main(String[] args) {
		start();

	}

	private static void start() {
		Injector injector = Guice.createInjector(new MoneyTransferConfig());
		MoneyAppStarter moneyAppStarter = injector.getInstance(SimpleMoneyAppStarter.class);
		moneyAppStarter.start();
	}
}
