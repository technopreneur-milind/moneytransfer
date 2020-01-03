package com.technopreneur.moneytransfer.service;

import com.technopreneur.moneytransfer.dao.AccountDao;
import com.technopreneur.moneytransfer.domain.Account;
import com.technopreneur.moneytransfer.dto.AccountDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class SimpleAccountServiceTest {

    @Mock
    private AccountDao mockAccountDao;

    @InjectMocks
    private SimpleAccountService simpleAccountServiceUnderTest;

    private final Long DUMMY_ACCOUNT = 1L;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testDebitAccount_false() {
        // Setup
        when(mockAccountDao.debitAccount(DUMMY_ACCOUNT, 100L)).thenReturn(false);

        // Run the test
        final boolean result = simpleAccountServiceUnderTest.debitAccount(DUMMY_ACCOUNT, 100L);

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testDebitAccount_true(){
        // Setup
        when(mockAccountDao.debitAccount(DUMMY_ACCOUNT, 100L)).thenReturn(true);

        // Run the test
        final boolean result = simpleAccountServiceUnderTest.debitAccount(DUMMY_ACCOUNT, 100L);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testCreditAccount_false() {
        // Setup
        when(mockAccountDao.creditAccount(DUMMY_ACCOUNT, 100L)).thenReturn(false);

        // Run the test
        final boolean result = simpleAccountServiceUnderTest.creditAccount(DUMMY_ACCOUNT, 100L);

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testCreditAccount_true() {
        // Setup
        when(mockAccountDao.creditAccount(DUMMY_ACCOUNT, 100L)).thenReturn(true);

        // Run the test
        final boolean result = simpleAccountServiceUnderTest.creditAccount(DUMMY_ACCOUNT, 100L);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testCreateAccount() {
        // Setup
        final AccountDto accountDto = new AccountDto(DUMMY_ACCOUNT, "name", 100L);
        when(mockAccountDao.createAccount(any(Account.class))).thenReturn(new Account(DUMMY_ACCOUNT, "name", 100L));

        // Run the test
        final AccountDto result = simpleAccountServiceUnderTest.createAccount(accountDto);

        assertTrue(result.equals(new AccountDto(DUMMY_ACCOUNT, "name", 100L)));
    }

    @Test
    public void testAccountExist_false() {
        // Setup
        when(mockAccountDao.accountExist(DUMMY_ACCOUNT)).thenReturn(false);

        // Run the test
        final boolean result = simpleAccountServiceUnderTest.accountExist(DUMMY_ACCOUNT);

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testAccountExist_true() {
        // Setup
        when(mockAccountDao.accountExist(DUMMY_ACCOUNT)).thenReturn(true);

        // Run the test
        final boolean result = simpleAccountServiceUnderTest.accountExist(DUMMY_ACCOUNT);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testGetAccounts() {
        // Setup

        // Configure AccountDao.getAccounts(...).
        final List<Account> accounts = Arrays.asList(new Account(DUMMY_ACCOUNT, "name", 100L));
        when(mockAccountDao.getAccounts()).thenReturn(accounts);

        // Run the test
        final List<AccountDto> result = simpleAccountServiceUnderTest.getAccounts();

        result.forEach( e -> assertTrue(e.equals(new AccountDto(DUMMY_ACCOUNT, "name", 100L))));
    }

    @Test
    public void testGetAccount() {
        // Setup
        when(mockAccountDao.getAccount(DUMMY_ACCOUNT)).thenReturn(new Account(DUMMY_ACCOUNT, "name", 100L));

        // Run the test
        final AccountDto result = simpleAccountServiceUnderTest.getAccount(DUMMY_ACCOUNT);

        assertTrue(result.equals(new AccountDto(DUMMY_ACCOUNT, "name", 100L)));
    }

}
