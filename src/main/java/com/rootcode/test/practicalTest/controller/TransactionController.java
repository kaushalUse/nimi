package com.rootcode.test.practicalTest.controller;

import com.rootcode.test.practicalTest.dto.TransactionRequest;
import com.rootcode.test.practicalTest.entity.Transaction;
import com.rootcode.test.practicalTest.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class TransactionController {

    @Autowired
    TransactionService transactionService;


    @GetMapping("/view-transactions")
    public String loadTransactions(Model model) {
        List<Transaction> transactions = transactionService.getAll();
        model.addAttribute("transactions", transactions);
        return "ViewTransactions";
    }

    @GetMapping("/view-add-transactions")
    public String loadTransactionsWithAdd(Model model) {
        List<Transaction> transactions = transactionService.getAll();
        model.addAttribute("transactions", transactions);
        return "DoTransactions";
    }

    @PostMapping("/do-transaction")
    public String saveTransaction(@RequestBody TransactionRequest transactionRequest, Model model) {
        try {
            transactionService.saveTransaction(transactionRequest);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "DoTransactions";
    }
}
