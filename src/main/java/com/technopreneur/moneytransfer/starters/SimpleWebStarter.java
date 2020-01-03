package com.technopreneur.moneytransfer.starters;

import com.google.inject.Inject;
import com.technopreneur.moneytransfer.web.AccountController;
import com.technopreneur.moneytransfer.web.MoneyTransferController;

public class SimpleWebStarter implements WebStarter {

	@Inject
	private MoneyTransferController moneyTransferController;

	@Inject
	private AccountController accountController;

	@Override
	public void startEndpoints() {
		moneyTransferController.addEndPoints();
		accountController.addEndPoints();
	}

}
