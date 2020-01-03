package com.technopreneur.moneytransfer.validate;

import com.technopreneur.moneytransfer.exception.MoneyTransferValidationException;
import com.technopreneur.moneytransfer.model.MoneyTransferBatchDetail;
import com.technopreneur.moneytransfer.model.MoneyTransferDetail;

public class SimpleMoneyAppValidator implements MoneyAppValidator {

	@Override
	public void validateMoneyTransferDetail(MoneyTransferDetail moneyTransferDetail)
			throws MoneyTransferValidationException {
		//TODO: Implement this
	}

	@Override
	public void validateMoneyTransferDetail(MoneyTransferBatchDetail moneyTransferBatchDetail) throws MoneyTransferValidationException {
		//TODO: Implement this
	}

}
