package com.technopreneur.moneytransfer.processor;

import java.util.concurrent.ExecutorService;

public interface AccountProcessingStrategy {

    public ExecutorService getExecutorService();
}
