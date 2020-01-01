package com.technopreneur.moneytransfer.starters;

import com.google.inject.Inject;

public class SimpleMoneyAppStarter implements MoneyAppStarter {
	
	@Inject
	private ProcessingStarter processingStarter;
	
	@Inject
	private WebStarter webStarter;

	@Override
	public void start() {
		processingStarter.startProcessing();
		webStarter.startEndpoints();
		
	}

}
