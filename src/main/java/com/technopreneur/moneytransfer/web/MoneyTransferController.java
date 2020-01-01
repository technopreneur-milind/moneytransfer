package com.technopreneur.moneytransfer.web;

import static spark.Spark.post;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.technopreneur.moneytransfer.exception.MoneyTransferValidationException;
import com.technopreneur.moneytransfer.model.MoneyTransferDetail;
import com.technopreneur.moneytransfer.model.Response;
import com.technopreneur.moneytransfer.model.Status;
import com.technopreneur.moneytransfer.service.MoneyTransferService;
import com.technopreneur.moneytransfer.validate.MoneyAppValidator;

public class MoneyTransferController implements BaseController {

	@Inject
	private MoneyTransferService moneyTransferService;

	@Inject
	private MoneyAppValidator validator;

	@Override
	public void addEndPoints() {
		transfer();
	}

	private void transfer() {
		post("/transfer", (request, response) -> {
			response.type("application/json");
			MoneyTransferDetail transferDetail = new Gson().fromJson(request.body(), MoneyTransferDetail.class);
			try {
				validator.validateMoneyTransferDetail(transferDetail);
			} catch (MoneyTransferValidationException e) {
				response.raw().setStatus(400);
				response.body(e.getMessage());
				return new Gson().toJson(new Response(Status.ERROR,"Some Message"));
			}
			moneyTransferService.transfer(transferDetail);
			return new Gson().toJson(new Response(Status.SUCCESS));
		});
	}

}
