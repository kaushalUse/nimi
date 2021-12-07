package com.rootcode.test.practicalTest.controller;


import com.rootcode.test.practicalTest.dto.LedgerHolderRequestDTO;
import com.rootcode.test.practicalTest.service.SecurityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {


    @Autowired
    private SecurityServiceImpl securityService;


    @GetMapping("/registration")
    public String loadRegistration(@ModelAttribute("legerHolder") LedgerHolderRequestDTO legerHolder, BindingResult bindingResult, Model model) {
        model.addAttribute("legerHolder", new LedgerHolderRequestDTO());
        return "AddLedgerHolder";
    }


    @PostMapping("/registration")
    public String registration(@ModelAttribute("legerHolder") LedgerHolderRequestDTO legerHolder, BindingResult bindingResult, Model model) {
        model.addAttribute("legerHolder", new LedgerHolderRequestDTO());
        return "AddLedgerHolder";
    }
}