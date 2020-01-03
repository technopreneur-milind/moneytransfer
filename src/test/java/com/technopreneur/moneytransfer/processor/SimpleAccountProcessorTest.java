package com.technopreneur.moneytransfer.processor;

import com.technopreneur.moneytransfer.domain.Account;
import com.technopreneur.moneytransfer.event.TransactionEventPublisher;
import com.technopreneur.moneytransfer.model.MoneyTransferDetail;
import com.technopreneur.moneytransfer.model.MoneyTransferTask;
import com.technopreneur.moneytransfer.service.AccountService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.concurrent.ExecutorService;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class SimpleAccountProcessorTest {

    @Mock
    private AccountService mockAccountService;

    @Mock
    private TransactionEventPublisher transactionEventPublisher;

    @Mock
    private AccountProcessingStrategy processingStrategy;

    @Mock
    private ExecutorService executorService;

    private SimpleAccountProcessor simpleAccountProcessorUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        when(processingStrategy.getExecutorService()).thenReturn(executorService);
        simpleAccountProcessorUnderTest = new SimpleAccountProcessor(0L, mockAccountService,processingStrategy, transactionEventPublisher);
    }

    @Test
    public void testAddToProcessingQueue() {
        // Setup
        final MoneyTransferDetail moneyTransferDetail = new MoneyTransferDetail();
        moneyTransferDetail.setFromAccount(0L);
        moneyTransferDetail.setToAccount(0L);
        moneyTransferDetail.setAmount(0L);
        final MoneyTransferTask moneyTransferTask = new MoneyTransferTask(moneyTransferDetail, "someString");

        // Run the test
        simpleAccountProcessorUnderTest.addToProcessingQueue(moneyTransferTask);
        Assert.assertTrue(simpleAccountProcessorUnderTest.getTasksSize() ==1);

    }
}
