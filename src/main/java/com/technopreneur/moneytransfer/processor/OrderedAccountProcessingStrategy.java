package com.technopreneur.moneytransfer.processor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrderedAccountProcessingStrategy implements AccountProcessingStrategy {

    @Override
    public ExecutorService getExecutorService() {
        return Executors.newSingleThreadExecutor();
    }
}
