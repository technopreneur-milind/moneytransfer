package com.technopreneur.moneytransfer.model;

import java.util.ArrayList;
import java.util.List;

public class MoneyTransferBatchDetail {

    public List<MoneyTransferDetail> moneyTransferDetails = new ArrayList<>();

    public List<MoneyTransferDetail> getMoneyTransferDetails() {
        return moneyTransferDetails;
    }

    public void setMoneyTransferDetails(List<MoneyTransferDetail> moneyTransferDetails) {
        this.moneyTransferDetails = moneyTransferDetails;
    }

    @Override
    public String toString() {
        return "MoneyTransferBatchDetail{" +
                "moneyTransferDetails=" + moneyTransferDetails +
                '}';
    }
}
