package com.technopreneur.moneytransfer.web;

import com.technopreneur.moneytransfer.exception.MoneyTransferValidationException;
import com.technopreneur.moneytransfer.model.MoneyTransferDetail;
import com.technopreneur.moneytransfer.service.MoneyTransferService;
import com.technopreneur.moneytransfer.validate.MoneyAppValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class MoneyTransferControllerTest {

    @Mock
    private MoneyTransferService mockMoneyTransferService;
    @Mock
    private MoneyAppValidator mockValidator;

    @InjectMocks
    private MoneyTransferController moneyTransferControllerUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testAddEndPoints() throws MoneyTransferValidationException {
        // Setup

        // Run the test
        moneyTransferControllerUnderTest.addEndPoints();

        // Verify the results
    }

}
