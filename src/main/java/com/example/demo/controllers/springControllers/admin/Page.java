package com.example.demo.controllers.springControllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("adminPage")
@RequestMapping(path = "/admin")
public class Page {
    @GetMapping({"/dashboard", "", "/"})
    public String page() {
        return "admin/page";
    }
}
