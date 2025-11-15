package com.example.demo.controllers.renderController;

import com.example.demo.helpers.metadata_render.homePage.MetadataRender;
import com.example.demo.services.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("homePageRender")
@RequestMapping("/home")
public class HomeRender {
    @Autowired
    private AccountService accountService;

    // ** Route tra ve toan bo du lieu cho trang home theo các ROLES
    @GetMapping(value = "/render-homepage", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> page(Authentication authentication) {
        return MetadataRender.metadata(authentication, accountService);
    }
}
