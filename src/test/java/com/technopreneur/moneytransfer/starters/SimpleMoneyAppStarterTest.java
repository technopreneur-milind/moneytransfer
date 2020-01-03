package com.technopreneur.moneytransfer.starters;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class SimpleMoneyAppStarterTest {

    @Mock
    private ProcessingStarter mockProcessingStarter;
    @Mock
    private WebStarter mockWebStarter;

    @InjectMocks
    private SimpleMoneyAppStarter simpleMoneyAppStarterUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testStart() {
        // Setup

        // Run the test
        simpleMoneyAppStarterUnderTest.start();

        // Verify the results
        verify(mockProcessingStarter).startProcessing();
        verify(mockWebStarter).startEndpoints();
    }
}
