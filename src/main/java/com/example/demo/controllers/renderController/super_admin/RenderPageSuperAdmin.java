package com.example.demo.controllers.renderController.super_admin;

import com.example.demo.helpers.metadata_render.homePage.MetadataRender;
import com.example.demo.services.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("SuperAdminPageRender")
@RequestMapping("/super-admin")
public class RenderPageSuperAdmin {
    @Autowired
    private AccountService accountService;
    // ** Route tra ve MetaData cho dashboard chung cua Role Admin

    @GetMapping(value = "/render-super-admin-page", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> portal(Authentication authentication) {
        return MetadataRender.metadata(authentication, accountService);
    }
}
