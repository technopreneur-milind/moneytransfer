package com.technopreneur.moneytransfer.web;

import static spark.Spark.get;
import static spark.Spark.post;

import java.util.List;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.technopreneur.moneytransfer.dto.AccountDto;
import com.technopreneur.moneytransfer.model.Response;
import com.technopreneur.moneytransfer.model.Status;
import com.technopreneur.moneytransfer.service.AccountService;

public class AccountController implements BaseController {

	@Inject
	private AccountService accountService;

	@Override
	public void addEndPoints() {
		addAccounts();
		getAccounts();
		getAccount();
	}

	private void getAccount() {
		get("/accounts/:accountId", (request, response) -> {
			response.type("application/json");
			Long accountId = Long.valueOf(request.params(":accountId"));
			AccountDto account = accountService.getAccount(accountId);
			return new Gson().toJson(new Response(Status.SUCCESS, account));
		});

	}

	private void getAccounts() {
		get("/accounts", (request, response) -> {
			response.type("application/json");
			List<AccountDto> accounts = accountService.getAccounts();
			return new Gson().toJson(new Response(Status.SUCCESS, accounts));
		});

	}

	private void addAccounts() {
		post("/accounts", (request, response) -> {
			response.type("application/json");
			AccountDto accountDto = new Gson().fromJson(request.body(), AccountDto.class);
			AccountDto accountCreated = accountService.createAccount(accountDto);
			return new Gson().toJson(new Response(Status.SUCCESS, accountCreated));
		});

	}

}
