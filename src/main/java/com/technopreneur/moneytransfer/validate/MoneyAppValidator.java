package com.technopreneur.moneytransfer.validate;

import com.technopreneur.moneytransfer.exception.MoneyTransferValidationException;
import com.technopreneur.moneytransfer.model.MoneyTransferDetail;

public interface MoneyAppValidator {
	
	public void validateMoneyTransferDetail(MoneyTransferDetail moneyTransferDetail) throws MoneyTransferValidationException;

}
