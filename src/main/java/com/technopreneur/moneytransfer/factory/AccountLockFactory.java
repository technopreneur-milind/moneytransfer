package com.technopreneur.moneytransfer.factory;

import java.util.concurrent.locks.Lock;

public interface AccountLockFactory {
	public Lock getAccountLock(Long accountId);

}
