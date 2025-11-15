package com.example.demo.configurations.security.authentication.components;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    )
            throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        roles.forEach(System.out::println);

        if (roles.contains("ROLE_SUPER_ADMIN")) {
            response.sendRedirect("/super-admin?logged=success");
        } else if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("/admin?logged=success");
        } else if (roles.contains("ROLE_DOCTOR")) {
            response.sendRedirect("/doctor?logged=success");
        } else if (roles.contains("ROLE_PATIENT")){
            response.sendRedirect("/patient?logged=success");
        } else {
            response.sendRedirect("/");
        }
    }
}