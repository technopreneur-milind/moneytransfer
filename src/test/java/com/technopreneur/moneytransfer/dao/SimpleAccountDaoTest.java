package com.technopreneur.moneytransfer.dao;

import com.technopreneur.moneytransfer.domain.Account;
import com.technopreneur.moneytransfer.dto.AccountDto;
import com.technopreneur.moneytransfer.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class SimpleAccountDaoTest {

    @Mock
    private AccountRepository mockAccountRepository;

    @InjectMocks
    private SimpleAccountDao simpleAccountDaoUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testDebitAccount() {
        // Setup
        when(mockAccountRepository.debitAccount(0L, 0L)).thenReturn(true);

        // Run the test
        final boolean result = simpleAccountDaoUnderTest.debitAccount(0L, 0L);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testCreditAccount() {
        // Setup
        when(mockAccountRepository.creditAccount(0L, 0L)).thenReturn(true);

        // Run the test
        final boolean result = simpleAccountDaoUnderTest.creditAccount(0L, 0L);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testCreateAccount() {
        // Setup
        final Account account = new Account(0L, "name", 0L);
        when(mockAccountRepository.createAccount(any(Account.class))).thenReturn(new Account(0L, "name", 0L));

        // Run the test
        final Account result = simpleAccountDaoUnderTest.createAccount(account);

        assertTrue(result.equals(new Account(0L, "name", 0L)));
    }

    @Test
    public void testAccountExist() {
        // Setup
        when(mockAccountRepository.accountExist(any(Long.class))).thenReturn(true);
        // Run the test
        final boolean result = simpleAccountDaoUnderTest.accountExist(0L);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testGetAccounts() {
        // Setup

        // Configure AccountRepository.getAccounts(...).
        final List<Account> accounts = Arrays.asList(new Account(0L, "name", 0L));
        when(mockAccountRepository.getAccounts()).thenReturn(accounts);

        // Run the test
        final List<Account> result = simpleAccountDaoUnderTest.getAccounts();

        result.forEach( e -> assertTrue(e.equals(new Account(0L, "name", 0L))));
    }

    @Test
    public void testGetAccount() {
        // Setup
        when(mockAccountRepository.getAccount(0L)).thenReturn(new Account(0L, "name", 0L));

        // Run the test
        final Account result = simpleAccountDaoUnderTest.getAccount(0L);

        assertTrue(result.equals(new Account(0L, "name", 0L)));
    }
}
