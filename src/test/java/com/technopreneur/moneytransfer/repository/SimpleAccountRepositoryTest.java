package com.technopreneur.moneytransfer.repository;

import com.technopreneur.moneytransfer.domain.Account;
import com.technopreneur.moneytransfer.factory.AccountLockFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class SimpleAccountRepositoryTest {

    @Mock
    private AccountLockFactory mockAccountLockFactory;

    @Mock
    private Lock lock;

    @Mock
    private Map<Long, Account> accounts;

    @Mock
    private Account account;

    @InjectMocks
    private SimpleAccountRepository simpleAccountRepositoryUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testInit() {
        // Setup

        // Run the test
        simpleAccountRepositoryUnderTest.init();

        // Verify the results
    }

    @Test
    public void testDebitAccount() {
        // Setup
        when(mockAccountLockFactory.getAccountLock(0L)).thenReturn(lock);
        when(accounts.get(0L)).thenReturn(account);

        // Run the test
        final boolean result = simpleAccountRepositoryUnderTest.debitAccount(0L, 0L);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testCreditAccount() {
        // Setup
        when(mockAccountLockFactory.getAccountLock(0L)).thenReturn(lock);
        when(accounts.get(0L)).thenReturn(account);

        // Run the test
        final boolean result = simpleAccountRepositoryUnderTest.creditAccount(0L, 0L);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testCreateAccount() {
        // Setup
        final Account account = new Account(0L, "name", 0L);

        // Run the test
        final Account result = simpleAccountRepositoryUnderTest.createAccount(account);

        // Verify the results
    }

    @Test
    public void testAccountExist() {
        // Setup
        when(accounts.containsKey(any(Long.class))).thenReturn(true);
        // Run the test
        final boolean result = simpleAccountRepositoryUnderTest.accountExist(0L);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testGetAccounts() {
        // Setup

        // Run the test
        final List<Account> result = simpleAccountRepositoryUnderTest.getAccounts();

        // Verify the results
    }

    @Test
    public void testGetAccount() {
        // Setup

        // Run the test
        final Account result = simpleAccountRepositoryUnderTest.getAccount(0L);

        // Verify the results
    }
}
