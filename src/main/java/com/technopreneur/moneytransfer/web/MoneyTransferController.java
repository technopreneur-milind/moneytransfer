package com.technopreneur.moneytransfer.web;

import static spark.Spark.post;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.technopreneur.moneytransfer.model.MoneyTransferDetail;
import com.technopreneur.moneytransfer.model.Response;
import com.technopreneur.moneytransfer.model.Status;
import com.technopreneur.moneytransfer.service.MoneyTransferService;

public class MoneyTransferController implements BaseController {

	@Inject
	private MoneyTransferService moneyTransferService;

	@Override
	public void addEndPoints() {
		transfer();
	}

	private void transfer() {
		post("/transfer", (request, response) -> {
			response.type("application/json");
			MoneyTransferDetail transferDetail = new Gson().fromJson(request.body(), MoneyTransferDetail.class);
			moneyTransferService.transfer(transferDetail);
			return new Gson().toJson(new Response(Status.SUCCESS));
		});
	}

}
