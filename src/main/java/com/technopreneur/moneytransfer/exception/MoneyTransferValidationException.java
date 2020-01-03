package com.technopreneur.moneytransfer.exception;

public class MoneyTransferValidationException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public MoneyTransferValidationException(String message) {
		super(message);
	}
	
	public MoneyTransferValidationException(String message, Throwable cause) {
        super(message, cause);
    }
	

}
