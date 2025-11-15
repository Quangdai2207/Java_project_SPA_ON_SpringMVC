package com.example.demo.controllers.springControllers.doctor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("doctorPage")
@RequestMapping("/doctor")
public class Page {
    @GetMapping({"/portal", "", "/"})
    public String page() {
        return "doctor/page";
    }
}
