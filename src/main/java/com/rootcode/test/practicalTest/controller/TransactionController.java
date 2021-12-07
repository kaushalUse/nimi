package com.rootcode.test.practicalTest.controller;

import com.rootcode.test.practicalTest.dto.TransactionRequest;
import com.rootcode.test.practicalTest.service.TransactionService;
import com.rootcode.test.practicalTest.util.JwtTokenUtil;
import com.rootcode.test.practicalTest.util.UtilClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

//    @GetMapping("/view-transactions")
//    public String loadTransactions(Model model) {
//        List<TransactionDTO> transactions = transactionService.getAll();
//        model.addAttribute("transactions", transactions);
//        return "ViewTransactions";
//    }


    @RequestMapping(value = {"/view-transactions", "/view-add-transactions"}, method = RequestMethod.GET)
    public String loadTransactionsWithAdd(Model model) {
        model.addAttribute("transactions", transactionService.getAll());
        if (UtilClass.hasRole("ROLE_ADMIN")) {

                model.addAttribute("token", jwtTokenUtil.generateToken(UtilClass.getCurrentUser()));
                return "DoTransactions";
            } else{
                return "ViewTransactions";
            }
        }

        @PostMapping("/do-transaction")
        public String saveTransaction (@RequestBody TransactionRequest transactionRequest, Model model){
            try {
                transactionService.saveTransaction(transactionRequest);
            } catch (Exception e) {
                model.addAttribute("error", e.getMessage());
            }
            return "DoTransactions";
    }
}
