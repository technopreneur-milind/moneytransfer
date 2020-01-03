package com.technopreneur.moneytransfer.starters;

import com.technopreneur.moneytransfer.queue.SimpleMoneyTransferQueueProcessor;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class SimpleProcessingStarterTest {

    private SimpleProcessingStarter simpleProcessingStarterUnderTest;

    @Before
    public void setUp() {
        simpleProcessingStarterUnderTest = new SimpleProcessingStarter();
        simpleProcessingStarterUnderTest.moneyTransferQueueProcessor = mock(SimpleMoneyTransferQueueProcessor.class);
    }

    @Test
    public void testStartProcessing() {
        // Setup

        // Run the test
        simpleProcessingStarterUnderTest.startProcessing();

        // Verify the results
    }
}
