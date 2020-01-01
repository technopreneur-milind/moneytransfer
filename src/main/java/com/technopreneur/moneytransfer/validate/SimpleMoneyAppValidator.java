package com.technopreneur.moneytransfer.validate;

import com.technopreneur.moneytransfer.exception.MoneyTransferValidationException;
import com.technopreneur.moneytransfer.model.MoneyTransferDetail;

public class SimpleMoneyAppValidator implements MoneyAppValidator {

	@Override
	public void validateMoneyTransferDetail(MoneyTransferDetail moneyTransferDetail)
			throws MoneyTransferValidationException {
		checkNull(moneyTransferDetail);
		validateField(moneyTransferDetail.getFromAccount(), "from AccountId");
		validateField(moneyTransferDetail.getToAccount(), "to AccountId");
		validateField(moneyTransferDetail.getAmount(), "Amount");
	}

	private void validateField(Long accountId, String name) throws MoneyTransferValidationException {
		if (accountId == null || accountId <= 0) {
			throw new MoneyTransferValidationException(name + " is invalid");
		}
	}

	private void checkNull(MoneyTransferDetail moneyTransferDetail) throws MoneyTransferValidationException {
		if (moneyTransferDetail == null) {
			throw new MoneyTransferValidationException("Invalid Input");
		}
	}

}
