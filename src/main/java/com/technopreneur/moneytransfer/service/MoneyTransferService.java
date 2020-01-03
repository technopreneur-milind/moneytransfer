package com.technopreneur.moneytransfer.service;

import com.technopreneur.moneytransfer.model.MoneyTransferBatchDetail;
import com.technopreneur.moneytransfer.model.MoneyTransferDetail;

import java.util.List;

public interface MoneyTransferService {
	
	public String transfer(MoneyTransferDetail transferDetail);
	public List<String> batchTransfer(MoneyTransferBatchDetail transferBatchDetail);

}
