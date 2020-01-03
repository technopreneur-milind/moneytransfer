package com.technopreneur.moneytransfer.service;

import com.technopreneur.moneytransfer.event.TransactionEventPublisher;
import com.technopreneur.moneytransfer.model.MoneyTransferDetail;
import com.technopreneur.moneytransfer.model.MoneyTransferTask;
import com.technopreneur.moneytransfer.queue.MoneyTransferQueue;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class SimpleMoneyTransferServiceTest {

    @Mock
    private MoneyTransferQueue mockMoneyTransferQueue;

    @Mock
    private TransactionEventPublisher eventPublisher;

    @InjectMocks
    private SimpleMoneyTransferService simpleMoneyTransferServiceUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testTransfer() {
        // Setup
        final MoneyTransferDetail transferDetail = new MoneyTransferDetail();
        transferDetail.setFromAccount(0L);
        transferDetail.setToAccount(0L);
        transferDetail.setAmount(0L);

        // Run the test
        simpleMoneyTransferServiceUnderTest.transfer(transferDetail);

        // Verify the results
        verify(mockMoneyTransferQueue).add(any(MoneyTransferTask.class));
    }
}
