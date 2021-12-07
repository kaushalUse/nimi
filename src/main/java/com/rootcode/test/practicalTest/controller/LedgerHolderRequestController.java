package com.rootcode.test.practicalTest.controller;

import com.rootcode.test.practicalTest.dto.LedgerHolderRequestDTO;
import com.rootcode.test.practicalTest.entity.LedgerHolderRequest;
import com.rootcode.test.practicalTest.service.LedgerHolderRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LedgerHolderRequestController {

    @Autowired
    LedgerHolderRequestService ledgerHolderRequestService;


    @GetMapping("/ledger-holder-request-list")
    public String getLedgerHolderRequests(Model model) {
        ledgerHolderRequestService.getLedgerHolderRequests();
        model.addAttribute("ledgerHolderRequests", ledgerHolderRequestService.getLedgerHolderRequests());
        return "ViewLedgerHolderRequests";
    }

    @GetMapping("/ledger-holder-request/{id}")
    public String loadLedgerHolderRequest(@PathVariable(required = false) String id, Model model) {
        LedgerHolderRequestDTO ledgerHolderRequestDTO = new LedgerHolderRequestDTO();
        try {
            ledgerHolderRequestDTO = ledgerHolderRequestService.getLedgerHolderRequest(Long.parseLong(id));
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("ledgerHolderRequest", ledgerHolderRequestDTO);
        if(id !=null) {
            return "DeleteLedgerHolderRequest";
        }
        return "AddLedgerHolderRequest";
    }


    @PostMapping("/ledger-holder-request")
    public String createLedgerHolder(@ModelAttribute LedgerHolderRequest ledgerHolderRequest, Model model) {
        try {
            ledgerHolderRequestService.createLedgerHolderRequest(ledgerHolderRequest);

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "AddLedgerHolderRequest";
        }
        model.addAttribute("ledgerHolderRequest", ledgerHolderRequest);
        return "redirect:/ledger-holder-request-list";
    }

    @GetMapping("/ledger-holder-request-delete/{id}")
    public String deleteLedgerHolder(@PathVariable String id, Model model) {
        try {
            ledgerHolderRequestService.deleteLedgerHolder(Long.parseLong(id));
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "DeleteLedgerHolderRequest";
        }
        model.addAttribute("message", "Ledger holder request deleted");
        return "redirect:/ledger-holder-request-list";
    }
}