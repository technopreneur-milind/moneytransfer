package com.technopreneur.moneytransfer.store;

import com.technopreneur.moneytransfer.event.TransactionEventPublisher;
import com.technopreneur.moneytransfer.model.MoneyTransferDetail;
import com.technopreneur.moneytransfer.model.MoneyTransferTask;
import com.technopreneur.moneytransfer.processor.AccountProcessor;
import com.technopreneur.moneytransfer.processor.OrderedAccountProcessingStrategy;
import com.technopreneur.moneytransfer.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.concurrent.ExecutorService;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class InMemoryMoneyTransferStoreTest {

    @Mock
    private AccountService mockAccountService;

    @Mock
    private TransactionEventPublisher transactionEventPublisher;

    @Mock
    private OrderedAccountProcessingStrategy accountProcessingStrategy;

    @Mock
    private ExecutorService executorService;

    @InjectMocks
    private InMemoryMoneyTransferStore inMemoryMoneyTransferStoreUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        when(accountProcessingStrategy.getExecutorService()).thenReturn(executorService);
    }

    @Test
    public void testGetAccountProcessor() {
        // Setup
        final MoneyTransferDetail moneyTransferDetail = new MoneyTransferDetail();
        moneyTransferDetail.setFromAccount(0L);
        moneyTransferDetail.setToAccount(0L);
        moneyTransferDetail.setAmount(0L);
        final MoneyTransferTask moneyTransferTask = new MoneyTransferTask(moneyTransferDetail, "SomeString");

        // Run the test
        final AccountProcessor result = inMemoryMoneyTransferStoreUnderTest.getAccountProcessor(moneyTransferTask);

        // Verify the results
    }
}
