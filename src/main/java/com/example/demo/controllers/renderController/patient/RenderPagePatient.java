package com.example.demo.controllers.renderController.patient;

import com.example.demo.helpers.metadata_render.homePage.MetadataRender;
import com.example.demo.services.account.AccountService;
import com.example.demo.services.appointment.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller dung de render cac trang chinh cho cac ROLEs
 * **/
@RestController("PatientPageRender")
@RequestMapping("/patient")
public class RenderPagePatient {
    @Autowired
    private AccountService accountService;
    // ** Route tra ve MetaData cho dashboard chung cua Role Admin

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping(value = "/render-patient-page", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> portal(Authentication authentication) {
        return MetadataRender.metadata(authentication, accountService);
    }
}
