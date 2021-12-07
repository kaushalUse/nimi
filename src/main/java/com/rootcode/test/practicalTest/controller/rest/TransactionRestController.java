package com.rootcode.test.practicalTest.controller.rest;

import com.rootcode.test.practicalTest.dto.TransactionRequest;
import com.rootcode.test.practicalTest.entity.Transaction;
import com.rootcode.test.practicalTest.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/rest-api")
public class TransactionRestController {

    @Autowired
    TransactionService transactionService;


    @GetMapping("/view-transactions")
    public String loadTransactions(Model model) {
        List<Transaction> transactions = transactionService.getAll();
        model.addAttribute("transactions", transactions);
        return "ViewTransactions";
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
