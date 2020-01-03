package com.technopreneur.moneytransfer.validate;

import com.technopreneur.moneytransfer.exception.MoneyTransferValidationException;
import com.technopreneur.moneytransfer.model.MoneyTransferDetail;
import org.junit.Before;
import org.junit.Test;

public class SimpleMoneyAppValidatorTest {

    private SimpleMoneyAppValidator simpleMoneyAppValidatorUnderTest;

    @Before
    public void setUp() {
        simpleMoneyAppValidatorUnderTest = new SimpleMoneyAppValidator();
    }

    @Test
    public void testValidateMoneyTransferDetail() throws Exception {
        // Setup
        final MoneyTransferDetail moneyTransferDetail = new MoneyTransferDetail();
        moneyTransferDetail.setFromAccount(0L);
        moneyTransferDetail.setToAccount(0L);
        moneyTransferDetail.setAmount(0L);

        // Run the test
        simpleMoneyAppValidatorUnderTest.validateMoneyTransferDetail(moneyTransferDetail);

        // Verify the results
    }

    //@Test(expected = MoneyTransferValidationException.class)
    public void testValidateMoneyTransferDetail_ThrowsMoneyTransferValidationException() throws Exception {
        // Setup
        final MoneyTransferDetail moneyTransferDetail = new MoneyTransferDetail();
        moneyTransferDetail.setFromAccount(0L);
        moneyTransferDetail.setToAccount(0L);
        moneyTransferDetail.setAmount(0L);

        // Run the test
        simpleMoneyAppValidatorUnderTest.validateMoneyTransferDetail(moneyTransferDetail);
    }
}
