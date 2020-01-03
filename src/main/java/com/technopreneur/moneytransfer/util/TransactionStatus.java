package com.technopreneur.moneytransfer.util;

public enum TransactionStatus {
    QUEUED("Transaction is Queued for Processing"),
    INPROCESS("Transaction is under Process"),
    DEBIT_STARTED("Transaction under process - Debit Started"),
    DEBIT_COMPLETED("Transaction under process - Debit Completed, waiting for Credit part"),
    DEBIT_FAILED("Transaction Aborted- Debit Failed"),
    CREDIT_STARTED("Transaction under process - Debit Completed & Credit in process"),
    CREDIT_FAILED("Transaction Aborted - Debit Completed & Credit Failed"),
    CREDIT_COMPLETED("Transaction under process - Debit Completed & Credit Completed"),
    COMPLETED("Transaction is Successful"),
    FAILED("Transaction failed");

    private String status;

    TransactionStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
