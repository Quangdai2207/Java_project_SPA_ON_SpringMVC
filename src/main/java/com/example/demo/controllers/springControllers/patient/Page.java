package com.example.demo.controllers.springControllers.patient;

import com.example.demo.entities.Account;
import com.example.demo.services.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("patientPage")
@RequestMapping("/patient")
public class Page {
    @Autowired
    private AccountService accountService;

    @GetMapping({"/portal", "", "/"})
    public String page(
            Authentication auth
    ) {
        System.out.println("-------------------------------------------------");
        System.out.println(auth.getPrincipal());
        System.out.println(accountService.findAccountByEmail(auth.getName()).getBranchHospital() == null ?
                "Co du lieu" : "NULL");
        System.out.println("-------------------------------------------------");
        return "patient/page";
    }
}
