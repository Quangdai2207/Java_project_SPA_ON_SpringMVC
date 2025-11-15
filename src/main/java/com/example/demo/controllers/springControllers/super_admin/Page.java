package com.example.demo.controllers.springControllers.super_admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("superAdminPage")
@RequestMapping("/super-admin")
public class Page {
    @GetMapping({"/dashboard", "", "/"})
    public String page() {
        return "super_admin/page";
    }
}
