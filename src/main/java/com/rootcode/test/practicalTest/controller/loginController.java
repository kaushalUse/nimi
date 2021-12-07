package com.rootcode.test.practicalTest.controller;

import com.rootcode.test.practicalTest.service.SecurityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class loginController {

    @Autowired
    private SecurityServiceImpl securityService;


    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        return "login";
    }

    @PostMapping("/login")
    public String userLogin(Model model, String error, String logout) {

        if (securityService.isAuthenticated()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        }


        return "redirect:/registration";
    }

}
