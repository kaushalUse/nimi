package com.rootcode.test.practicalTest.service;

import com.rootcode.test.practicalTest.dto.TransactionRequest;
import com.rootcode.test.practicalTest.entity.Transaction;
import com.rootcode.test.practicalTest.exception.TansactionException;

import java.util.List;

public interface TransactionService {

    List<Transaction> getAll();

    void saveTransaction(TransactionRequest transactionRequest) throws TansactionException;
}
