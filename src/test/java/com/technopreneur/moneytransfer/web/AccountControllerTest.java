package com.technopreneur.moneytransfer.web;

import com.technopreneur.moneytransfer.dto.AccountDto;
import com.technopreneur.moneytransfer.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AccountControllerTest {

    @Mock
    private AccountService mockAccountService;

    @InjectMocks
    private AccountController accountControllerUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testAddEndPoints() {
        // Setup
        when(mockAccountService.createAccount(any(AccountDto.class))).thenReturn(new AccountDto(0L, "name", 0L));

        // Configure AccountService.getAccounts(...).
        final List<AccountDto> accountDtos = Arrays.asList(new AccountDto(0L, "name", 0L));
        when(mockAccountService.getAccounts()).thenReturn(accountDtos);

        when(mockAccountService.getAccount(0L)).thenReturn(new AccountDto(0L, "name", 0L));

        // Run the test
        accountControllerUnderTest.addEndPoints();

        // Verify the results
    }
}
