package com.technopreneur.moneytransfer.factory;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.locks.Lock;

public class SimpleAccountLockFactoryTest {

    private SimpleAccountLockFactory simpleAccountLockFactoryUnderTest;

    @Before
    public void setUp() {
        simpleAccountLockFactoryUnderTest = new SimpleAccountLockFactory();
    }

    @Test
    public void testGetAccountLock() {
        // Setup

        // Run the test
        final Lock result = simpleAccountLockFactoryUnderTest.getAccountLock(0L);

        // Verify the results
    }
}
