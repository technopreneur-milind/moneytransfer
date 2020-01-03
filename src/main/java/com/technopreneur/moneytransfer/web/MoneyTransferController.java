package com.technopreneur.moneytransfer.web;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.technopreneur.moneytransfer.exception.MoneyTransferValidationException;
import com.technopreneur.moneytransfer.model.MoneyTransferBatchDetail;
import com.technopreneur.moneytransfer.model.MoneyTransferDetail;
import com.technopreneur.moneytransfer.model.Response;
import com.technopreneur.moneytransfer.model.Status;
import com.technopreneur.moneytransfer.service.MoneyTransferService;
import com.technopreneur.moneytransfer.service.TransactionStatusService;
import com.technopreneur.moneytransfer.validate.MoneyAppValidator;
import spark.Request;

import java.util.List;

import static spark.Spark.*;

public class MoneyTransferController implements BaseController {

    @Inject
    private MoneyTransferService moneyTransferService;

    @Inject
    private MoneyAppValidator validator;

    @Inject
    private TransactionStatusService transactionStatusService;

    private final String TRANSFER_QUEUE_MESSAGE ="Your transfers are succesfully queued ! Please refer Data Section for Transaction Id." +
            "You can quote this transaction Id for any queries related to the transfer. You can check status at /transfer/status/{transactionId} endpoint";

    @Override
    public void addEndPoints() {
        transfer();
        batchTransfer();
        transactionStatus();
    }

    private void transactionStatus() {
        get("/transfer/status/:transactionId", (request, response) -> {
            response.type("application/json");
            String transactionId = request.params(":transactionId");
            return new Gson().toJson(transactionStatusService.getTransactionStatus(transactionId));
        });
    }

    private void transfer() {
        post("/transfer", (request, response) -> {
            response.type("application/json");
            MoneyTransferDetail transferDetail = new Gson().fromJson(request.body(), MoneyTransferDetail.class);
            validator.validateMoneyTransferDetail(transferDetail);
            String transactionId = moneyTransferService.transfer(transferDetail);
            return new Gson().toJson(new Response(Status.SUCCESS, TRANSFER_QUEUE_MESSAGE, transactionId));
        });
    }


    private void batchTransfer() {
        post("/batchTransfer", (request, response) -> {
            response.type("application/json");
            MoneyTransferBatchDetail transferBatchDetail = new Gson().fromJson(request.body(), MoneyTransferBatchDetail.class);
            validator.validateMoneyTransferDetail(transferBatchDetail);
            List<String> transactionIds = moneyTransferService.batchTransfer(transferBatchDetail);
            return new Gson().toJson(new Response(Status.SUCCESS, TRANSFER_QUEUE_MESSAGE, transactionIds));
        });
    }
}
