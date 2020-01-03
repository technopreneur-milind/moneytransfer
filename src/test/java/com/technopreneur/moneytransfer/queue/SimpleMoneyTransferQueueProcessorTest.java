package com.technopreneur.moneytransfer.queue;

import com.technopreneur.moneytransfer.model.MoneyTransferDetail;
import com.technopreneur.moneytransfer.model.MoneyTransferTask;
import com.technopreneur.moneytransfer.store.MoneyTransferAccountStore;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class SimpleMoneyTransferQueueProcessorTest {

    @Mock
    private MoneyTransferQueue mockMoneyTransferQueue;
    @Mock
    private MoneyTransferAccountStore mockMoneyTransferAccountStore;

    @InjectMocks
    private SimpleMoneyTransferQueueProcessor simpleMoneyTransferQueueProcessorUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
    }

    //@Test
    public void testProcess() throws Exception {
        // Setup

        // Configure MoneyTransferQueue.getNext(...).
        final MoneyTransferDetail moneyTransferDetail = new MoneyTransferDetail();
        moneyTransferDetail.setFromAccount(0L);
        moneyTransferDetail.setToAccount(0L);
        moneyTransferDetail.setAmount(0L);
        final MoneyTransferTask moneyTransferTask = new MoneyTransferTask(moneyTransferDetail, "SomeString");
        when(mockMoneyTransferQueue.getNext()).thenReturn(moneyTransferTask);

        when(mockMoneyTransferAccountStore.getAccountProcessor(any(MoneyTransferTask.class))).thenReturn(null);

        // Run the test
        simpleMoneyTransferQueueProcessorUnderTest.process();

        // Verify the results
    }

    //@Test
    public void testProcess_MoneyTransferQueueThrowsInterruptedException() throws Exception {
        // Setup
        when(mockMoneyTransferQueue.getNext()).thenThrow(InterruptedException.class);
        when(mockMoneyTransferAccountStore.getAccountProcessor(any(MoneyTransferTask.class))).thenReturn(null);

        // Run the test
        simpleMoneyTransferQueueProcessorUnderTest.process();

        // Verify the results
    }

    //@Test
    public void testRun() throws Exception {
        // Setup

        // Configure MoneyTransferQueue.getNext(...).
        final MoneyTransferDetail moneyTransferDetail = new MoneyTransferDetail();
        moneyTransferDetail.setFromAccount(0L);
        moneyTransferDetail.setToAccount(0L);
        moneyTransferDetail.setAmount(0L);
        final MoneyTransferTask moneyTransferTask = new MoneyTransferTask(moneyTransferDetail, "SomeString");
        when(mockMoneyTransferQueue.getNext()).thenReturn(moneyTransferTask);

        when(mockMoneyTransferAccountStore.getAccountProcessor(any(MoneyTransferTask.class))).thenReturn(null);

        // Run the test
        simpleMoneyTransferQueueProcessorUnderTest.run();

        // Verify the results
    }

    //@Test
    public void testRun_MoneyTransferQueueThrowsInterruptedException() throws Exception {
        // Setup
        when(mockMoneyTransferQueue.getNext()).thenThrow(InterruptedException.class);
        when(mockMoneyTransferAccountStore.getAccountProcessor(any(MoneyTransferTask.class))).thenReturn(null);

        // Run the test
        simpleMoneyTransferQueueProcessorUnderTest.run();

        // Verify the results
    }
}
