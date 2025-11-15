package com.example.demo.controllers.springControllers;

import com.example.demo.entities.Account;
import com.example.demo.services.account.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;
import java.util.UUID;

@Controller("homePageController")
@RequestMapping({"/home", "", "/"})
public class Page {
    @GetMapping({"/index", "", "/"})
    public String page() {
        return "page";
    }
}
