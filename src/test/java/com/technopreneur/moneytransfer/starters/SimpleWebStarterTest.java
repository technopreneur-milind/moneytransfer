package com.technopreneur.moneytransfer.starters;

import com.technopreneur.moneytransfer.web.AccountController;
import com.technopreneur.moneytransfer.web.MoneyTransferController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class SimpleWebStarterTest {

    @Mock
    private MoneyTransferController mockMoneyTransferController;
    @Mock
    private AccountController mockAccountController;

    @InjectMocks
    private SimpleWebStarter simpleWebStarterUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testStartEndpoints() {
        // Setup

        // Run the test
        simpleWebStarterUnderTest.startEndpoints();

        // Verify the results
        verify(mockMoneyTransferController).addEndPoints();
        verify(mockAccountController).addEndPoints();
    }
}
