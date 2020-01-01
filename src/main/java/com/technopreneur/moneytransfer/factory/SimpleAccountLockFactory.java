package com.technopreneur.moneytransfer.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.google.inject.Singleton;

@Singleton
public class SimpleAccountLockFactory implements AccountLockFactory {

	private Map<Long, Lock> accountLocks = new ConcurrentHashMap<>();

	@Override
	public Lock getAccountLock(Long accountId) {
		return accountLocks.computeIfAbsent(accountId, k -> new ReentrantLock());
	}

}
