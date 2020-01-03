package com.technopreneur.moneytransfer.queue;

import com.technopreneur.moneytransfer.model.MoneyTransferDetail;
import com.technopreneur.moneytransfer.model.MoneyTransferTask;
import org.junit.Before;
import org.junit.Test;

public class InMemoryMoneyTransferQueueTest {

    private InMemoryMoneyTransferQueue inMemoryMoneyTransferQueueUnderTest;

    @Before
    public void setUp() {
        inMemoryMoneyTransferQueueUnderTest = new InMemoryMoneyTransferQueue();
    }

    @Test
    public void testAdd() {
        // Setup
        final MoneyTransferDetail moneyTransferDetail = new MoneyTransferDetail();
        moneyTransferDetail.setFromAccount(0L);
        moneyTransferDetail.setToAccount(0L);
        moneyTransferDetail.setAmount(0L);
        final MoneyTransferTask moneyTransferTask = new MoneyTransferTask(moneyTransferDetail, "SomeString");

        // Run the test
        inMemoryMoneyTransferQueueUnderTest.add(moneyTransferTask);

        // Verify the results
    }

    //@Test
    public void testGetNext() throws Exception {
        // Setup

        // Run the test
        final MoneyTransferTask result = inMemoryMoneyTransferQueueUnderTest.getNext();

        // Verify the results
    }

    //@Test(expected = InterruptedException.class)
    public void testGetNext_ThrowsInterruptedException() throws Exception {
        // Setup

        // Run the test
        inMemoryMoneyTransferQueueUnderTest.getNext();
    }
}
